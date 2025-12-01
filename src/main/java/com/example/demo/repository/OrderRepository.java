package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUserId(int userId);

    List<Order> findByAmountGreaterThan(double amount);

    List<Order> findByProductContaining(String product);

}
