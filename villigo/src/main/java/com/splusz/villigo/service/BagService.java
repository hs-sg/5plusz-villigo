package com.splusz.villigo.service;

import org.springframework.stereotype.Service;

import com.splusz.villigo.domain.Address;
import com.splusz.villigo.domain.Bag;
import com.splusz.villigo.domain.Brand;
import com.splusz.villigo.domain.Color;
import com.splusz.villigo.domain.User;
import com.splusz.villigo.dto.BagCreateDto;
import com.splusz.villigo.dto.BagUpdateDto;
import com.splusz.villigo.repository.AddressRepository;
import com.splusz.villigo.repository.BagRepository;
import com.splusz.villigo.repository.BrandRepository;
import com.splusz.villigo.repository.ColorRepository;
import com.splusz.villigo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BagService {

    private final BagRepository bagRepo;
    private BrandRepository brandRepo;
    private ColorRepository colorRepo;
    private UserRepository userRepo;
    private AddressRepository addRepo;

    public Bag create(BagCreateDto dto) {

        Brand brand = brandRepo.findById(dto.getBrandId()).orElseThrow();
        Color color = colorRepo.findById(dto.getColorId()).orElseThrow();
        User user = userRepo.findById(dto.getUserId()).orElseThrow();
        Address address = addRepo.findById(dto.getAddressId()).orElseThrow();

        Bag entity = Bag.builder()
            .name(dto.getName())
            .brand(brand)
            .color(color)
            .detail(dto.getDetail())
            .fee(dto.getFee())
            .user(user)
            .address(address)
            .build();

        bagRepo.save(entity);

        return entity;
    }

    public Bag readById(Long id) {
        Bag bag = bagRepo.findById(id).orElseThrow();
        return bag;
    }

    public Bag update(BagUpdateDto dto) {
        Bag entity = bagRepo.findById(dto.getId()).orElseThrow();
        entity.update(dto);
        bagRepo.save(entity);

        return entity;
    }

    public void deleteById(Long id) {
        bagRepo.deleteById(id);
    }
}
