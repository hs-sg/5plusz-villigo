package com.splusz.villigo.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "chat_messages")
@Getter @Setter
@NoArgsConstructor
public class ChatMessage extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "chat_room_id") @Basic(optional = false)
    private ChatRoom chatRoom;

    @ToString.Exclude @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") @Basic(optional = false)
    private User sender;

    @Basic(optional = false)
    private String messageType;
    
    @Basic(optional = false)
    private String content;

}
