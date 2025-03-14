package com.splusz.villigo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.splusz.villigo.domain.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

	// 사용자가 참여한 채팅방 조회
	@Query("SELECT DISTINCT cr FROM ChatRoom cr " +
		       "JOIN ChatParty cp ON cr.id = cp.chatRoom.id " +
		       "WHERE cp.participant.id = :userId")
		List<ChatRoom> findByParticipantId(@Param("userId") Long userId);


    // 두 사용자가 모두 포함된 채팅방이 있는지 확인
    @Query("SELECT cr FROM ChatRoom cr " +
           "JOIN ChatParty cp1 ON cr.id = cp1.chatRoom.id " +
           "JOIN ChatParty cp2 ON cr.id = cp2.chatRoom.id " +
           "WHERE cp1.participant.id = :userId1 AND cp2.participant.id = :userId2")
    ChatRoom findExistingChatRoom(@Param("userId1") Long userId1, @Param("userId2") Long userId2);

	
}
