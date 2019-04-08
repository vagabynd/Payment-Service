package com.evgen.payment.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.evgen.payment.model.User;

public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findByUserName(String userName);

}