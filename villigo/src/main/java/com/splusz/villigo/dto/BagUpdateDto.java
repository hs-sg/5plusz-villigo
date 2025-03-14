package com.splusz.villigo.dto;

import com.splusz.villigo.domain.Bag;

import lombok.Data;

@Data
public class BagUpdateDto {

    private Long id;
    private String name;
    private String detail;
    private int fee;

    public Bag toEntity(BagUpdateDto dto)
    {
        return Bag.builder()
            .id(dto.getId())
            .name(dto.getName())
            .detail(dto.getDetail())
            .fee(dto.getFee())
            .build();
    }
}
