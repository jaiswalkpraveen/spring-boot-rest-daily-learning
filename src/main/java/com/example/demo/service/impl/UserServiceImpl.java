package com.example.demo.service.impl;

import com.example.demo.service.UserService;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.entity.UserDetails;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        // Create user entity from request
        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());

        // Create user details entity from request
        UserDetails userDetails = new UserDetails();
        userDetails.setAddress(userRequest.getAddress());
        userDetails.setPhone(userRequest.getPhone());

        // Set user details in user
        user.setUserDetails(userDetails);
        userDetails.setUser(user);

        // Save parent --> child auto saves because of cascade
        User savedUser = userRepository.save(user);

        // Covert Entity -> Response DTO manually
        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setName(savedUser.getName());
        userResponse.setAge(savedUser.getAge());
        userResponse.setAddress(savedUser.getUserDetails().getAddress());
        userResponse.setPhone(savedUser.getUserDetails().getPhone());
        return userResponse;

    }

    @Override
    public UserResponse getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setAge(user.getAge());
        userResponse.setAddress(user.getUserDetails().getAddress());
        userResponse.setPhone(user.getUserDetails().getPhone());
        return userResponse;
    }

    @Override
    public UserResponse updateUserWithDetails(int id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        user.getUserDetails().setAddress(userRequest.getAddress());
        user.getUserDetails().setPhone(userRequest.getPhone());
        User updatedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(updatedUser.getId());
        userResponse.setName(updatedUser.getName());
        userResponse.setAge(updatedUser.getAge());
        userResponse.setAddress(updatedUser.getUserDetails().getAddress());
        userResponse.setPhone(updatedUser.getUserDetails().getPhone());
        return userResponse;
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
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setName(user.getName());
            userResponse.setAge(user.getAge());
            userResponse.setAddress(user.getUserDetails().getAddress());
            userResponse.setPhone(user.getUserDetails().getPhone());
            userResponses.add(userResponse);
        }
        return userResponses;
    }
}
