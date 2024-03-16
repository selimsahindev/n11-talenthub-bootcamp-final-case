package com.selimsahin.userservice.service;

import com.selimsahin.userservice.dto.request.UserCreateRequest;
import com.selimsahin.userservice.dto.response.UserResponse;

import java.util.List;

/**
 * @author selimsahindev
 */
public interface UserService {

    UserResponse createUser(UserCreateRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UserCreateRequest userDetails);

    void deleteUser(Long id);
}
