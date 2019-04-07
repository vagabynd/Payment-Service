package com.evgen.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
  public RedirectView createUser(User user) {
    try {
      userCreateServiceImpl.createUser(user);

      return new RedirectView("/login");
    } catch (HttpServerErrorException e) {
      return new RedirectView("/error-registration");
    }
  }

  @RequestMapping("api/v1/error-registration")
  public String registrationError(User user) {
    //model.addAttribute("registrationError", true);

    return "registrationForm";
  }
}
