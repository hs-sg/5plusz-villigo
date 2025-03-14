package com.splusz.villigo.domain;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "USERS")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
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

