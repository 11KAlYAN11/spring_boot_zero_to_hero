package com.example.spring_boot_zero_to_hero.service;

import com.example.spring_boot_zero_to_hero.dto.CreateEmployeeRequest;
import com.example.spring_boot_zero_to_hero.dto.EmployeeResponse;
import com.example.spring_boot_zero_to_hero.dto.UpdateEmployeeRequest;
import com.example.spring_boot_zero_to_hero.entity.Employee;
import com.example.spring_boot_zero_to_hero.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service               // marks this as a Spring-managed service bean
@RequiredArgsConstructor  // Lombok auto-generates a constructor that injects the repository
public class EmployeeService {

    private final EmployeeRepository repository;

    // ─── CREATE ───────────────────────────────────────────────────────────────

    public EmployeeResponse createEmployee(CreateEmployeeRequest request) {

        // Step 1: convert the incoming request (DTO) → database object (Entity)
        Employee employee = mapToEntity(request);

        // Step 2: save to the database — JPA returns the saved record (now has an ID)
        Employee savedEmployee = repository.save(employee);

        // Step 3: convert the saved entity → response DTO to send back to the client
        return mapToResponse(savedEmployee);
    }

    // ─── HELPER METHODS ───────────────────────────────────────────────────────

    // Converts request DTO → Employee entity
    // (we don't set ID here — the database auto-generates it)
    private Employee mapToEntity(CreateEmployeeRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setSalary(request.getSalary());
        return employee;
    }

    // Converts saved Employee entity → EmployeeResponse DTO
    // (now includes the auto-generated ID from the database)
    private EmployeeResponse mapToResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getSalary()
        );
    }

    // Fetch Employees
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = repository.findAll();

        return employees.stream()
                .map(this::mapToResponse) // convert each Employee to EmployeeResponse
                .toList(); // collect results into a List
    }

    // Update Employees
    public EmployeeResponse updateEmployee(int id, UpdateEmployeeRequest request) {
        Employee employee = repository.findById(id)
                .orElseThrow( () -> new RuntimeException("Employee not found with id: " + id) );

        employee.setName(request.getName());
        employee.setSalary((request.getSalary()));

        Employee updatedEmployee = repository.save(employee);

        return mapToResponse(updatedEmployee);
    }

    public void deleteEmployee(int id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        repository.delete(employee);
    }
}
