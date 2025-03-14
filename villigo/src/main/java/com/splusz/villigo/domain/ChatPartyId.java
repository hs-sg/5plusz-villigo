package com.splusz.villigo.domain;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ChatPartyId implements Serializable {  // 반드시 Serializable 구현

    private Long chatRoom;  // ID 값만 저장
    private Long participant;  // ID 값만 저장

    public ChatPartyId(Long chatRoom, Long participant) {
        this.chatRoom = chatRoom;
        this.participant = participant;
    }
}
