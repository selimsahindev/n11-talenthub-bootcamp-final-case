package com.selimsahin.restaurantservice.kafka.consumer;

import com.selimsahin.restaurantservice.event.AverageRatingUpdatedEvent;
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
public class AverageRatingUpdateConsumer {

    private final ApplicationEventPublisher applicationEventPublisher;

    @KafkaListener(topics = "${kafka.topic.average-rating-update}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeAverageRatingUpdate(ConsumerRecord<String, String> payload) {
        
        applicationEventPublisher.publishEvent(new AverageRatingUpdatedEvent(payload.value()));
    }
}
