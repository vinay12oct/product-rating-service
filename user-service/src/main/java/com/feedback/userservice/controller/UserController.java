package com.feedback.userservice.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.feedback.userservice.dtos.UserDto;
import com.feedback.userservice.entity.User;
import com.feedback.userservice.payload.ApiResponse;
import com.feedback.userservice.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserDto userDto) {
        try {
            User savedUser = userService.createUser(userDto);
            ApiResponse response = new ApiResponse(
                "User created successfully",
                true,
                HttpStatus.CREATED.value(),
                savedUser,
                LocalDateTime.now()
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception ex) {
            ApiResponse response = new ApiResponse(
                "Failed to create user: " + ex.getMessage(),
                false,
                HttpStatus.BAD_REQUEST.value(),
                null,
                LocalDateTime.now()
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            ApiResponse response = new ApiResponse(
                "Users fetched successfully",
                true,
                HttpStatus.OK.value(),
                users,
                LocalDateTime.now()
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            ApiResponse response = new ApiResponse(
                "Failed to fetch users: " + ex.getMessage(),
                false,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                null,
                LocalDateTime.now()
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable int id) {
        try {
            UserDto userDto = userService.getUserById(id);
            ApiResponse response = new ApiResponse(
                "User fetched successfully",
                true,
                HttpStatus.OK.value(),
                userDto,
                LocalDateTime.now()
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            ApiResponse response = new ApiResponse(
                "User not found: " + ex.getMessage(),
                false,
                HttpStatus.NOT_FOUND.value(),
                null,
                LocalDateTime.now()
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        try {
            User updatedUser = userService.updateUser(id, userDto);
            ApiResponse response = new ApiResponse(
                "User updated successfully",
                true,
                HttpStatus.OK.value(),
                updatedUser,
                LocalDateTime.now()
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            ApiResponse response = new ApiResponse(
                "Failed to update user: " + ex.getMessage(),
                false,
                HttpStatus.BAD_REQUEST.value(),
                null,
                LocalDateTime.now()
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id) {
        try {
            userService.deleteUser(id);
            ApiResponse response = new ApiResponse(
                "User deleted successfully",
                true,
                HttpStatus.OK.value(),
                null,
                LocalDateTime.now()
            );
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            ApiResponse response = new ApiResponse(
                "Failed to delete user: " + ex.getMessage(),
                false,
                HttpStatus.NOT_FOUND.value(),
                null,
                LocalDateTime.now()
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
