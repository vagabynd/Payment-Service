package com.evgen.payment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evgen.payment.model.User;

public interface UserRepository extends JpaRepository<User, String> {

  Optional<User> findByUserName(String userName);

}