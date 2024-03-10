package com.selimsahin.restaurantservice.producer;

import com.selimsahin.restaurantservice.dto.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class RestaurantProducerService {

    @Value("${kafka.topic.restaurant-created}")
    private String restaurantCreatedTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishRestaurantCreatedEvent(RestaurantResponse restaurant) {

        kafkaTemplate.send(restaurantCreatedTopic, restaurant);
    }
}
