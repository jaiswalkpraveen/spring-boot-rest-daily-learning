package com.example.demo.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private int age;
    private String address;
    private String phone;
}
