package com.ebix.employee_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebix.employee_service.entity.Employee;
import com.ebix.employee_service.exception.DuplicateResourceException;
import com.ebix.employee_service.exception.ResourceNotFoundException;
import com.ebix.employee_service.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Employee> getAll() {
        return repo.findAll();
    }

    @Override
    public Employee getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }
 
    @Override
    public Employee create(Employee emp) {

        if (repo.existsByEmail(emp.getEmail())) {
            throw new DuplicateResourceException("Employee already exists with email: " + emp.getEmail());
        }

        return repo.save(emp);
    }

    @Override
    public Employee update(Long id, Employee emp) {

        Employee existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        if (!existing.getEmail().equals(emp.getEmail()) &&
            repo.existsByEmail(emp.getEmail())) {

            throw new DuplicateResourceException("Email already in use: " + emp.getEmail());
        }

        existing.setName(emp.getName());
        existing.setRole(emp.getRole());
        existing.setSalary(emp.getSalary());
        existing.setEmail(emp.getEmail());

        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        Employee emp = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        repo.delete(emp);
    }
}
