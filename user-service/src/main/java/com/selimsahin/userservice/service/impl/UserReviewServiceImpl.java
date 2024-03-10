package com.selimsahin.userservice.service.impl;

import com.selimsahin.userservice.dto.UserReviewCreateRequest;
import com.selimsahin.userservice.dto.UserReviewDetailDTO;
import com.selimsahin.userservice.entity.UserReview;
import com.selimsahin.userservice.enums.UserRating;
import com.selimsahin.userservice.exception.UserReviewNotFoundException;
import com.selimsahin.userservice.mapper.UserReviewMapper;
import com.selimsahin.userservice.producer.KafkaProducerService;
import com.selimsahin.userservice.repository.UserReviewRepository;
import com.selimsahin.userservice.service.UserReviewService;
import com.selimsahin.userservice.service.UserService;
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
    private final UserService userService;
    private final KafkaProducerService userReviewProducerService;

    @Override
    public UserReviewDetailDTO create(UserReviewCreateRequest request) {

        UserReview userReview = userReviewMapper.mapUserReviewCreateRequestToUserReview(request);
        userReview = userReviewRepository.save(userReview);

        UserReviewDetailDTO userReviewDetailDTO = userReviewMapper.mapUserReviewToUserReviewDetailDTO(userReview);

        // Update restaurant average rating.
        updateRestaurantAverageRating(userReview.getRestaurantId());

        return userReviewDetailDTO;
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

        // Check if the user exists. Throws UserNotFoundException if the user does not exist.
        userService.getUserById(userId);

        List<UserReview> userReviews = userReviewRepository.findAllByUserId(userId);

        return userReviews.stream()
                .map(userReviewMapper::mapUserReviewToUserReviewDetailDTO)
                .toList();
    }

    private void updateRestaurantAverageRating(Long restaurantId) {

        Double averageRating = userReviewRepository.findAverageRatingByRestaurantId(restaurantId);

        System.out.println("Average rating: " + averageRating);

        // Publish restaurant average rating updated event to Kafka.
        userReviewProducerService.publishAverageRatingCalculatedEvent(restaurantId, averageRating);
    }
}
