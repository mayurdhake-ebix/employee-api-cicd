package com.ebix.employee_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebix.employee_service.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	boolean existsByEmail(String email);
}
