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
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
public class User extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false) @NaturalId
    private String username;
    
    @Basic(optional = false)
    private String password;
    
    @Basic(optional = false)
    private String phone;
    
    @Basic(optional = false)
    private String region;
    
    @ToString.Exclude @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "theme_id") @Basic(optional = false)
    private Theme theme;
    
    @Basic(optional = false)
    private int score;
    
}

