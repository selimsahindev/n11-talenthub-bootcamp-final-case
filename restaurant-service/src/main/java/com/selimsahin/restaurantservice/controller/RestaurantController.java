package com.selimsahin.restaurantservice.controller;

import com.selimsahin.restaurantservice.dto.request.RestaurantCreateRequest;
import com.selimsahin.restaurantservice.dto.response.RestResponse;
import com.selimsahin.restaurantservice.dto.response.RestaurantResponse;
import com.selimsahin.restaurantservice.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get All Restaurants", description = "Retrieves a paginated list of all restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of restaurants retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantResponse.class)))
    })
    @GetMapping
    public ResponseEntity<RestResponse<List<RestaurantResponse>>> getAllRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {

        List<RestaurantResponse> restaurants = restaurantService.getAllRestaurants(page, limit);
        return ResponseEntity.ok(RestResponse.of(restaurants));
    }

    @Operation(summary = "Get Restaurant by Id", description = "Retrieves a restaurant by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantResponse.class))),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<RestaurantResponse>> getRestaurantById(@PathVariable Long id) {

        RestaurantResponse restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(RestResponse.of(restaurant));
    }

    @Operation(summary = "Create Restaurant", description = "Creates a new restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping
    public ResponseEntity<RestResponse<RestaurantResponse>> createRestaurant(
            @RequestBody @Valid RestaurantCreateRequest request) {

        RestaurantResponse createdRestaurant = restaurantService.createRestaurant(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(RestResponse.of(createdRestaurant));
    }

    @Operation(summary = "Delete Restaurant", description = "Deletes a restaurant by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Restaurant deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Restaurant not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> deleteRestaurant(@PathVariable Long id) {

        restaurantService.deleteRestaurant(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(RestResponse.empty());
    }
}
