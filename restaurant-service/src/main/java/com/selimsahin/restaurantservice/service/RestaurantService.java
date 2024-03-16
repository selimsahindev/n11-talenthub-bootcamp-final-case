package com.selimsahin.restaurantservice.service;

import com.selimsahin.restaurantservice.dto.request.RestaurantCreateRequest;
import com.selimsahin.restaurantservice.dto.response.RestaurantResponse;

import java.util.List;

/**
 * @author selimsahindev
 */
public interface RestaurantService {

    RestaurantResponse createRestaurant(RestaurantCreateRequest restaurantCreateRequest);

    List<RestaurantResponse> getAllRestaurants(int page, int limit);

    RestaurantResponse getRestaurantById(Long id);

    void updateAverageRating(Long restaurantId, Double newAverageRating);

    void deleteRestaurant(Long id);
}
