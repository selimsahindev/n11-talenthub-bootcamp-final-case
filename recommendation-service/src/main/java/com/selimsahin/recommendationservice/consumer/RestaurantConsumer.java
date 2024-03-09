package com.selimsahin.recommendationservice.consumer;

import com.selimsahin.recommendationservice.event.RestaurantCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author selimsahindev
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantConsumer {

    private final ApplicationEventPublisher eventPublisher;

    @KafkaListener(topics = "${kafka.topic.restaurant-created}")
    public void consume(ConsumerRecord<String, String> payload) {

        RestaurantCreatedEvent event = new RestaurantCreatedEvent(payload.value());
        eventPublisher.publishEvent(event);
    }
}
