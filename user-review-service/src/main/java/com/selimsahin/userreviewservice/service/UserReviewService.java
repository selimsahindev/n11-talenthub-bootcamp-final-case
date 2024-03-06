package com.selimsahin.userreviewservice.service;

import com.selimsahin.userreviewservice.dto.UserReviewDetailDTO;
import com.selimsahin.userreviewservice.dto.UserReviewCreateRequest;

import java.util.List;

/**
 * @author selimsahindev
 */
public interface UserReviewService {

    UserReviewDetailDTO create(UserReviewCreateRequest request);

    List<UserReviewDetailDTO> findAll();

    UserReviewDetailDTO findById(Long id);

    List<UserReviewDetailDTO> findAllByUserId(Long userId);
}
