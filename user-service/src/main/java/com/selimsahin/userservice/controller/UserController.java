package com.selimsahin.userservice.controller;

import com.selimsahin.userservice.dto.response.RestResponse;
import com.selimsahin.userservice.dto.response.UserResponse;
import com.selimsahin.userservice.dto.request.UserCreateRequest;
import com.selimsahin.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author selimsahindev
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<RestResponse<List<UserResponse>>> getAllUsers() {

        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(RestResponse.of(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserResponse>> getUserById(@PathVariable Long id) {

        UserResponse user = userService.getUserById(id);
        return ResponseEntity.ok(RestResponse.of(user));
    }

    @PostMapping
    public ResponseEntity<RestResponse<UserResponse>> createUser(@RequestBody @Valid UserCreateRequest request) {

        UserResponse createdUser = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(RestResponse.of(createdUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UserResponse>> updateUser(@PathVariable Long id, @RequestBody @Valid UserCreateRequest request) {

        UserResponse updatedUser = userService.updateUser(id, request);
        return ResponseEntity.ok(RestResponse.of(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(RestResponse.empty());
    }
}
