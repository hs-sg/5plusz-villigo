package com.splusz.villigo.dto;

import com.splusz.villigo.domain.Car;

import lombok.Data;

@Data
public class CarUpdateDto {

    private Long id;
    private String name;
    private String detail;
    private int fee;
    private Boolean drive;
    private int minRentalTime;

    public Car toEntity(CarUpdateDto dto)
    {
        return Car.builder()
            .id(dto.getId())
            .name(dto.getName())
            .detail(dto.getDetail())
            .fee(dto.getFee())
            .drive(dto.getDrive())
            .minRentalTime(dto.getMinRentalTime())
            .build();
    }
}
