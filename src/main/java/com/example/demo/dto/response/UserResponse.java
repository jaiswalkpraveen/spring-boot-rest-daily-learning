package com.example.demo.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private int id;
    private String name;
    private int age;
    private String address;
    private String phone;
}
