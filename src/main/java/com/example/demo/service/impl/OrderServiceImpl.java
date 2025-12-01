package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.request.OrderRequest;
import com.example.demo.dto.response.OrderResponse;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, UserMapper userMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public OrderResponse createOrder(int userId, OrderRequest orderRequest) {
        // find the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // create order
        Order order = userMapper.toOrderEntity(orderRequest);

        // Set the relationship
        user.addOrder(order); // this set both sides of the relationship

        Order saved = orderRepository.save(order);

        return userMapper.toOrderResponse(saved);
    }

    @Override
    public List<OrderResponse> getUserOrders(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : user.getOrders()) {
            orderResponses.add(userMapper.toOrderResponse(order));
        }
        return orderResponses;
    }

    @Override
    public OrderResponse getOrderById(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return userMapper.toOrderResponse(order);
    }

    @Override
    public void deleteOrder(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }
}
