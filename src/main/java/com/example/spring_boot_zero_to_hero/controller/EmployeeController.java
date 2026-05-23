package com.example.spring_boot_zero_to_hero.controller;

import com.example.spring_boot_zero_to_hero.dto.CreateEmployeeRequest;
import com.example.spring_boot_zero_to_hero.dto.EmployeeResponse;
import com.example.spring_boot_zero_to_hero.dto.UpdateEmployeeRequest;
import com.example.spring_boot_zero_to_hero.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// We ahve to add the @CrossOrigin annotation to allow cross-origin requests from the frontend application. This is necessary because the frontend and backend are running on different ports (e.g., frontend on port 3000 and backend on port 8080). By adding @CrossOrigin, we enable the frontend to communicate with the backend without encountering CORS (Cross-Origin Resource Sharing) issues.
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from the frontend running on port 3000
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    // Create a Employee
    @PostMapping
    public EmployeeResponse createEmployee(
            @Valid @RequestBody CreateEmployeeRequest request) {
        return employeeService.createEmployee(request);
        /*
        🌟 @RequestBody

        Converts:

        JSON → Java object

        🌟 @Valid

        VERY IMPORTANT.

        Triggers:

        DTO validations

        like:

        @NotBlank
        @Min
         */
    }

    @GetMapping
    public List<EmployeeResponse> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @PutMapping("{id}")
    public EmployeeResponse updateEmployee (
            @PathVariable int id, @Valid @RequestBody UpdateEmployeeRequest request) {
        return employeeService.updateEmployee(id, request);
    }

    @DeleteMapping("{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return "Employee with id " + id + " has been deleted.";
    }
}
