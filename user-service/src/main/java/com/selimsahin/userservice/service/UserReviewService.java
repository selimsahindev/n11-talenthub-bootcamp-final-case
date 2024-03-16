package com.selimsahin.userservice.service;

import com.selimsahin.userservice.dto.response.UserReviewResponse;
import com.selimsahin.userservice.dto.request.UserReviewCreateRequest;

import java.util.List;

/**
 * @author selimsahindev
 */
public interface UserReviewService {

    UserReviewResponse createUserReview(UserReviewCreateRequest request);

    List<UserReviewResponse> getAllUserReviews();

    UserReviewResponse getUserReviewById(Long id);

    List<UserReviewResponse> getAllUserReviewsByUserId(Long userId);
}
