package com.selimsahin.restaurantservice.service.impl;

import com.selimsahin.restaurantservice.dto.RestaurantCreateRequest;
import com.selimsahin.restaurantservice.dto.RestaurantResponse;
import com.selimsahin.restaurantservice.entity.Restaurant;
import com.selimsahin.restaurantservice.exception.RestaurantNotFoundException;
import com.selimsahin.restaurantservice.kafka.RestaurantEventProducer;
import com.selimsahin.restaurantservice.repository.RestaurantRepository;
import com.selimsahin.restaurantservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantEventProducer restaurantEventProducer;

    @Override
    public void createRestaurant(RestaurantCreateRequest restaurantCreateRequest) {

        Restaurant restaurant = mapRestaurantCreateRequestToRestaurant(restaurantCreateRequest);
        RestaurantResponse restaurantResponse = mapRestaurantToRestaurantResponse(restaurant);

        // Send to Kafka
        restaurantEventProducer.publishRestaurantCreatedEvent(restaurantResponse);

        restaurantRepository.save(restaurant);
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants(int page, int limit) {

        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream()
                .map(this::mapRestaurantToRestaurantResponse)
                .toList();
    }

    @Override
    public RestaurantResponse getRestaurantById(Long id) {

        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);

        if (restaurantOptional.isEmpty()) {
            throw new RestaurantNotFoundException("Restaurant not found with id: " + id);
        }

        return mapRestaurantToRestaurantResponse(restaurantOptional.get());
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    // Todo: Remove these methods and implement MapStruct!
    private Restaurant mapRestaurantCreateRequestToRestaurant(RestaurantCreateRequest request) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.name());
        restaurant.setLocation(request.location());
        return restaurant;
    }

    private RestaurantResponse mapRestaurantToRestaurantResponse(Restaurant restaurant) {
        RestaurantResponse response = new RestaurantResponse();
        response.setId(restaurant.getId());
        response.setName(restaurant.getName());
        response.setLocation(restaurant.getLocation());
        return response;
    }
}
