package com.selimsahin.userservice.service;

import com.selimsahin.userservice.model.dto.UserCreateRequest;
import com.selimsahin.userservice.model.dto.UserResponse;

import java.util.List;

/**
 * @author selimsahindev
 */
public interface UserService {

    UserResponse createUser(UserCreateRequest request);

    List<UserResponse> getUsers(int page, int limit);

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UserCreateRequest userDetails);

    void deleteUser(Long id);
}
