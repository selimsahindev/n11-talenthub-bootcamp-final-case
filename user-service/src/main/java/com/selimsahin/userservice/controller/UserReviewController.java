package com.selimsahin.userservice.controller;

import com.selimsahin.userservice.client.RestaurantServiceClient;
import com.selimsahin.userservice.dto.RestaurantInfoDTO;
import com.selimsahin.userservice.dto.UserReviewCreateRequest;
import com.selimsahin.userservice.dto.UserReviewDetailDTO;
import com.selimsahin.userservice.exception.RestaurantNotFoundException;
import com.selimsahin.userservice.service.UserReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author selimsahindev
 */
@RestController
@RequestMapping("/api/v1/user-reviews")
@RequiredArgsConstructor
public class UserReviewController {

    private final UserReviewService userReviewService;
    private final RestaurantServiceClient restaurantServiceClient;

    @GetMapping
    public ResponseEntity<List<UserReviewDetailDTO>> findAll() {
        return ResponseEntity.ok(userReviewService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReviewDetailDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userReviewService.findById(id));
    }

    @GetMapping("/by-user")
    public ResponseEntity<List<UserReviewDetailDTO>> findAllByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(userReviewService.findAllByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Void> createUserReview(@RequestBody @Valid UserReviewCreateRequest request) {

        ResponseEntity<RestaurantInfoDTO> response = restaurantServiceClient.getRestaurantById(request.restaurantId());

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RestaurantNotFoundException("Restaurant not found with id: " + request.restaurantId());
        }

        userReviewService.create(request);
        return ResponseEntity.status(201).build();
    }
}
