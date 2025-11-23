package com.example.demo.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Shared validation contract for User related DTOs and entities.
 * The interface defines getters with Bean Validation annotations.
 * Classes that implement this interface (e.g., UserRequest, User) will
 * automatically inherit the constraints.
 */
public interface UserConstraints {

    @NotBlank(message = "Name is required")
    String getName();

    @NotNull(message = "Age is required")
    @Positive(message = "Age must be a positive number")
    Integer getAge();

    @NotBlank(message = "Address is required")
    String getAddress();

    @NotBlank(message = "Phone is required")
    String getPhone();
}