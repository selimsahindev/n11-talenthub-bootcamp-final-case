package com.selimsahin.restaurantservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.selimsahin.restaurantservice.dto.AverageRatingUpdateDTO;
import com.selimsahin.restaurantservice.dto.RestaurantCreateRequest;
import com.selimsahin.restaurantservice.dto.RestaurantResponse;
import com.selimsahin.restaurantservice.entity.Restaurant;
import com.selimsahin.restaurantservice.event.AverageRatingUpdatedEvent;
import com.selimsahin.restaurantservice.exception.RestaurantNotFoundException;
import com.selimsahin.restaurantservice.mapper.RestaurantMapper;
import com.selimsahin.restaurantservice.kafka.producer.LogProducer;
import com.selimsahin.restaurantservice.kafka.producer.RestaurantProducer;
import com.selimsahin.restaurantservice.repository.RestaurantRepository;
import com.selimsahin.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final RestaurantProducer restaurantEventProducer;
    private final LogProducer logProducerService;
    private final ObjectMapper objectMapper;

    @Override
    public void createRestaurant(RestaurantCreateRequest restaurantCreateRequest) {

        Restaurant restaurant = restaurantMapper.mapCreateRequestToRestaurant(restaurantCreateRequest);
        restaurant = restaurantRepository.save(restaurant);

        RestaurantResponse restaurantResponse = restaurantMapper.mapRestaurantToRestaurantResponse(restaurant);

        // Publish restaurant created event to Kafka
        restaurantEventProducer.publishRestaurantCreatedEvent(restaurantResponse);

        // Publish log to Kafka
        logProducerService.publishInfoLog("Restaurant created: " + restaurantResponse);
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants(int page, int limit) {

        return restaurantRepository.findAll()
                .stream()
                .map(restaurantMapper::mapRestaurantToRestaurantResponse)
                .toList();
    }

    @Override
    public RestaurantResponse getRestaurantById(Long id) {

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);

        if (restaurantOptional.isEmpty()) {
            throw new RestaurantNotFoundException("Restaurant not found with id: " + id);
        }

        return restaurantMapper.mapRestaurantToRestaurantResponse(restaurantOptional.get());
    }

    @Override
    public void updateAverageRating(Long restaurantId, Double newAverageRating) {

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);

        if (restaurantOptional.isEmpty()) {
            throw new RestaurantNotFoundException("Restaurant not found with id: " + restaurantId);
        }

        Restaurant restaurant = restaurantOptional.get();
        restaurant.setAverageRating(newAverageRating);
        restaurantRepository.save(restaurant);

        // Todo: publish average rating updated event to Kafka for recommendation service

        // Log the update
        logProducerService.publishInfoLog("Average rating updated for Restaurant id: " + restaurantId + " to: " + newAverageRating);
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    @EventListener
    private void handleAverageRatingUpdatedEvent(AverageRatingUpdatedEvent event) throws JsonProcessingException {

        AverageRatingUpdateDTO dto = objectMapper.readValue(event.payload(), AverageRatingUpdateDTO.class);
        updateAverageRating(dto.restaurantId(), dto.averageRating());
    }
}
