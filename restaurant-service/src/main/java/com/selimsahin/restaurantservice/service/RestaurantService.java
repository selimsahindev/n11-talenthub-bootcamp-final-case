package com.selimsahin.restaurantservice.service;

import com.selimsahin.restaurantservice.dto.RestaurantCreateRequest;
import com.selimsahin.restaurantservice.dto.RestaurantResponse;

import java.util.List;

/**
 * @author selimsahindev
 */
public interface RestaurantService {

    void createRestaurant(RestaurantCreateRequest restaurantCreateRequest);

    List<RestaurantResponse> getAllRestaurants(int page, int limit);

    RestaurantResponse getRestaurantById(Long id);

    void deleteRestaurant(Long id);
}
