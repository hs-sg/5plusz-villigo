package com.splusz.villigo.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "likes")
@Getter @Setter
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 좋아요 누른 사용자

    private Long itemId; // 좋아요 대상 아이템 ID (BAG, CAR, JJAM 등)
    private String itemType; // 좋아요 대상의 유형 (예: "BAG", "CAR", "JJAM")

    private LocalDateTime createdTime = LocalDateTime.now();
}
