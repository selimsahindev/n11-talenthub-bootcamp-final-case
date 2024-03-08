package com.selimsahin.recommendationservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantListener {

    @KafkaListener(topics = "${kafka.topic.restaurant-created}")
    public void consume(ConsumerRecord<String, String> payload) {

        log.info("Consuming restaurant event: {}", payload.value());
    }
}
