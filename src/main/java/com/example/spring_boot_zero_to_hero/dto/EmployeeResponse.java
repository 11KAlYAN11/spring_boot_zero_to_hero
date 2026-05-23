package com.example.spring_boot_zero_to_hero.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeResponse {
    private int id;
    private String name;
    private double salary;
}
