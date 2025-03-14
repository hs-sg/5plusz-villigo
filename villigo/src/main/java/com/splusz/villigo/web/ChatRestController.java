package com.splusz.villigo.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.splusz.villigo.domain.ChatRoom;
import com.splusz.villigo.dto.ChatMessageDto;
import com.splusz.villigo.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatRestController {

	private final ChatService chatService;

	@PostMapping("/rooms")
	public ResponseEntity<ChatRoom> createChatRoom(@RequestParam Long userId1, @RequestParam Long userId2) {

		// 기존 채팅방이 있는지 확인
		ChatRoom existingChatRoom = chatService.findExistingChatRoom(userId1, userId2);
		
        if (existingChatRoom != null) {
            log.info("기존 채팅방 반환: {}", existingChatRoom.getId());
            return ResponseEntity.ok(existingChatRoom);
        }
		
	    // 없으면 새로 생성
		ChatRoom chatRoom = chatService.createChatRoom(userId1, userId2);
		log.info("새 채팅방 생성: {}", chatRoom.getId());
		
		return ResponseEntity.ok(chatRoom);
	}
	
	@GetMapping("/rooms/user/{userId}")
	public ResponseEntity<List<ChatRoom>> getUserChatRooms(@PathVariable Long userId) {
		List<ChatRoom> chatRooms = chatService.getUserChatRooms(userId);
		
        if (chatRooms.isEmpty()) {
            log.warn("사용자 {}의 채팅방 없음", userId);
        } else {
            log.info("사용자 {}의 채팅방 목록 반환: {}개", userId, chatRooms.size());
        }
		
		return ResponseEntity.ok(chatRooms.isEmpty() ? List.of() : chatRooms); // 채팅방이 없을경우 빈 배열 반환
	}
	
	@GetMapping("/rooms/{roomId}/messages")
	public ResponseEntity<List<ChatMessageDto>> getChatMessages(@PathVariable Long roomId) {
		List<ChatMessageDto> messages = chatService.getChatMessages(roomId);
		
        if (messages.isEmpty()) {
            log.warn("채팅방 {}에 저장된 메시지가 없음", roomId);
        } else {
            log.info("채팅방 {}의 메시지 개수: {}개", roomId, messages.size());
        }
		
		return ResponseEntity.ok(messages.isEmpty() ? List.of() : messages);
	}

}
