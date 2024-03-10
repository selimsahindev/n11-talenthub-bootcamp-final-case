package com.selimsahin.restaurantservice.service.impl;

import com.selimsahin.restaurantservice.dto.RestaurantCreateRequest;
import com.selimsahin.restaurantservice.dto.RestaurantResponse;
import com.selimsahin.restaurantservice.entity.Restaurant;
import com.selimsahin.restaurantservice.exception.RestaurantNotFoundException;
import com.selimsahin.restaurantservice.mapper.RestaurantMapper;
import com.selimsahin.restaurantservice.producer.LogProducerService;
import com.selimsahin.restaurantservice.producer.RestaurantEventProducer;
import com.selimsahin.restaurantservice.repository.RestaurantRepository;
import com.selimsahin.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final RestaurantEventProducer restaurantEventProducer;
    private final LogProducerService logProducerService;

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
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}
