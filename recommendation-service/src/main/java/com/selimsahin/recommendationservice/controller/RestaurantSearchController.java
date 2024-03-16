package com.selimsahin.recommendationservice.controller;

import com.selimsahin.recommendationservice.document.RestaurantDocument;
import com.selimsahin.recommendationservice.dto.request.RestaurantSearchRequest;
import com.selimsahin.recommendationservice.dto.response.RestResponse;
import com.selimsahin.recommendationservice.dto.response.RestaurantSearchResponse;
import com.selimsahin.recommendationservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author selimsahindev
 */
@RestController
@RequestMapping("/api/v1/search/restaurants")
@RequiredArgsConstructor
public class RestaurantSearchController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<RestResponse<List<RestaurantSearchResponse>>> findAll() {

        List<RestaurantSearchResponse> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(RestResponse.of(restaurants));
    }

    @GetMapping("/by-location-near")
    public ResponseEntity<RestResponse<List<RestaurantSearchResponse>>> searchByLocationNear(
            @ModelAttribute RestaurantSearchRequest request) {

        List<RestaurantSearchResponse> restaurants = restaurantService.getRestaurantsByLocationNear(request);
        return ResponseEntity.ok(RestResponse.of(restaurants));
    }
}
