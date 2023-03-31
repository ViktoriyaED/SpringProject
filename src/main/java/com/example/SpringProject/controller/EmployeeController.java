package com.example.SpringProject.controller;

import com.example.SpringProject.exception.EmployeeNotFoundException;
import com.example.SpringProject.repository.EmployeeRepository;
import com.example.SpringProject.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PostMapping("/")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(emp -> {
                    emp.setName(employee.getName());
                    return employeeRepository.save(emp);
                })
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
}
