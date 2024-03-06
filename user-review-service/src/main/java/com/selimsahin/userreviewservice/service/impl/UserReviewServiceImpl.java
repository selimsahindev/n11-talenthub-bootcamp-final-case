package com.selimsahin.userreviewservice.service.impl;

import com.selimsahin.userreviewservice.dto.UserReviewCreateRequest;
import com.selimsahin.userreviewservice.dto.UserReviewDetailDTO;
import com.selimsahin.userreviewservice.entity.UserReview;
import com.selimsahin.userreviewservice.enums.UserRating;
import com.selimsahin.userreviewservice.exception.UserReviewNotFoundException;
import com.selimsahin.userreviewservice.repository.UserReviewRepository;
import com.selimsahin.userreviewservice.service.UserReviewService;
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

    @Override
    public UserReviewDetailDTO create(UserReviewCreateRequest request) {

        UserReview userReview = mapUserReviewCreateRequestToUserReview(request);
        userReview = userReviewRepository.save(userReview);

        return mapUserReviewToUserReviewDetailDTO(userReview);
    }

    @Override
    public List<UserReviewDetailDTO> findAll() {

        return userReviewRepository.findAll().stream()
                .map(this::mapUserReviewToUserReviewDetailDTO)
                .toList();
    }

    @Override
    public UserReviewDetailDTO findById(Long id) {

        Optional<UserReview> userReviewOptional = userReviewRepository.findById(id);

        if (userReviewOptional.isEmpty()) {
            throw new UserReviewNotFoundException("User review not found with ID: " + id);
        }

        return mapUserReviewToUserReviewDetailDTO(userReviewOptional.get());
    }

    @Override
    public List<UserReviewDetailDTO> findAllByUserId(Long userId) {

        Optional<List<UserReview>> userReviewsOptional = userReviewRepository.findAllByUserId(userId);

        if (userReviewsOptional.isEmpty() || userReviewsOptional.get().isEmpty()) {
            throw new UserReviewNotFoundException("User review not found with user ID: " + userId);
        }

        return userReviewsOptional.get().stream()
                .map(this::mapUserReviewToUserReviewDetailDTO)
                .toList();
    }

    // TODO: Use MapStruct and get rid of these methods
    private UserReview mapUserReviewCreateRequestToUserReview(UserReviewCreateRequest request) {
        UserReview userReview = new UserReview();
        userReview.setUserId(request.userId());
        userReview.setRate(UserRating.fromValue(request.rate()));
        userReview.setComment(request.comment());
        return userReview;
    }
    private UserReviewDetailDTO mapUserReviewToUserReviewDetailDTO(UserReview userReview) {
        UserReviewDetailDTO userReviewDetailDTO = new UserReviewDetailDTO();
        userReviewDetailDTO.setId(userReview.getId());
        userReviewDetailDTO.setUserId(userReview.getUserId());
        userReviewDetailDTO.setRate(userReview.getRate());
        userReviewDetailDTO.setComment(userReview.getComment());
        return userReviewDetailDTO;
    }
}
