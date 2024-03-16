package com.selimsahin.userservice.service;

import com.selimsahin.userservice.client.RestaurantServiceClient;
import com.selimsahin.userservice.dto.Location;
import com.selimsahin.userservice.dto.RestaurantInfoDTO;
import com.selimsahin.userservice.dto.request.UserReviewCreateRequest;
import com.selimsahin.userservice.dto.response.UserResponse;
import com.selimsahin.userservice.dto.response.UserReviewResponse;
import com.selimsahin.userservice.entity.UserReview;
import com.selimsahin.userservice.enums.UserRating;
import com.selimsahin.userservice.exception.UserNotFoundException;
import com.selimsahin.userservice.exception.UserReviewNotFoundException;
import com.selimsahin.userservice.mapper.UserReviewMapper;
import com.selimsahin.userservice.producer.KafkaProducer;
import com.selimsahin.userservice.repository.UserReviewRepository;
import com.selimsahin.userservice.service.impl.UserReviewServiceImpl;
import com.selimsahin.userservice.util.AppLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author selimsahindev
 */
class UserReviewServiceTest {

    @InjectMocks
    private UserReviewServiceImpl userReviewService;

    @Mock
    private UserService userService;

    @Mock
    private UserReviewRepository userReviewRepository;

    @Mock
    private UserReviewMapper userReviewMapper;

    @Mock
    private AppLogger appLogger;

    @Mock
    private KafkaProducer kafkaProducer;

    @Mock
    private RestaurantServiceClient restaurantServiceClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUserReviews_returnsListOfAllUserReviews() {

        // Given
        List<UserReview> mockReviews = createMockUserReviews();
        List<UserReviewResponse> mockResponses = createMockUserReviewResponses();

        // Mock the calls
        when(userReviewRepository.findAll()).thenReturn(mockReviews);
        when(userReviewMapper.mapUserReviewsToUserReviewResponses(mockReviews)).thenReturn(mockResponses);

        // When
        List<UserReviewResponse> result = userReviewService.getAllUserReviews();

        // Then
        assertEquals(mockResponses, result);
    }

    @Test
    void testGetAllUserReviews_returnsEmptyListForNoUserReviews() {

        // Mock the calls
        when(userReviewRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        List<UserReviewResponse> result = userReviewService.getAllUserReviews();

        // Then
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetUserReviewById_returnsUserForExistingId() {

        // Given
        UserReview mockReview = createMockUserReviews().getFirst();
        UserReviewResponse mockResponse = createMockUserReviewResponses().getFirst();

        // Mock the calls
        when(userReviewRepository.findById(1L)).thenReturn(Optional.of(mockReview));
        when(userReviewMapper.mapUserReviewToUserReviewResponse(mockReview)).thenReturn(mockResponse);

        // When
        UserReviewResponse result = userReviewService.getUserReviewById(1L);

        // Then
        assertEquals(mockResponse, result);
    }

    @Test
    void testGetUserReviewById_throwsUserReviewNotFoundExceptionForNonExistentId() {

        // Mock the calls
        when(userReviewRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Expect an exception to be thrown
        assertThrows(UserReviewNotFoundException.class, () -> userReviewService.getUserReviewById(1L));
    }

    @Test
    void testGetUserReviewsByUserId_returnsListOfUserReviewsForExistingUserId() {

            // Given
            List<UserReview> mockReviews = createMockUserReviews();
            List<UserResponse> mockUserResponses = createMockUserResponses();
            List<UserReviewResponse> mockUserReviewResponses = createMockUserReviewResponses();

            // Mock the calls
            when(userService.getUserById(anyLong())).thenReturn(mockUserResponses.getFirst());
            when(userReviewRepository.findAllByUserId(anyLong())).thenReturn(mockReviews);
            when(userReviewMapper.mapUserReviewsToUserReviewResponses(mockReviews)).thenReturn(mockUserReviewResponses);

            // When
            List<UserReviewResponse> result = userReviewService.getAllUserReviewsByUserId(1L);

            // Then
            assertEquals(mockUserReviewResponses, result);
    }

    @Test
    void testGetUserReviewsByUserId_throwsUserNotFoundExceptionForNonExistentUserId() {

        // Mock the calls
        when(userService.getUserById(anyLong())).thenThrow(new UserNotFoundException("User not found with id: " + 1L));

        // Expect an exception to be thrown
        assertThrows(UserNotFoundException.class, () -> userReviewService.getAllUserReviewsByUserId(1L));
    }

    @Test
    void testGetUserReviewsByUserId_returnsEmptyListForNoUserReviews() {

        // Given
        List<UserResponse> mockUserResponses = createMockUserResponses();

        // Mock the calls
        when(userService.getUserById(anyLong())).thenReturn(mockUserResponses.getFirst());
        when(userReviewRepository.findAllByUserId(anyLong())).thenReturn(Collections.emptyList());

        // When
        List<UserReviewResponse> result = userReviewService.getAllUserReviewsByUserId(1L);

        // Then
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testCreateUserReview_successfullyCreatesReviewForValidRequest() {

        // Given
        UserReviewCreateRequest mockRequest = createMockUserReviewCreateRequest();
        UserResponse mockUserResponse = createMockUserResponses().getFirst();
        UserReview mockUserReview = createMockUserReview();
        UserReviewResponse userReviewResponse = createMockUserReviewResponses().getFirst();
        RestaurantInfoDTO mockRestaurantInfoDto = createMockRestaurantInfoDTO();

        // Mock calls
        when(userService.getUserById(anyLong()))
                .thenReturn(mockUserResponse);
        when(restaurantServiceClient.getRestaurantById(anyLong()))
                .thenReturn(ResponseEntity.ok(mockRestaurantInfoDto));
        when(userReviewRepository.save(any(UserReview.class)))
                .thenReturn(mockUserReview);
        when(userReviewMapper.mapUserReviewToUserReviewResponse(any(UserReview.class)))
                .thenReturn(userReviewResponse);
        when(userReviewMapper.mapUserReviewCreateRequestToUserReview(any(UserReviewCreateRequest.class)))
                .thenReturn(mockUserReview);

        // When
        UserReviewResponse result = userReviewService.createUserReview(mockRequest);

        // Then
        assertNotNull(result);
        assertEquals(mockRequest.userId(), result.userId());
        assertEquals(mockRequest.restaurantId(), result.restaurantId());
        assertEquals(mockRequest.rate(), result.rate().getValue());
        assertEquals(mockRequest.comment(), result.comment());
    }

    private UserReview createMockUserReview() {

        return new UserReview(
                1L,
                1L,
                1L,
                UserRating.FIVE,
                "Great food and service!");
    }

    private static UserReviewCreateRequest createMockUserReviewCreateRequest() {

        return UserReviewCreateRequest.builder()
                .userId(1L)
                .restaurantId(1L)
                .rate(5)
                .comment("Great food and service!")
                .build();
    }

    private List<UserReview> createMockUserReviews() {
        return List.of(
                new UserReview(
                        1L,
                        1L,
                        1L,
                        UserRating.FIVE,
                        "Great food and service!"
                )
        );
    }

    private List<UserReviewResponse> createMockUserReviewResponses() {
        return List.of(
                UserReviewResponse.builder()
                        .id(1L)
                        .userId(1L)
                        .restaurantId(1L)
                        .rate(UserRating.FIVE)
                        .comment("Great food and service!")
                        .build()
        );
    }

    private List<UserResponse> createMockUserResponses() {

        return List.of(UserResponse.builder()
                .id(1L)
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .location(new Location(38.386349, 26.791997))
                .build());
    }

    private RestaurantInfoDTO createMockRestaurantInfoDTO() {

        return RestaurantInfoDTO.builder()
                .id(1L)
                .name("Restaurant")
                .averageRating(4.5)
                .build();
    }
}