package com.selimsahin.userservice.service.impl;

import com.selimsahin.userservice.dto.request.UserCreateRequest;
import com.selimsahin.userservice.dto.response.UserResponse;
import com.selimsahin.userservice.entity.User;
import com.selimsahin.userservice.exception.UserNotFoundException;
import com.selimsahin.userservice.mapper.UserMapper;
import com.selimsahin.userservice.repository.UserRepository;
import com.selimsahin.userservice.service.UserService;
import com.selimsahin.userservice.util.AppLogger;
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
    private final UserMapper userMapper;
    private final AppLogger appLogger;

    @Override
    public UserResponse createUser(UserCreateRequest request) {

        User user = userMapper.mapUserCreateRequestToUser(request);
        user = userRepository.save(user);

        appLogger.logInfo("User created","User created with id: " + user.getId());

        return userMapper.mapToUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(userMapper::mapToUserResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        return userMapper.mapToUserResponse(userOptional.get());
    }

    @Override
    public UserResponse updateUser(Long id, UserCreateRequest request) {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        User user = userOptional.get();
        user.setName(request.name());
        user.setSurname(request.surname());
        user.setEmail(request.email());
        userRepository.save(user);

        appLogger.logInfo("User updated","User updated with id: " + user.getId());

        return userMapper.mapToUserResponse(user);
    }

    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);

        appLogger.logInfo("User deleted","User deleted with id: " + id);
    }
}
