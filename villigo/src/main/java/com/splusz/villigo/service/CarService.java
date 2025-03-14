package com.splusz.villigo.service;

import org.springframework.stereotype.Service;

import com.splusz.villigo.domain.Address;
import com.splusz.villigo.domain.Brand;
import com.splusz.villigo.domain.Car;
import com.splusz.villigo.domain.Color;
import com.splusz.villigo.domain.User;
import com.splusz.villigo.dto.CarCreateDto;
import com.splusz.villigo.dto.CarUpdateDto;
import com.splusz.villigo.repository.AddressRepository;
import com.splusz.villigo.repository.BrandRepository;
import com.splusz.villigo.repository.CarRepository;
import com.splusz.villigo.repository.ColorRepository;
import com.splusz.villigo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarService {

    private final CarRepository carRepo;
    private BrandRepository brandRepo;
    private ColorRepository colorRepo;
    private UserRepository userRepo;
    private AddressRepository addRepo;

    public Car create(CarCreateDto dto) {
        Brand brand = brandRepo.findById(dto.getBrandId()).orElseThrow();
        Color color = colorRepo.findById(dto.getColorId()).orElseThrow();
        User user = userRepo.findById(dto.getUserId()).orElseThrow();
        Address address = addRepo.findById(dto.getAddressId()).orElseThrow();

        Car entity = Car.builder()
        .name(dto.getName())
        .old(dto.getOld())
        .brand(brand)
        .color(color)
        .detail(dto.getDetail())
        .fee(dto.getFee())
        .user(user)
        .address(address)
        .drive(dto.getDrive())
        .minRentalTime(dto.getMinRentalTime())
        .build();

        carRepo.save(entity);
        return entity;
    }

    public Car readById(Long id) {
        Car car = carRepo.findById(id).orElseThrow();
        return car;
    }

    public Car update(CarUpdateDto dto) {
        Car entity = carRepo.findById(dto.getId()).orElseThrow();
        entity.update(dto);
        carRepo.save(entity);

        return entity;
    }

    public void deleteById(Long id) {
        carRepo.deleteById(id);
    }
}
