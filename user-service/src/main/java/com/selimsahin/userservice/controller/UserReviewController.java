package com.selimsahin.userservice.controller;

import com.selimsahin.userservice.dto.response.RestResponse;
import com.selimsahin.userservice.dto.response.UserReviewResponse;
import com.selimsahin.userservice.dto.request.UserReviewCreateRequest;
import com.selimsahin.userservice.service.UserReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/v1/user-reviews")
@RequiredArgsConstructor
@Tag(name = "User Review Controller", description = "Provides operations for managing user reviews")
public class UserReviewController {

    private final UserReviewService userReviewService;

    @GetMapping
    @Operation(summary = "Get all user reviews", description = "Returns a list of all user reviews")
    public ResponseEntity<RestResponse<List<UserReviewResponse>>> getAllUserReviews() {

        List<UserReviewResponse> userReviews = userReviewService.getAllUserReviews();
        return ResponseEntity.ok(RestResponse.of(userReviews));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user review by id", description = "Returns a user review by id")
    public ResponseEntity<RestResponse<UserReviewResponse>> getUserReviewById(@PathVariable Long id) {

        UserReviewResponse userReview = userReviewService.getUserReviewById(id);
        return ResponseEntity.ok(RestResponse.of(userReview));
    }

    @GetMapping("/by-user")
    @Operation(summary = "Get all user reviews by user id", description = "Returns a list of all user reviews by user id")
    public ResponseEntity<RestResponse<List<UserReviewResponse>>> getAllUserReviewsByUserId(@RequestParam Long userId) {

        List<UserReviewResponse> userReviews = userReviewService.getAllUserReviewsByUserId(userId);
        return ResponseEntity.ok(RestResponse.of(userReviews));
    }

    @PostMapping
    @Operation(summary = "Create user review", description = "Creates a new user review")
    public ResponseEntity<RestResponse<UserReviewResponse>> createUserReview(
            @RequestBody @Valid UserReviewCreateRequest request) {

        UserReviewResponse createdUserReview = userReviewService.createUserReview(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(RestResponse.of(createdUserReview));
    }
}
