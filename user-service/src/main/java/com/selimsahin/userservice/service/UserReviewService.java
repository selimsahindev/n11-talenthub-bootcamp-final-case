package com.selimsahin.userservice.service;

import com.selimsahin.userservice.dto.UserReviewCreateRequest;
import com.selimsahin.userservice.dto.UserReviewDetailDTO;

import java.util.List;

/**
 * @author selimsahindev
 */
public interface UserReviewService {

    UserReviewDetailDTO createUserReview(UserReviewCreateRequest request);

    List<UserReviewDetailDTO> getAllUserReviews();

    UserReviewDetailDTO getUserReviewById(Long id);

    List<UserReviewDetailDTO> getAllUserReviewsByUserId(Long userId);
}
