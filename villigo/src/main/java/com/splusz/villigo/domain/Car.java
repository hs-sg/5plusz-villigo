package com.splusz.villigo.domain;

import com.splusz.villigo.dto.CarUpdateDto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cars")
@Getter @Setter
@Builder
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseTimeEntity {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String name;
    
    @Basic(optional = false)
    private int old;
    
    @ToString.Exclude @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "brand_id") @Basic(optional = false)
    private Brand brand;
    
    @ToString.Exclude @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "color_id") @Basic(optional = false)
    private Color color;
    
    private String detail;
    
    @Basic(optional = false)
    private int fee;
    
    @ToString.Exclude @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "users_id") @Basic(optional = false)
    private User user;
    
    @ToString.Exclude @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "addresses_id") @Basic(optional = false)
    private Address address;
    
    @Basic(optional = false)
    private Boolean drive;
    
    @Column(name = "min_rental_time")
    private int minRentalTime;
    
    public Car update(CarUpdateDto dto) {
        this.name = dto.getName();
        this.detail = dto.getDetail();
        this.fee = dto.getFee();
        this.drive = dto.getDrive();
        this.minRentalTime =  dto.getMinRentalTime();
        return this;
    }
}
