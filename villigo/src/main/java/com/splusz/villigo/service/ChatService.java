package com.splusz.villigo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.splusz.villigo.domain.ChatMessage;
import com.splusz.villigo.domain.ChatParty;
import com.splusz.villigo.domain.ChatRoom;
import com.splusz.villigo.domain.User;
import com.splusz.villigo.dto.ChatMessageDto;
import com.splusz.villigo.repository.ChatMessageRepository;
import com.splusz.villigo.repository.ChatPartyRepository;
import com.splusz.villigo.repository.ChatRoomRepository;
import com.splusz.villigo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

	private final ChatRoomRepository chatRoomRepo;
	private final ChatMessageRepository chatMessageRepo;
	private final ChatPartyRepository chatPartyRepo;
	private final UserRepository userRepo;

	// 1:1 채팅방 생성
	@Transactional
	public ChatRoom createChatRoom(Long userId1, Long userId2) {

		User user1 = userRepo.findById(userId1).orElseThrow();
		User user2 = userRepo.findById(userId2).orElseThrow();

		// 이미 1:1 채팅방이 있는지 확인하는 로직
	    ChatRoom existingChatRoom = findExistingChatRoom(userId1, userId2);
        if (existingChatRoom != null) {
            log.info("기존 채팅방 반환: {}", existingChatRoom.getId());
            return existingChatRoom;
        }

		// 새 채팅방 생성
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.setName(user1.getUsername() + ", " + user2.getUsername());
		chatRoom.setStatus("ACTIVE");
		chatRoomRepo.save(chatRoom);
		log.info("새 채팅방 생성: ID={}", chatRoom.getId());

		// 채팅방 참가자 추가
		chatPartyRepo.save(new ChatParty(chatRoom, user1));
		chatPartyRepo.save(new ChatParty(chatRoom, user2));

		return chatRoom;

	}

	// 채팅 저장
	@Transactional
	public ChatMessage saveMessage(ChatMessageDto messageDto) {

		ChatRoom chatRoom = chatRoomRepo.findById(messageDto.getChatRoomId()).orElseThrow();
		User sender = userRepo.findById(messageDto.getSenderId()).orElseThrow();

		ChatMessage message = new ChatMessage();
		message.setChatRoom(chatRoom);
		message.setSender(sender);
		
	    try {
	        message.setMessageType(messageDto.getMessageType());
	    } catch (IllegalArgumentException e) {
	    	log.warn("⚠️ 유효하지 않은 메시지 타입: {}", messageDto.getMessageType());
	        throw new IllegalArgumentException("유효하지 않은 메시지 타입: " + messageDto.getMessageType());
	    }
	    
		message.setContent(messageDto.getContent());
        log.info("채팅 메시지 저장: 채팅방={} | 보낸 사람={} | 내용={}", 
                chatRoom.getId(), sender.getId(), message.getContent()
            );

		return chatMessageRepo.save(message);

	}

	// 채팅방 메시지 목록 조회
	@Transactional(readOnly = true)
	public List<ChatMessageDto> getChatMessages(Long chatRoomId) {
		List<ChatMessage> messages = chatMessageRepo.findByChatRoomIdOrderByCreatedAtAsc(chatRoomId);
		
        if (messages.isEmpty()) {
            log.warn("채팅방 {}에 저장된 메시지가 없음", chatRoomId);
        } else {
            log.info("채팅방 {}의 메시지 개수: {}개", chatRoomId, messages.size());
        }

		return messages.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	private ChatMessageDto convertToDto(ChatMessage message) {
		ChatMessageDto dto = new ChatMessageDto();
		dto.setId(message.getId());
		dto.setChatRoomId(message.getChatRoom().getId());
		dto.setSenderId(message.getSender().getId());
		dto.setSenderName(message.getSender().getUsername());
		dto.setMessageType(message.getMessageType());
		dto.setContent(message.getContent());
		dto.setCreatedAt(message.getCreatedTime());
		return dto;
	}

	// 채팅방 목록 조회
	@Transactional(readOnly = true)
	public List<ChatRoom> getUserChatRooms(Long userId) {
		List<ChatRoom> chatRooms = chatRoomRepo.findByParticipantId(userId);
		
        if (chatRooms.isEmpty()) {
            log.warn("사용자 {}의 채팅방 없음", userId);
        } else {
            log.info("사용자 {}의 채팅방 목록 반환: {}개", userId, chatRooms.size());
        }
		
		return chatRooms;
	}

	// 상대방 찾기
    public Long getReceiverId(ChatRoom chatRoom, Long senderId) {
        return chatPartyRepo.findByChatRoomId(chatRoom.getId()).stream()
                .map(ChatParty::getParticipant) // 채팅방 참가자 가져오기
                .filter(user -> !user.getId().equals(senderId)) // 본인이 아닌 상대방 찾기
                .map(User::getId)
                .findFirst() // 상대방 ID 반환
                .orElseThrow(() -> {
                    log.error("상대방을 찾을 수 없습니다. 채팅방: {} | 보낸 사람: {}", chatRoom.getId(), senderId);
                    return new IllegalArgumentException("상대방을 찾을 수 없습니다.");
                });
    }
	
    // 기존 채팅방 조회 (중복 방지)
	@Transactional(readOnly = true)
	public ChatRoom findExistingChatRoom(Long userId1, Long userId2) {
        ChatRoom chatRoom = chatRoomRepo.findExistingChatRoom(userId1, userId2);
        if (chatRoom != null) {
            log.info("기존 채팅방 조회 성공: ID={}", chatRoom.getId());
        }
        return chatRoom;
	}

}
