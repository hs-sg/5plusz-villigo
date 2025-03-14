package com.splusz.villigo.repository;

import com.splusz.villigo.domain.ChatParty;
import com.splusz.villigo.domain.ChatPartyId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatPartyRepository extends JpaRepository<ChatParty, ChatPartyId> {
	List<ChatParty> findByChatRoomId(Long chatRoomId);
    
    @Query("SELECT cp FROM ChatParty cp WHERE cp.chatRoom.id = :chatRoomId AND cp.participant.id = :userId")
    Optional<ChatParty> findByChatRoomIdAndUserId(@Param("chatRoomId") Long chatRoomId, @Param("userId") Long userId);
}