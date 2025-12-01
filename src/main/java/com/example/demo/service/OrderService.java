package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.request.OrderRequest;
import com.example.demo.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(int userId, OrderRequest orderRequest);

    List<OrderResponse> getUserOrders(int userId);

    OrderResponse getOrderById(int orderId);

    void deleteOrder(int orderId);
}