package com.example.spring_boot_zero_to_hero.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateEmployeeRequest {

    @NotBlank(message = "Name can not be empty")
    private String name;

    @Min(value = 1, message = "Salary must be greater than 0")
    private double salary;
}
