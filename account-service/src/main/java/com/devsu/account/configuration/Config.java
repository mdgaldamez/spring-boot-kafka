package com.devsu.account.configuration;

import com.devsu.account.service.AccountService;
import com.devsu.account.service.MovementService;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class Config {

  @Value("${spring.kafka.topic.name}")
  private String topicName;

  @Bean
  public AccountService accountBean() {
    return new AccountService();
  }

  @Bean
  public MovementService movementBean() {
    return new MovementService();
  }

  @Bean
  public NewTopic topic(){
    return TopicBuilder.name(topicName).build();
  }
}
