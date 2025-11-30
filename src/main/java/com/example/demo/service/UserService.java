package com.example.demo.service;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);

    UserResponse getUserById(int id);

    List<UserResponse> getAllUsers();

    UserResponse updateUserWithDetails(int id, UserRequest userRequest);

    void deleteUser(int id);

    List<UserResponse> searchUsers(String keyword);

    List<UserResponse> getUsersByAgeRange(int minAge, int maxAge);

}
