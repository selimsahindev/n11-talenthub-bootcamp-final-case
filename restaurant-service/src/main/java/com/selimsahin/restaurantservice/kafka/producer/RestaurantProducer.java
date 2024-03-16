package com.selimsahin.restaurantservice.kafka.producer;

import com.selimsahin.restaurantservice.dto.response.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class RestaurantProducer {

    @Value("${kafka.topic.restaurant-created}")
    private String restaurantCreatedTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishRestaurantCreatedEvent(RestaurantResponse restaurant) {

        kafkaTemplate.send(restaurantCreatedTopic, restaurant);
    }
}
