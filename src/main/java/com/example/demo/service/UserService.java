package com.example.demo.service;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import java.util.List;


public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    UserResponse getUserById(int id);
    List<UserResponse> getAllUsers();
    UserResponse updateUserWithDetails(int id, UserRequest userRequest);
    void deleteUser(int id);   
}

