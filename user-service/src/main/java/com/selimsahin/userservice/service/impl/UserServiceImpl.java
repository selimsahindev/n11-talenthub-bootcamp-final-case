package com.selimsahin.userservice.service.impl;

import com.selimsahin.userservice.model.dto.LocationDTO;
import com.selimsahin.userservice.model.dto.UserCreateRequest;
import com.selimsahin.userservice.model.dto.UserResponse;
import com.selimsahin.userservice.model.entity.Location;
import com.selimsahin.userservice.model.entity.User;
import com.selimsahin.userservice.repository.UserRepository;
import com.selimsahin.userservice.service.LocationService;
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
    private final LocationService locationService;

    @Override
    public UserResponse createUser(UserCreateRequest request) {

        // Create user
        User user = mapUserCreateRequestToUser(request);

        // Create location
        LocationDTO locationDTO = Location.mapLocationToLocationDTO(request.getLocation());
        Location location = Location.mapLocationDTOToLocation(locationDTO);

        // Set location for user
        user.setLocation(location);

        // Save user
        user = userRepository.save(user);

        return mapToUserResponse(user);
    }

    @Override
    public List<UserResponse> getUsers(int page, int limit) {
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
        return null;
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
        return user;
    }

    // TODO: Implement mapstruct for conversion
    private UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setSurname(user.getSurname());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }
}
