package com.example.SpringProject.controller;

import com.example.SpringProject.entity.Employee;
import com.example.SpringProject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public List<Employee> getList() {
        return employeeService.getList();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeService.getById(id);
    }

    @PostMapping("/create")
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PutMapping("/edit")
    public Employee edit(@RequestBody Employee employee) {
        return employeeService.edit(employee);
    }

    @PatchMapping("/update")
    public Employee update(@RequestBody Employee employee){
        return employeeService.update(employee);
    }

    @DeleteMapping("/delete/{id}")
    public void remove(@PathVariable Long id) {
        employeeService.remove(id);
    }
}
