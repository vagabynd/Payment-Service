package com.evgen.payment.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.evgen.payment.model.User;
import com.evgen.payment.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String name) throws RuntimeException {
    User user = getUserByUserNameOrThrowException(name);
    Set<GrantedAuthority> roles = new HashSet<>();
    roles.add(new SimpleGrantedAuthority("ROLE_USER"));

    return new org.springframework.security.core.userdetails.User(
        user.getUserName(),
        user.getPassword(),
        roles);
  }

  private User getUserByUserNameOrThrowException(String userName) throws RuntimeException {
    return userRepository.findByUserName(userName)
        .orElseThrow(() -> new RuntimeException(String.format("User with name %s not found", userName)));
  }

}