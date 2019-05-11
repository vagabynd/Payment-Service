package com.evgen.payment.service.api;

import com.evgen.payment.model.Pay;
import com.evgen.payment.model.User;

public interface UserCreateService {

  User createUser(User user, Pay pay);

  User createUserFromGoogle(String name, Pay pay);

}