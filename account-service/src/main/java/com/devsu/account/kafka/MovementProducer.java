package com.devsu.account.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MovementProducer {
  private static final Logger LOGGER = LoggerFactory.getLogger(MovementProducer.class);

  private NewTopic topic;
  private KafkaTemplate<String, String> kafkaTemplate;

  public MovementProducer(NewTopic topic, KafkaTemplate<String, String> kafkaTemplate) {
    this.topic = topic;
    this.kafkaTemplate = kafkaTemplate;
  }

  public void sendMessage(String message) {
      LOGGER.info("Mensaje enviado => {}", message);
      kafkaTemplate.send(topic.name(), message);
  }
}
