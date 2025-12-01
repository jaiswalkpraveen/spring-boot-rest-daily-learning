package com.example.demo.dto.request;

import com.example.demo.validation.OrderConstraint;

import lombok.Data;

@Data
public class OrderRequest implements OrderConstraint {
    private String product;
    private Double amount;
}
