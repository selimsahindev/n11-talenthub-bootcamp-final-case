package com.selimsahin.userservice.service.impl;

import com.selimsahin.userservice.client.RestaurantServiceClient;
import com.selimsahin.userservice.dto.RestaurantInfoDTO;
import com.selimsahin.userservice.dto.response.UserReviewResponse;
import com.selimsahin.userservice.dto.response.UserResponse;
import com.selimsahin.userservice.dto.request.UserReviewCreateRequest;
import com.selimsahin.userservice.entity.UserReview;
import com.selimsahin.userservice.exception.RestaurantNotFoundException;
import com.selimsahin.userservice.exception.UserNotFoundException;
import com.selimsahin.userservice.exception.UserReviewNotFoundException;
import com.selimsahin.userservice.mapper.UserReviewMapper;
import com.selimsahin.userservice.producer.KafkaProducer;
import com.selimsahin.userservice.repository.UserReviewRepository;
import com.selimsahin.userservice.service.UserReviewService;
import com.selimsahin.userservice.service.UserService;
import com.selimsahin.userservice.util.AppLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor

public class UserReviewServiceImpl implements UserReviewService {

    private final RestaurantServiceClient restaurantServiceClient;
    private final UserReviewRepository userReviewRepository;
    private final UserReviewMapper userReviewMapper;
    private final KafkaProducer kafkaProducer;
    private final UserService userService;
    private final AppLogger appLogger;

    @Override
    public UserReviewResponse createUserReview(UserReviewCreateRequest request) {

        validateUser(request.userId());
        validateRestaurant(request.restaurantId());

        UserReview userReview = userReviewMapper.mapUserReviewCreateRequestToUserReview(request);
        userReview = userReviewRepository.save(userReview);
        UserReviewResponse userReviewDetailDTO = userReviewMapper.mapUserReviewToUserReviewResponse(userReview);

        // Update restaurant average rating and publish event to Kafka.
        updateRestaurantAverageRating(userReview.getRestaurantId());

        // Send info log to Kafka
        appLogger.logInfo("User review created","User review created with id: " + userReview.getId());

        return userReviewDetailDTO;
    }

    @Override
    public List<UserReviewResponse> getAllUserReviews() {

        List<UserReview> userReviews = userReviewRepository.findAll();
        return userReviewMapper.mapUserReviewsToUserReviewResponses(userReviews);
    }

    @Override
    public UserReviewResponse getUserReviewById(Long id) {

        Optional<UserReview> userReviewOptional = userReviewRepository.findById(id);

        if (userReviewOptional.isEmpty()) {
            throw new UserReviewNotFoundException("User review not found with id: " + id);
        }

        return userReviewMapper.mapUserReviewToUserReviewResponse(userReviewOptional.get());
    }

    @Override
    public List<UserReviewResponse> getAllUserReviewsByUserId(Long userId) {

        // Check if the user exists.
        userService.getUserById(userId);

        List<UserReview> userReviews = userReviewRepository.findAllByUserId(userId);

        return userReviewMapper.mapUserReviewsToUserReviewResponses(userReviews);
    }

    private void updateRestaurantAverageRating(Long restaurantId) {

        Double averageRating = userReviewRepository.findAverageRatingByRestaurantId(restaurantId);

        // Publish restaurant average rating updated event to Kafka.
        kafkaProducer.publishAverageRatingCalculatedEvent(restaurantId, averageRating);

        appLogger.logInfo("Restaurant average rating updated","Restaurant average rating updated with id: " + restaurantId + " and average rating: " + averageRating);
    }

    private void validateUser(Long userId) {
        UserResponse userResponse = userService.getUserById(userId);

        if (userResponse == null) {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
    }

    private void validateRestaurant(Long restaurantId) {
        ResponseEntity<RestaurantInfoDTO> response = restaurantServiceClient.getRestaurantById(restaurantId);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RestaurantNotFoundException("Restaurant not found with id: " + restaurantId);
        }
    }
}
