package com.splusz.villigo.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.NaturalId;
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
@Table(name = "reservations")
@Getter @Setter
@NoArgsConstructor
public class Reservation extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Basic(optional = false)
    private Long itemId; // 좋아요 대상 아이템 ID (BAG, CAR 등)
	
	@ToString.Exclude @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "rental_category_id") @Basic(optional = false)
    private RentalCategory rentalCategory; // 좋아요 대상의 유형 (예: "BAG", "CAR")
	
	@Basic(optional = false)
    private String status;

	@Basic(optional = false)
    private LocalDateTime startTime;
	
	@Basic(optional = false)
    private LocalDateTime endTime;

	@ToString.Exclude @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") @Basic(optional = false)
    private User renter;
    
}
