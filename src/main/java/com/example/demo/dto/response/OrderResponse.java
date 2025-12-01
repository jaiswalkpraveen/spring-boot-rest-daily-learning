package com.example.demo.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderResponse {
    private int id;
    private String product;
    private double amount;
    private LocalDateTime orderDate;
    private int userId;
    private String userName;
}
