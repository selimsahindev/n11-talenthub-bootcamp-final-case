package com.selimsahin.userservice.controller;

import com.selimsahin.userservice.dto.response.RestResponse;
import com.selimsahin.userservice.dto.response.UserReviewResponse;
import com.selimsahin.userservice.dto.request.UserReviewCreateRequest;
import com.selimsahin.userservice.service.UserReviewService;
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
public class UserReviewController {

    private final UserReviewService userReviewService;

    @GetMapping
    public ResponseEntity<RestResponse<List<UserReviewResponse>>> getAllUserReviews() {

        List<UserReviewResponse> userReviews = userReviewService.getAllUserReviews();
        return ResponseEntity.ok(RestResponse.of(userReviews));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserReviewResponse>> getUserReviewById(@PathVariable Long id) {

        UserReviewResponse userReview = userReviewService.getUserReviewById(id);
        return ResponseEntity.ok(RestResponse.of(userReview));
    }

    @GetMapping("/by-user")
    public ResponseEntity<RestResponse<List<UserReviewResponse>>> getAllUserReviewsByUserId(@RequestParam Long userId) {

        List<UserReviewResponse> userReviews = userReviewService.getAllUserReviewsByUserId(userId);
        return ResponseEntity.ok(RestResponse.of(userReviews));
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserReviewResponse>> createUserReview(
            @RequestBody @Valid UserReviewCreateRequest request) {

        UserReviewResponse createdUserReview = userReviewService.createUserReview(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(RestResponse.of(createdUserReview));
    }
}
