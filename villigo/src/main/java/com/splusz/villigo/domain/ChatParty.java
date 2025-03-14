package com.splusz.villigo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "chat_parties")
@IdClass(ChatPartyId.class)
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ChatParty {

    @Id
    private Long chatRoom;  // 기본 키를 ID 값으로 변경

    @Id
    private Long participant;  // 기본 키를 ID 값으로 변경

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", insertable = false, updatable = false)
    private ChatRoom chatRoomEntity;  // 관계 매핑용 필드 추가

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id", insertable = false, updatable = false)
    private User participantEntity;  // 관계 매핑용 필드 추가

    public ChatParty(ChatRoom chatRoom, User participant) {
        this.chatRoom = chatRoom.getId();
        this.participant = participant.getId();
        this.chatRoomEntity = chatRoom;
        this.participantEntity = participant;
    }
}
