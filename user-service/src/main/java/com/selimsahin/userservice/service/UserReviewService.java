package com.selimsahin.userservice.service;

import com.selimsahin.userservice.dto.UserReviewCreateRequest;
import com.selimsahin.userservice.dto.UserReviewDetailDTO;

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
