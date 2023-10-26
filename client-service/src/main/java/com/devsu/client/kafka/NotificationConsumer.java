package com.devsu.client.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.SerializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumer.class);
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(ConsumerRecord<String, String> payload) {
        try {
            LOGGER.info("Topic: {}", topicName);
            LOGGER.info("key: {}", payload.key());
            LOGGER.info("Headers: {}", payload.headers());
            LOGGER.info("Partion: {}", payload.partition());
            LOGGER.info("Order: {}", payload.value());
        } catch (SerializationException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
    }
}
