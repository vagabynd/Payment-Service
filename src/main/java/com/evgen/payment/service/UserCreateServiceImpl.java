package com.evgen.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebInputException;

import com.evgen.payment.model.Pay;
import com.evgen.payment.model.User;
import com.evgen.payment.repository.UserRepository;
import com.evgen.payment.service.api.UserCreateService;
import com.nimbusds.oauth2.sdk.util.StringUtils;

@Service
public class UserCreateServiceImpl implements UserCreateService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public UserCreateServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @Override
  public User createUser(User user, Pay pay) {
    validationUser(user);
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

    //TODO: payment impl
    //https://stripe.com/docs
    //https://habr.com/ru/post/138869/
    //https://habr.com/ru/company/payonline/blog/195854/
    if(true) {
      return userRepository.save(user);
    }
    return null;
  }

  @Override
  public User createUserFromGoogle(String name, Pay pay) {
    User user = new User();
    user.setUserName(name);

    //TODO: payment impl
    //https://stripe.com/docs
    //https://habr.com/ru/post/138869/
    //https://habr.com/ru/company/payonline/blog/195854/
    if(true) {
      return userRepository.save(user);
    }
    return null;
  }

  private void validationUser(User user) {
    if (StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getPassword())) {
      throw new ServerWebInputException("Bad user request");
    }
  }
}