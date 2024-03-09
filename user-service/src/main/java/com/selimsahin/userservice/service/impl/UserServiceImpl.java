package com.selimsahin.userservice.service.impl;

import com.selimsahin.userservice.dto.UserCreateRequest;
import com.selimsahin.userservice.dto.UserResponse;
import com.selimsahin.userservice.entity.User;
import com.selimsahin.userservice.exception.UserNotFoundException;
import com.selimsahin.userservice.mapper.UserMapper;
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
    private final UserMapper userMapper;

    @Override
    public void createUser(UserCreateRequest request) {

        User user = userMapper.mapUserCreateRequestToUser(request);
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> getAllUsers(int page, int limit) {

        Page<User> userPage = userRepository.findAll(PageRequest.of(page, limit));

        return userPage.getContent().stream()
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
    public UserResponse updateUser(Long id, UserCreateRequest userDetails) {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found with id: " + id);
        }

        User user = userOptional.get();
        user.setName(userDetails.getName());
        user.setSurname(userDetails.getSurname());
        user.setEmail(userDetails.getEmail());
        userRepository.save(user);

        return userMapper.mapToUserResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
