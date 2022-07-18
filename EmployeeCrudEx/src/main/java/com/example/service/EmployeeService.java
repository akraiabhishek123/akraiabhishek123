package com.example.service;

import java.util.List;

import com.example.entity.Employee;


public interface EmployeeService {
	Integer saveEmployee(Employee employee);
	List<Employee> getAllEmployees();
	void deleteEmployee(Integer id);
	Employee getOneEmployee(Integer id);
	void updateEmployee(Employee e);
}

