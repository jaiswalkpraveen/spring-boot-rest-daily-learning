package com.example.demo.service.impl;

import com.example.demo.service.UserService;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.entity.UserDetails;
import com.example.demo.mapper.UserMapper;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        // Convert DTO -> Entity
        User user = userMapper.toEntity(userRequest);
        UserDetails userDetails = userMapper.toUserDetails(userRequest);

        // Set bidirectional relationship
        user.setUserDetails(userDetails);
        userDetails.setUser(user);

        // Save and convert back to DTO
        User saved = userRepository.save(user);
        return userMapper.toUserResponse(saved);
    }

    @Override
    public UserResponse getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUserWithDetails(int id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUserFromRequest(userRequest, user);
        userMapper.updateUserDetailsFromRequest(userRequest, user.getUserDetails());
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(userMapper.toUserResponse(user));
        }
        return userResponses;
    }

    // // serach users by keywords
    // public List<UserResponse> searchUsers(String keyword) {
    // List<User> users = userRepository.searchUsers(keyword);
    // List<UserResponse> userResponses = new ArrayList<>();
    // for (User user : users) {
    // userResponses.add(userMapper.toUserResponse(user));
    // }
    // return userResponses;
    // }

    public List<UserResponse> getUsersByAgeRange(int minAge, int maxAge) {
        List<User> users = userRepository.findByAgeBetween(minAge, maxAge);
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(userMapper.toUserResponse(user));
        }
        return userResponses;
    }
}
