package com.selimsahin.userservice.controller;

import com.selimsahin.userservice.dto.response.RestResponse;
import com.selimsahin.userservice.dto.response.UserReviewResponse;
import com.selimsahin.userservice.dto.request.UserReviewCreateRequest;
import com.selimsahin.userservice.service.UserReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get all user reviews", description = "Returns a list of all user reviews")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User review retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserReviewResponse.class))),
            @ApiResponse(responseCode = "404", description = "User review not found")
    })
    public ResponseEntity<RestResponse<UserReviewResponse>> getUserReviewById(@PathVariable Long id) {

        UserReviewResponse userReview = userReviewService.getUserReviewById(id);
        return ResponseEntity.ok(RestResponse.of(userReview));
    }

    @GetMapping("/by-user")
    @Operation(summary = "Get all user reviews by user id", description = "Returns a list of all user reviews by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of user reviews by user id retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserReviewResponse.class)))
    })
    public ResponseEntity<RestResponse<List<UserReviewResponse>>> getAllUserReviewsByUserId(@RequestParam Long userId) {

        List<UserReviewResponse> userReviews = userReviewService.getAllUserReviewsByUserId(userId);
        return ResponseEntity.ok(RestResponse.of(userReviews));
    }

    @PostMapping
    @Operation(summary = "Create user review", description = "Creates a new user review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User review created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserReviewResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public ResponseEntity<RestResponse<UserReviewResponse>> createUserReview(
            @RequestBody @Valid UserReviewCreateRequest request) {

        UserReviewResponse createdUserReview = userReviewService.createUserReview(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(RestResponse.of(createdUserReview));
    }
}
