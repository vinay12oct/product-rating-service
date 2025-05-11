package com.feedback.userservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.userservice.dtos.UserDto;
import com.feedback.userservice.entity.User;
import com.feedback.userservice.exception.BadRequestException;
import com.feedback.userservice.exception.ResourceNotFoundException;
import com.feedback.userservice.repository.UserRepo;
import com.feedback.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepository;

    // Add new user
    @Override
    public User createUser(UserDto userDto) {
        try {
            validateUserDto(userDto);

            User user = new User();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setAbout(userDto.getAbout());

            return userRepository.save(user);
        } catch (Exception ex) {
            throw new BadRequestException("Error creating user: " + ex.getMessage());
        }
    }

    // Get all users
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    @Override
    public UserDto getUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
        
        UserDto userDto = new UserDto();
        
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAbout(user.getAbout());
        
        return userDto;
        
    }

    // Update user
    @Override
    public User updateUser(int userId, UserDto userDto) {
        try {
            validateUserDto(userDto);

            User existingUser = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));

            existingUser.setName(userDto.getName());
            existingUser.setEmail(userDto.getEmail());
            existingUser.setAbout(userDto.getAbout());

            return userRepository.save(existingUser);
        } catch (Exception ex) {
            throw new BadRequestException("Error updating user: " + ex.getMessage());
        }
    }

    // Delete user
    @Override
    public void deleteUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
        userRepository.delete(user);
    }

    // Internal validation method
    private void validateUserDto(UserDto userDto) {
        if (userDto.getName() == null || userDto.getName().trim().isEmpty()) {
            throw new BadRequestException("Name must not be empty.");
        }

        if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
            throw new BadRequestException("Email must not be empty.");
        }

        if (!userDto.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
            throw new BadRequestException("Invalid email format.");
        }
    }
}
