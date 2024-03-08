package com.selimsahin.recommendationservice.service.impl;

import com.selimsahin.recommendationservice.document.RestaurantDocument;
import com.selimsahin.recommendationservice.dto.RestaurantSearchRequest;
import com.selimsahin.recommendationservice.dto.RestaurantSearchResponse;
import com.selimsahin.recommendationservice.repository.RestaurantRepository;
import com.selimsahin.recommendationservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository productRepository;

    public List<RestaurantSearchResponse> getRestaurantsByLocation(RestaurantSearchRequest request) {

        List<RestaurantDocument> restaurants =
                productRepository.findByLocation(request.getLatitude(), request.getLongitude());

        return restaurants.stream()
                .map(this::convertToResponse)
                .toList();
    }

    private RestaurantSearchResponse convertToResponse(RestaurantDocument document) {
        RestaurantSearchResponse response = new RestaurantSearchResponse();
        response.setId(document.getId());
        response.setName(document.getName());
        return response;
    }
}