package com.selimsahin.restaurantservice.controller;

import com.selimsahin.restaurantservice.dto.request.RestaurantCreateRequest;
import com.selimsahin.restaurantservice.dto.response.RestResponse;
import com.selimsahin.restaurantservice.dto.response.RestaurantResponse;
import com.selimsahin.restaurantservice.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author selimsahindev
 */
@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<RestResponse<List<RestaurantResponse>>> getAllRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {

        List<RestaurantResponse> restaurants = restaurantService.getAllRestaurants(page, limit);
        return ResponseEntity.ok(RestResponse.of(restaurants));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<RestaurantResponse>> getRestaurantById(@PathVariable Long id) {

        RestaurantResponse restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(RestResponse.of(restaurant));
    }

    @PostMapping
    public ResponseEntity<RestResponse<RestaurantResponse>> createRestaurant(
            @RequestBody @Valid RestaurantCreateRequest request) {

        RestaurantResponse createdRestaurant = restaurantService.createRestaurant(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(RestResponse.of(createdRestaurant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> deleteRestaurant(@PathVariable Long id) {

        restaurantService.deleteRestaurant(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(RestResponse.empty());
    }
}
