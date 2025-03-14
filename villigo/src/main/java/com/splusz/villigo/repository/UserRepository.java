package com.splusz.villigo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.splusz.villigo.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
