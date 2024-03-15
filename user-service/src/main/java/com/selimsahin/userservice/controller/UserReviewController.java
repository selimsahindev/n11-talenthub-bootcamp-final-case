package com.selimsahin.userservice.controller;

import com.selimsahin.userservice.dto.response.UserReviewResponse;
import com.selimsahin.userservice.dto.request.UserReviewCreateRequest;
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

    @GetMapping
    public ResponseEntity<List<UserReviewResponse>> findAll() {
        return ResponseEntity.ok(userReviewService.getAllUserReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReviewResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userReviewService.getUserReviewById(id));
    }

    @GetMapping("/by-user")
    public ResponseEntity<List<UserReviewResponse>> findAllByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(userReviewService.getAllUserReviewsByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<Void> createUserReview(@RequestBody @Valid UserReviewCreateRequest request) {
        userReviewService.createUserReview(request);
        return ResponseEntity.status(201).build();
    }
}
