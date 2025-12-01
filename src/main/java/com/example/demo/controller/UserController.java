package com.example.demo.controller;

import java.util.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users") // base URL: /users
public class UserController {

  private final UserService service; // dependency
  // Constructor DI — Spring injects UserRepository bean here

  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping("/all")
  public List<UserResponse> getAllUsers() {
    return service.getAllUsers();
  }

  @GetMapping("/{id}")
  public UserResponse getUser(@Valid @PathVariable int id) {
    return service.getUserById(id);
  }

  @PostMapping("/create")
  public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {
    // you don't need to set the id, it will be auto-generated
    return service.createUser(userRequest);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@Valid @PathVariable int id) {
    service.deleteUser(id);
  }

  @PutMapping("/{id}")
  public UserResponse updateUser(@Valid @PathVariable int id, @Valid @RequestBody UserRequest userRequest) {
    return service.updateUserWithDetails(id, userRequest); // update user with details (including user details) using
                                                           // the UserRequest object
  }

  // @GetMapping("/search/{keyword}")
  // public List<UserResponse> searchUsers(@PathVariable String keyword) {
  // return service.searchUsers(keyword);
  // }

  @GetMapping("/age-range/{minAge}/{maxAge}")
  public List<UserResponse> getUsersByAgeRange(@PathVariable int minAge, @PathVariable int maxAge) {
    return service.getUsersByAgeRange(minAge, maxAge);
  }

}