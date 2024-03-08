package com.selimsahin.recommendationservice.controller;

import com.selimsahin.recommendationservice.dto.RestaurantSearchRequest;
import com.selimsahin.recommendationservice.dto.RestaurantSearchResponse;
import com.selimsahin.recommendationservice.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author selimsahindev
 */
@RestController
@RequestMapping("/api/v1/search/restaurants")
@RequiredArgsConstructor
public class RestaurantSearchController {

    private final RestaurantService restaurantService;

    @PostMapping("/by-location")
    public ResponseEntity<List<RestaurantSearchResponse>> searchByLocation(
            @RequestBody RestaurantSearchRequest request) {

        return ResponseEntity.ok(restaurantService.getRestaurantsByLocation(request));
    }
}
