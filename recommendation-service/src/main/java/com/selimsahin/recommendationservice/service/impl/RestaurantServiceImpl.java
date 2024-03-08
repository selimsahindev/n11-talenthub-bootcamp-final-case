package com.selimsahin.recommendationservice.service.impl;

import com.selimsahin.recommendationservice.document.RestaurantDocument;
import com.selimsahin.recommendationservice.dto.RestaurantCreatedEventDTO;
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

    private final RestaurantRepository restaurantRepository;

    public List<RestaurantSearchResponse> getRestaurantsByLocation(RestaurantSearchRequest request) {

        List<RestaurantDocument> restaurants =
                restaurantRepository.findByLocation(request.getLatitude(), request.getLongitude());

        return restaurants.stream()
                .map(this::convertToResponse)
                .toList();
    }

    public void saveRestaurantDocument(RestaurantCreatedEventDTO dto) {

        RestaurantDocument document = convertToRestaurantDocument(dto);

        System.out.println("Saving restaurant document: " + document);

        restaurantRepository.save(document);
    }

    // Todo: Use MapStruct
    private RestaurantSearchResponse convertToResponse(RestaurantDocument document) {
        RestaurantSearchResponse response = new RestaurantSearchResponse();
        response.setId(document.getId());
        response.setName(document.getName());
        return response;
    }
    private RestaurantDocument convertToRestaurantDocument(RestaurantCreatedEventDTO dto) {
        RestaurantDocument document = new RestaurantDocument();
        document.setId(dto.id());
        document.setName(dto.name());
        document.setLatitude(dto.location().latitude());
        document.setLongitude(dto.location().longitude());
        return document;
    }
}