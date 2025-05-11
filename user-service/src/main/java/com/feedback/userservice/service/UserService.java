package com.feedback.userservice.service;

import java.util.List;

import com.feedback.userservice.dtos.UserDto;
import com.feedback.userservice.entity.User;

public interface UserService {

    // Add a new user
    User createUser(UserDto userDto);

    // Get all users
    List<User> getAllUsers();

    // Get user by ID
    UserDto getUserById(int userId);

    // Update user
    User updateUser(int userId, UserDto userDto);

    // Delete user
    void deleteUser(int userId);
}
