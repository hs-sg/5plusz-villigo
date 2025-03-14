package com.splusz.villigo.dto;

import lombok.Data;

@Data
public class CarCreateDto {

    private String name;
    private int old;
    private Long brandId;
    private Long colorId;
    private String detail;
    private int fee;
    private Long userId;
    private Long addressId;
    private Boolean drive;
    private int minRentalTime;
    
}
