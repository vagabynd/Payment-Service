package com.evgen.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.view.RedirectView;

import com.evgen.payment.model.User;
import com.evgen.payment.service.api.UserCreateService;

@Controller
public class RegistrationController {

  private final UserCreateService userCreateServiceImpl;

  @Autowired
  public RegistrationController(UserCreateService userCreateServiceImpl) {
    this.userCreateServiceImpl = userCreateServiceImpl;
  }

  @PostMapping("api/v1/users")
  public ResponseEntity<User> createUser(User user) {
    return ResponseEntity.ok().body(userCreateServiceImpl.createUser(user));
  }

}
