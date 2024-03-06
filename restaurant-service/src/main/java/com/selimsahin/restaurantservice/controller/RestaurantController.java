package com.selimsahin.restaurantservice.controller;

import com.selimsahin.restaurantservice.dto.RestaurantCreateRequest;
import com.selimsahin.restaurantservice.dto.RestaurantResponse;
import com.selimsahin.restaurantservice.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurants(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int limit
    ) {
        List<RestaurantResponse> restaurants = restaurantService.getAllRestaurants(page, limit);
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> getRestaurantById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createRestaurant(@RequestBody @Valid RestaurantCreateRequest request) {
        restaurantService.createRestaurant(request);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}
