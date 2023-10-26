package com.devsu.client.configuration;

import com.devsu.client.service.ClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {
  @Bean
  public ClientService customerBean() {
    return new ClientService();
  }
}
