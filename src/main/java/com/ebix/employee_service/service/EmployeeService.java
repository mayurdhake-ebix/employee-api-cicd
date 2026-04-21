package com.ebix.employee_service.service;

import java.util.List;

import com.ebix.employee_service.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> getAll();
	
	public Employee getById(Long id);
	
	public Employee create(Employee emp);
	
	public Employee update(Long id, Employee emp);
	
	public void delete(Long id);
}
