package com.splusz.villigo.dto;

import lombok.Data;

@Data
public class BagCreateDto {

    private String name;
    private Long brandId;
    private Long colorId;
    private String detail;
    private int fee;
    private Long userId;
    private Long addressId;
    
}
