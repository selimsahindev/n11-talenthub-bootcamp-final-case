package com.selimsahin.restaurantservice.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class LogProducer {

    @Value("${kafka.topic.error-log}")
    private String errorLogTopic;

    @Value("${kafka.topic.info-log}")
    private String infoLogTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishErrorLog(String message) {
        kafkaTemplate.send(errorLogTopic, message);
    }

    public void publishInfoLog(String message) {
        kafkaTemplate.send(infoLogTopic, message);
    }
}
