package com.evgen.payment.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.evgen.payment.model.News;
import com.evgen.payment.model.User;
import com.evgen.payment.repository.UserRepository;
import com.evgen.payment.service.api.VkNewsService;

@Service
public class VkNewsServiceImpl implements VkNewsService {

  private final RestTemplate restTemplate;
  private final UserRepository userRepository;
  private final static String URL = "https://api.vk.com/method/wall.get?access_token=7a5de8997a5de8997a5de899917a34fc5777a5d7a5de8992620176c45c38ea6bdcec820&owner_id=-179220995&fields=photo_500&v=5.52";

  public VkNewsServiceImpl(RestTemplate restTemplate, UserRepository userRepository) {
    this.restTemplate = restTemplate;
    this.userRepository = userRepository;
  }

  public News getVkNews() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    News news = new News();
    news.setText(restTemplate.getForObject(URL, String.class));

    try {
      userRepository.findByUserName(authentication.getName())
          .orElseThrow(() -> new RuntimeException("User is not premium"));
      news.setIsPremium(true);
    } catch (RuntimeException e) {
      news.setIsPremium(false);
    }

    return news;
  }

}