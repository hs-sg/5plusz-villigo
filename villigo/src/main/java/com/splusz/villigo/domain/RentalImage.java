	package com.splusz.villigo.domain;

	import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rental_images")
@Getter @Setter
@NoArgsConstructor
public class RentalImage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long itemId; // 이미지가 속한 아이템 ID (BAG, CAR, JJAM 등)
    private String itemType; // 아이템의 유형 (예: "BAG", "CAR", "JJAM")
    private String filePath; // 이미지 파일 경로
    
}
