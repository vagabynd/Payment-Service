package com.evgen.payment.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class DaoConfig {

  @Bean
  RestTemplate restTemplate(ObjectMapper objectMapper) {
    HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
    httpRequestFactory.setConnectTimeout(5000);
    httpRequestFactory.setReadTimeout(5000);

    BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(
        httpRequestFactory);
    RestTemplate restTemplate = new RestTemplate(bufferingClientHttpRequestFactory);
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
    mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
    restTemplate.setMessageConverters(Collections.singletonList(mappingJackson2HttpMessageConverter));

    return restTemplate;
  }

}