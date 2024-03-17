package com.selimsahin.userservice.controller;

import com.selimsahin.userservice.dto.response.RestResponse;
import com.selimsahin.userservice.dto.response.UserResponse;
import com.selimsahin.userservice.dto.request.UserCreateRequest;
import com.selimsahin.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User Controller", description = "Provides operations for managing users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get All Users", description = "Retrieves a list of all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)))
    })
    @GetMapping
    public ResponseEntity<RestResponse<List<UserResponse>>> getAllUsers() {

        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(RestResponse.of(users));
    }

    @Operation(summary = "Get User by Id", description = "Retrieves a user by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserResponse>> getUserById(@PathVariable Long id) {

        UserResponse user = userService.getUserById(id);
        return ResponseEntity.ok(RestResponse.of(user));
    }

    @Operation(summary = "Create User", description = "Creates a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    @PostMapping
    public ResponseEntity<RestResponse<UserResponse>> createUser(@RequestBody @Valid UserCreateRequest request) {

        UserResponse createdUser = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(RestResponse.of(createdUser));
    }

    @Operation(summary = "Update User", description = "Updates an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UserResponse>> updateUser(@PathVariable Long id, @RequestBody @Valid UserCreateRequest request) {

        UserResponse updatedUser = userService.updateUser(id, request);
        return ResponseEntity.ok(RestResponse.of(updatedUser));
    }

    @Operation(summary = "Delete User", description = "Deletes a user by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<Void>> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(RestResponse.empty());
    }
}
