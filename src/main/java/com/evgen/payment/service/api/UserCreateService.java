package com.evgen.payment.service.api;

import com.evgen.payment.model.User;

public interface UserCreateService {

  User createUser(User user);

  User createUserFromGoogle(String name);

}