package com.selimsahin.restaurantservice.kafka;

import com.selimsahin.restaurantservice.dto.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author selimsahindev
 */
@Component
@RequiredArgsConstructor
public class RestaurantEventProducer {

    @Value("${kafka.topic.restaurant-created}")
    private String restaurantCreatedTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishRestaurantCreatedEvent(RestaurantResponse restaurant) {

        System.out.println("RestaurantEventProducer.publishRestaurantCreatedEvent: " + restaurant);
        kafkaTemplate.send(restaurantCreatedTopic, restaurant);
    }
}
