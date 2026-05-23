package com.example.spring_boot_zero_to_hero.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateEmployeeRequest {
    // for first will say can't be empty

    @NotBlank(message = "Name is Empty")
    private String name;

    @DecimalMin(value = "0.1", message = "Salary must be greater than 0")
    private double salary;
}
