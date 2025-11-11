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

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users") // base URL: /users
public class HelloController {

    // private Map<Integer, User> users = new HashMap<>();
    // private final UserRepository repo;  // dependency
    private final UserService service;  // dependency
    // Constructor DI — Spring injects UserRepository bean here
    public HelloController(UserService service) {
        this.service = service;
    }


    @GetMapping("/all")
    public Collection<User> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return service.getUserById(id);
    }

  @PostMapping("/create")
   public User createUser(@RequestBody User user){
    // you don't need to set the id, it will be auto-generated
    return service.createUser(user);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable int id){
    service.deleteUser(id);
  }

  @PutMapping("/{id}")
  public User updateUser(@PathVariable int id, @RequestBody User user){
    return service.updateUser(id, user);
  }
}