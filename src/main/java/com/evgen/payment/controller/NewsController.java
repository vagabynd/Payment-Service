package com.evgen.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.evgen.payment.model.News;
import com.evgen.payment.model.User;
import com.evgen.payment.service.api.VkNewsService;

@Controller
public class NewsController {

  private final VkNewsService vkNewsService;

  public NewsController(VkNewsService vkNewsService) {
    this.vkNewsService = vkNewsService;
  }

  //get news with isPremium flag
  @PostMapping("/api/v1/news")
  public ResponseEntity<News> getNews(@RequestBody User user) {
    return ResponseEntity.ok().body(vkNewsService.getVkNews(user));
  }

}
