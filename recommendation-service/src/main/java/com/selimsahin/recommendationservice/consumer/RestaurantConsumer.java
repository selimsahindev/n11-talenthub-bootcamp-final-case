package com.selimsahin.recommendationservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selimsahin.recommendationservice.dto.RestaurantDTO;
import com.selimsahin.recommendationservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class RestaurantConsumer {

    private final RestaurantService restaurantSearchService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topic.restaurant-created}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, String> payload) throws JsonProcessingException {

        RestaurantDTO restaurantDto = objectMapper.readValue(payload.value(), RestaurantDTO.class);
        restaurantSearchService.saveRestaurantDocument(restaurantDto);
    }
}
