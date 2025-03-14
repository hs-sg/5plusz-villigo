package com.splusz.villigo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.splusz.villigo.domain.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{
	
	List<ChatMessage> findByChatRoomIdOrderByCreatedAtAsc(Long chatRoomId);

}
