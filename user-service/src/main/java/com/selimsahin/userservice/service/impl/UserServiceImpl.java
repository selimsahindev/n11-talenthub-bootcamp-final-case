package com.selimsahin.userservice.service.impl;

import com.selimsahin.userservice.dto.Location;
import com.selimsahin.userservice.dto.UserCreateRequest;
import com.selimsahin.userservice.dto.UserResponse;
import com.selimsahin.userservice.entity.User;
import com.selimsahin.userservice.repository.UserRepository;
import com.selimsahin.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author selimsahindev
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserCreateRequest request) {

        User user = mapUserCreateRequestToUser(request);

        System.out.println("User: " + userRepository.save(user));

        return mapToUserResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponse> getAllUsers(int page, int limit) {
        Page<User> userPage = userRepository.findAll(PageRequest.of(page, limit));

        return userPage.getContent().stream()
                .map(this::mapToUserResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        return mapToUserResponse(userOptional.get());
    }

    @Override
    public UserResponse updateUser(Long id, UserCreateRequest userDetails) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOptional.get();
        user.setName(userDetails.getName());
        user.setSurname(userDetails.getSurname());
        user.setEmail(userDetails.getEmail());

        userRepository.save(user);

        return mapToUserResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // TODO: Implement mapstruct for conversion
    private User mapUserCreateRequestToUser(UserCreateRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setLocation(request.getLocation());
        user.setStatus(request.getStatus());
        return user;
    }

    // TODO: Implement mapstruct for conversion
    private UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setSurname(user.getSurname());
        userResponse.setEmail(user.getEmail());
        userResponse.setLocation(user.getLocation());
        return userResponse;
    }
}
