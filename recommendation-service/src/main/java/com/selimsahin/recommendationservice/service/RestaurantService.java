package com.selimsahin.recommendationservice.service;

import com.selimsahin.recommendationservice.document.RestaurantDocument;
import com.selimsahin.recommendationservice.dto.RestaurantDTO;
import com.selimsahin.recommendationservice.dto.request.RestaurantSearchRequest;
import com.selimsahin.recommendationservice.dto.response.RestaurantSearchResponse;

import java.util.List;

/**
 * @author selimsahindev
 */
public interface RestaurantService {

    List<RestaurantSearchResponse> getAllRestaurants();

    List<RestaurantSearchResponse> getRestaurantsByLocationNear(RestaurantSearchRequest request);

    void saveRestaurantDocument(RestaurantDTO restaurant);
}
