package com.selimsahin.recommendationservice.controller;

import com.selimsahin.recommendationservice.document.RestaurantDocument;
import com.selimsahin.recommendationservice.dto.request.RestaurantSearchRequest;
import com.selimsahin.recommendationservice.dto.response.RestResponse;
import com.selimsahin.recommendationservice.dto.response.RestaurantSearchResponse;
import com.selimsahin.recommendationservice.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Restaurant Search", description = "Restaurant Search API")
public class RestaurantSearchController {

    private final RestaurantService restaurantService;

    @Operation(summary = "Get All Restaurants", description = "Retrieves a list of all restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of restaurants retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantSearchResponse.class)))
    })
    @GetMapping
    public ResponseEntity<RestResponse<List<RestaurantSearchResponse>>> findAll() {

        List<RestaurantSearchResponse> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(RestResponse.of(restaurants));
    }

    @Operation(summary = "Search Restaurants by Location (Near)", description = "Searches for restaurants near a given location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of restaurants near the specified location retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RestaurantSearchResponse.class),
                    examples = @ExampleObject(value = "{ \"location\": \"39.90180157645193,32.85794341887077\" }",
                            description = "Search restaurants near the specified location. Example location is somewhere near Kuğulu Park, Ankara.")))
    })
    @GetMapping("/by-location-near")
    public ResponseEntity<RestResponse<List<RestaurantSearchResponse>>> searchByLocationNear(
            @ModelAttribute RestaurantSearchRequest request) {

        List<RestaurantSearchResponse> restaurants = restaurantService.getRestaurantsByLocationNear(request);
        return ResponseEntity.ok(RestResponse.of(restaurants));
    }
}
