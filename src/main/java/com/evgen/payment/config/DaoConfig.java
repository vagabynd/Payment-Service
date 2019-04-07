package com.evgen.payment.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
@EnableMongoRepositories(basePackages = "com.evgen.payment.repository")
public class DaoConfig extends AbstractMongoConfiguration {

  @Value("${mongo.url}")
  private String url;

  @Value("${mongo.name}")
  private String name;

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

  @Override
  protected String getDatabaseName() {
    return name;
  }

  @Override
  public MongoClient mongoClient() {
    MongoClientURI uri = new MongoClientURI(url);
    return new MongoClient(uri);
  }

  @Override
  protected Collection<String> getMappingBasePackages() {
    return Collections.singleton("com.evgen");
  }

}