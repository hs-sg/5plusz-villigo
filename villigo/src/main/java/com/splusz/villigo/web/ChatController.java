package com.splusz.villigo.web;

import com.splusz.villigo.domain.ChatMessage;
import com.splusz.villigo.dto.ChatMessageDto;
import com.splusz.villigo.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    // 클라이언트가 /app/chat.sendMessage로 메시지를 보내면 이 메서드가 처리
    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessageDto chatMessageDto) {
        // 메시지 저장
        ChatMessage savedMessage = chatService.saveMessage(chatMessageDto);
        
        // 변환된 메시지 DTO 생성
        ChatMessageDto responseDto = new ChatMessageDto(
            savedMessage.getId(),
            savedMessage.getChatRoom().getId(),
            savedMessage.getSender().getId(),
            savedMessage.getSender().getUsername(),
            savedMessage.getMessageType(),
            savedMessage.getContent(),
            savedMessage.getCreatedTime()
        );
        
        log.info("채팅방 {}에 메시지 전송 (보낸 사람: {}): {}", 
                savedMessage.getChatRoom().getId(),
                savedMessage.getSender().getId(),
                savedMessage.getContent()
            );

        // 채팅방의 모든 참가자에게 메시지 브로드캐스트
        messagingTemplate.convertAndSend(
            "/topic/chat." + savedMessage.getChatRoom().getId(),
            responseDto
        );
    }
    
    // 1:1 메시지 전송 (특정 사용자에게만 전송)
    @MessageMapping("/chat.sendPrivateMessage")
    public void sendPrivateMessage(@Payload ChatMessageDto chatMessageDto) {
        // 메시지 저장
        ChatMessage savedMessage = chatService.saveMessage(chatMessageDto);
        
        // receiverId 찾기 (현재 채팅방에서 상대방 ID 조회)
        Long receiverId;
        try {
            receiverId = chatService.getReceiverId(savedMessage.getChatRoom(), savedMessage.getSender().getId());
        } catch (Exception e) {
            log.error("상대방을 찾을 수 없습니다. (보낸 사람: {}, 채팅방: {}) - {}", 
                savedMessage.getSender().getId(), 
                savedMessage.getChatRoom().getId(), 
                e.getMessage()
            );
            return; // 예외 발생 시 메시지 전송하지 않음
        }
        
        // 변환된 메시지 DTO 생성
        ChatMessageDto responseDto = new ChatMessageDto(
                savedMessage.getId(),
                savedMessage.getChatRoom().getId(),
                savedMessage.getSender().getId(),
                savedMessage.getSender().getUsername(),
                savedMessage.getMessageType(),
                savedMessage.getContent(),
                savedMessage.getCreatedTime()
            );
        
        log.info("사용자 {}에게 1:1 메시지 전송 (보낸 사람: {}, 채팅방: {}): {}", 
                receiverId,
                savedMessage.getSender().getId(),
                savedMessage.getChatRoom().getId(),
                savedMessage.getContent()
            );

            // 1:1 메시지 전송
            messagingTemplate.convertAndSendToUser(
                receiverId.toString(),
                "/queue/messages",
                responseDto
            );
    }
}