package com.selimsahin.recommendationservice.service;

import com.selimsahin.recommendationservice.dto.RestaurantDTO;
import com.selimsahin.recommendationservice.dto.RestaurantSearchRequest;
import com.selimsahin.recommendationservice.dto.RestaurantSearchResponse;

import java.util.List;

/**
 * @author selimsahindev
 */
public interface RestaurantService {

    List<RestaurantSearchResponse> getRestaurantsByLocation(RestaurantSearchRequest request);

    void saveRestaurantDocument(RestaurantDTO restaurant);
}
