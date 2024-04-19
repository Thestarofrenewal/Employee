package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.binding.EmployeeForm;
import com.example.demo.entity.Employee;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.repo.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;
	
	public Optional<Employee> getUser(Integer id) throws DataNotFoundException {
		Optional<Employee> byId = employeeRepo.findById(id);
		if(byId.isPresent()) {
			return byId;
		}
		throw new DataNotFoundException("user not found");
	}

	public List<Employee> getData() throws DataNotFoundException {

		List<Employee> all = employeeRepo.findAll();
		if (all != null) {
			return all;

		} else {
			throw new DataNotFoundException("Data not Found ");
		}

	}

	public boolean addData(EmployeeForm employee) {

		try {
			Employee emp = new Employee();
			emp.setName(employee.getName());
			emp.setDesignation(employee.getDesignation());
			employeeRepo.save(emp);
			return true;
		} catch (Exception e) {
			// Log the error or handle it gracefully
			e.printStackTrace();
			return false;
		}

	}
}
