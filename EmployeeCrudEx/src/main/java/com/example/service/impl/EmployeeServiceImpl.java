package com.example.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.repo.EmployeeRepository;
import com.example.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repo;
	
	/**
	 * @author Abhishek Rai
	 * @param
	 * 
	 *@return 
	 * */

	public Integer saveEmployee(Employee employee) {
		employee = repo.save(employee);
		return employee.getId();
	}

	public List<Employee> getAllEmployees() {
		List<Employee> list = repo.findAll();
		return list;
	}

	public void deleteEmployee(Integer id) {
		repo.deleteById(id);
	}

	public Employee getOneEmployee(Integer id) {
		Optional<Employee> opt = repo.findById(id);
		if (opt.isPresent()) {
			Employee e = opt.get();
			return e;
		}
		return null;
	}
	public void updateEmployee(Employee e) {
		repo.save(e);

}
}
