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
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/users") // base URL: /users
public class HelloController {

    // private Map<Integer, User> users = new HashMap<>();
    private final UserRepository repo;  // dependency

    // Constructor DI — Spring injects UserRepository bean here
    public HelloController(UserRepository repo) {
        this.repo = repo;
    }


    @GetMapping("/all")
    public Collection<User> getAllUsers(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

  @PostMapping("/create")
   public String createUser(@RequestBody User user){
    // you don't need to set the id, it will be auto-generated
    repo.save(user);
    return "User added successfully!"; 
  }

  @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable int id){
    repo.deleteById(id);
    return "User deleted successfully!";
  }

  @PutMapping("/{id}")
  public User updateUser(@PathVariable int id, @RequestBody User user){
      // Check if user exists before updating
      if (repo.existsById(id)) {
          repo.save(user);
          return user;
      } else {
          throw new RuntimeException("User with ID " + id + " not found");
      }
  }
}