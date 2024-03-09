package com.selimsahin.userservice.service.impl;

import com.selimsahin.userservice.dto.UserReviewCreateRequest;
import com.selimsahin.userservice.dto.UserReviewDetailDTO;
import com.selimsahin.userservice.entity.UserReview;
import com.selimsahin.userservice.exception.UserReviewNotFoundException;
import com.selimsahin.userservice.mapper.UserReviewMapper;
import com.selimsahin.userservice.repository.UserReviewRepository;
import com.selimsahin.userservice.service.UserReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class UserReviewServiceImpl implements UserReviewService {

    private final UserReviewRepository userReviewRepository;
    private final UserReviewMapper userReviewMapper;

    @Override
    public UserReviewDetailDTO create(UserReviewCreateRequest request) {

        UserReview userReview = userReviewMapper.mapUserReviewCreateRequestToUserReview(request);
        userReview = userReviewRepository.save(userReview);

        return userReviewMapper.mapUserReviewToUserReviewDetailDTO(userReview);
    }

    @Override
    public List<UserReviewDetailDTO> findAll() {

        return userReviewRepository.findAll().stream()
                .map(userReviewMapper::mapUserReviewToUserReviewDetailDTO)
                .toList();
    }

    @Override
    public UserReviewDetailDTO findById(Long id) {

        Optional<UserReview> userReviewOptional = userReviewRepository.findById(id);

        if (userReviewOptional.isEmpty()) {
            throw new UserReviewNotFoundException("User review not found with id: " + id);
        }

        return userReviewMapper.mapUserReviewToUserReviewDetailDTO(userReviewOptional.get());
    }

    @Override
    public List<UserReviewDetailDTO> findAllByUserId(Long userId) {

        Optional<List<UserReview>> userReviewsOptional = userReviewRepository.findAllByUserId(userId);

        if (userReviewsOptional.isEmpty() || userReviewsOptional.get().isEmpty()) {
            throw new UserReviewNotFoundException("User review not found with user id: " + userId);
        }

        return userReviewsOptional.get().stream()
                .map(userReviewMapper::mapUserReviewToUserReviewDetailDTO)
                .toList();
    }
}
