	package com.splusz.villigo.domain;

	import jakarta.persistence.Id;

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
@Table(name = "rental_images")
@Getter @Setter
@NoArgsConstructor
public class RentalImage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private Long itemId; // 이미지가 속한 아이템 ID (BAG, CAR, JJAM 등)
    
	@ToString.Exclude @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "rental_category_id") @Basic(optional = false)
    private RentalCategory rentalCategory; // 좋아요 대상의 유형 (예: "BAG", "CAR")
	
	@Basic(optional = false)
    private String filePath; // 이미지 파일 경로
    
}
