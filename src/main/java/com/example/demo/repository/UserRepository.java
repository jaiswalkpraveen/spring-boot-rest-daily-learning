package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

        // you get CRUD methods automatically: save, findAll, findById, deleteById,
        // existsById etc.

        // Find user by exact name
        Optional<User> findByName(String name);

        // Find users by age greater than
        List<User> findByAgeGreaterThan(Integer age);

        // find users withing age range
        List<User> findByAgeBetween(Integer minAge, Integer maxAge);

        // serach users by name(case-insensitive, partial match)
        List<User> findByNameContaining(String keyword);

        // Search by name or address
        @Query("SELECT u FROM User u WHERE u.name LIKE %:keyword% OR u.address LIKE %:keyword%")
        List<User> searchUsers(@Param("keyword") String keyword);

}