package com.example.demo.dto.request;

import lombok.Data;
import com.example.demo.validation.UserConstraints;

@Data
public class UserRequest implements UserConstraints {
    private String name;
    private Integer age;
    private String address;
    private String phone;
}
