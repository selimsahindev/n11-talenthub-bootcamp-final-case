package com.selimsahin.userservice.service;

import com.selimsahin.userservice.dto.Location;
import com.selimsahin.userservice.dto.request.UserCreateRequest;
import com.selimsahin.userservice.dto.response.UserResponse;
import com.selimsahin.userservice.entity.User;
import com.selimsahin.userservice.enums.UserStatus;
import com.selimsahin.userservice.exception.UserNotFoundException;
import com.selimsahin.userservice.mapper.UserMapper;
import com.selimsahin.userservice.repository.UserRepository;
import com.selimsahin.userservice.service.impl.UserServiceImpl;
import com.selimsahin.userservice.util.AppLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author selimsahindev
 */
class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private AppLogger appLogger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_successfullyCreatesUserWithValidRequest() {

        // Given
        UserCreateRequest mockRequest = createMockUserCreateRequest();
        User mockUser = createMockUsers().getFirst();
        UserResponse mockResponse = createMockUserResponses().getFirst();

        // Mock the calls
        when(userMapper.mapUserCreateRequestToUser(any(UserCreateRequest.class))).thenReturn(mockUser);
        when(userMapper.mapToUserResponse(any(User.class))).thenReturn(mockResponse);
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        // Setup logging mock
        doNothing().when(appLogger).logInfo(anyString(), anyString());

        // When
        UserResponse responseDto = userService.createUser(mockRequest);

        // Then
        assertNotNull(responseDto);
        assertEquals(mockUser.getId(), responseDto.id());

        // We can optionally verify logging behavior
        verify(appLogger).logInfo(anyString(), anyString());
    }

    @Test
    void testCreateUser_throwsExceptionOnMappingError() {

        // Given
        UserCreateRequest mockRequest = createMockUserCreateRequest();

        // Mock the call
        when(userMapper.mapUserCreateRequestToUser(any(UserCreateRequest.class)))
                .thenThrow(new RuntimeException("Mapping error"));

        // Expect an exception to be thrown
        assertThrows(RuntimeException.class, () -> userService.createUser(mockRequest));

        // Verify no repository interaction (optional)
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    void testCreateUser_throwsExceptionOnRepositoryError() {

        // Given
        UserCreateRequest mockRequest = createMockUserCreateRequest();
        User mockUser = createMockUsers().getFirst();

        // Mock the calls
        when(userMapper.mapUserCreateRequestToUser(any(UserCreateRequest.class))).thenReturn(mockUser);
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("Repository error"));

        // Call the method and assert exception
        assertThrows(RuntimeException.class, () -> userService.createUser(mockRequest));
    }

    @Test
    void testGetAllUsers_returnsListOfAllUsers() {

        // Given
        List<User> mockUsers = createMockUsers();
        List<UserResponse> mockUserResponses = createMockUserResponses();

        // Mock the calls
        when(userRepository.findAll()).thenReturn(mockUsers);
        when(userMapper.mapToUserResponse(any(User.class))).thenReturn(mockUserResponses.getFirst());

        // When
        List<UserResponse> result = userService.getAllUsers();

        // Then
        assertEquals(mockUserResponses, result);
    }

    @Test
    void testGetAllUsers_returnsEmptyListForNoUsers() {

        // Mock the calls
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        List<UserResponse> result = userService.getAllUsers();

        // Then
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetUserById_returnsUserForExistingId() {

        // Given
        User mockUser = createMockUsers().getFirst();
        UserResponse mockUserResponse = createMockUserResponses().getFirst();

        // Mock the calls
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        when(userMapper.mapToUserResponse(mockUser)).thenReturn(mockUserResponse);

        // When
        UserResponse result = userService.getUserById(1L);

        // Then
        assertEquals(mockUserResponse, result);
    }

    @Test
    void testGetUserById_throwsUserNotFoundExceptionForNonExistentId() {

        // Mock the calls
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Expect an exception to be thrown
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    void testUpdateUser_successfullyUpdatesUserWithValidRequestAndId() {

        // Given
        UserResponse mockUpdatedUserResponse = createMockUserResponses().getFirst();
        UserCreateRequest mockRequest = createMockUserCreateRequest();
        User mockUser = createMockUsers().getFirst();
        Long userId = 1L;

        // Mock calls
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(userRepository.save(any(User.class))).thenReturn(mockUser);
        when(userMapper.mapToUserResponse(mockUser)).thenReturn(mockUpdatedUserResponse);
        doNothing().when(appLogger).logInfo(anyString(), anyString());

        // When
        UserResponse result = userService.updateUser(userId, mockRequest);

        // Then
        assertEquals(mockUpdatedUserResponse, result);
        verify(userRepository).save(mockUser); // Ensure user was saved
        verify(appLogger).logInfo(anyString(), anyString()); // Optionally verify logging
    }

    @Test
    void testUpdateUser_throwsNotFoundExceptionForNonExistentId() {

        // Given
        UserCreateRequest mockRequest = createMockUserCreateRequest();
        Long nonExistentId = 2L;

        // Mock call
        when(userRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Expect exception
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(nonExistentId, mockRequest));
    }

    @Test
    void testDeleteUser_successfullyDeletesExistingUser() {

        // Mock the call (optional)
        doNothing().when(userRepository).deleteById(anyLong());

        // Call the method
        userService.deleteUser(1L);

        // We can optionally verify logging behavior
        verify(appLogger).logInfo(anyString(), anyString());
    }

    private List<User> createMockUsers() {

        return List.of(new User(
                1L,
                "John",
                "Doe",
                "john.doe@example.com",
                new Location(38.386349, 26.791997),
                UserStatus.ACTIVE)
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


    private UserCreateRequest createMockUserCreateRequest() {

        return UserCreateRequest.builder()
                .name("John")
                .surname("Doe")
                .email("john.doe@example.com")
                .location(new Location(38.386349, 26.791997))
                .status(UserStatus.ACTIVE)
                .build();
    }

}