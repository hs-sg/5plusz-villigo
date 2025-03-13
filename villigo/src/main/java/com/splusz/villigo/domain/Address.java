package com.splusz.villigo.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter @Setter
@NoArgsConstructor
public class Address {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String region;
    
    @Basic(optional = false)
    private String detailAddress;
    
    @Basic(optional = false)
    private Double latitude;
    
    @Basic(optional = false)
    private Double longitude;
}
