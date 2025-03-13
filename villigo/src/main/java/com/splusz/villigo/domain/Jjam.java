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
@Table(name = "jjams")
@Getter @Setter
@NoArgsConstructor
public class Jjam {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private int quantity;
    
    @Basic(optional = false)
    private int unitPrice;

	@ToString.Exclude @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id") @Basic(optional = false)
    private User buyer;

	@Basic(optional = false)
    private LocalDateTime transactionTime;
}