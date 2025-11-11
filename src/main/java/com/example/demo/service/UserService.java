package com.example.demo.service;
import org.springframework.stereotype.Service;



import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User getUserById(int id) {
        return repo.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return repo.save(user);
    }

    public User updateUser(int id, User user) {
        if (repo.existsById(id)) {
            user.setId(id);
            return repo.save(user);
        } else {
            throw new RuntimeException("User with ID " + id + " not found");
        }
    }

    public void deleteUser(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            throw new RuntimeException("User with ID " + id + " not found");
        }
    }
}
