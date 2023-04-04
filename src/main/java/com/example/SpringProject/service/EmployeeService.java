package com.example.SpringProject.service;

import com.example.SpringProject.entity.Employee;
import com.example.SpringProject.exception.EmployeeNotFoundException;
import com.example.SpringProject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getList() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee edit(Employee employee) {
        employeeRepository.findById(employee.getId()).orElseThrow(() -> new EmployeeNotFoundException(employee.getId()));
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee) {
       Employee storedEmployee = employeeRepository.findById(employee.getId()).orElseThrow(() -> new EmployeeNotFoundException(employee.getId()));

        boolean hasChanges = false;

        if (employee.getName() != null && !Objects.equals(storedEmployee.getName(), employee.getName())) {
            storedEmployee.setName(employee.getName());
            hasChanges = true;
        }
        if (employee.getJobTitle() != null && !Objects.equals(storedEmployee.getJobTitle(), employee.getJobTitle())) {
            storedEmployee.setJobTitle(employee.getJobTitle());
            hasChanges = true;
        }

        if (hasChanges) {
            return employeeRepository.save(storedEmployee);
        } else {
            return storedEmployee;
        }

    }

    public void remove(Long id) {
        employeeRepository.deleteById(id);
    }
}
