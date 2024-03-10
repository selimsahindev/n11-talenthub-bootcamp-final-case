package com.selimsahin.recommendationservice.consumer;

import com.selimsahin.recommendationservice.event.RestaurantCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class RestaurantConsumer {

    private final ApplicationEventPublisher eventPublisher;

    @KafkaListener(topics = "${kafka.topic.restaurant-created}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, String> payload) {

        // Publish restaurant created event to the application.
        eventPublisher.publishEvent(new RestaurantCreatedEvent(payload.value()));
    }
}
