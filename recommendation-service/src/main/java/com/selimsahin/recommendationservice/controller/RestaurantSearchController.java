package com.selimsahin.recommendationservice.controller;

import com.selimsahin.recommendationservice.document.RestaurantDocument;
import com.selimsahin.recommendationservice.dto.RestaurantSearchRequest;
import com.selimsahin.recommendationservice.dto.RestaurantSearchResponse;
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
    public ResponseEntity<List<RestaurantDocument>> findAll() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/by-location-near")
    public ResponseEntity<List<RestaurantSearchResponse>> searchByLocationNear(
            @ModelAttribute RestaurantSearchRequest request) {

        return ResponseEntity.ok(restaurantService.getRestaurantsByLocationNear(request));
    }
}
