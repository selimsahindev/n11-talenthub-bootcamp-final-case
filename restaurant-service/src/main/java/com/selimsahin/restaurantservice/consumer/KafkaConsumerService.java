package com.selimsahin.restaurantservice.consumer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    @KafkaListener(topics = "${kafka.topic.rating-updated}")
    public void consumeAverageRating(ConsumerRecord<String, String> payload) {

        System.out.println("Consumed rating updated event: " + payload.value());
    }
}
