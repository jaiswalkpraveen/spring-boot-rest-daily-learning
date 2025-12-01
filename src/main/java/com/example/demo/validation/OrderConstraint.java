package com.example.demo.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface OrderConstraint {

    @NotBlank(message = "Product name is required")
    String getProduct();

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be a positive number")
    Double getAmount();

}
