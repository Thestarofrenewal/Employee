package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.binding.EmployeeForm;
import com.example.demo.entity.Employee;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@Component
@PropertySource("classpath:config.properties")
@ConfigurationProperties(prefix = "app")
@RequestMapping("/employees")
public class EmployeeRest {

	@Value(value = "${app.name}")
	String nameMsg;

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/get")
	public ResponseEntity<List<Employee>> getData() throws DataNotFoundException {
		List<Employee> data = employeeService.getData();
		return new ResponseEntity<List<Employee>>(data, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Optional<Employee>> getById(@PathVariable("id") Integer id) throws DataNotFoundException {

		return ResponseEntity.ok(employeeService.getUser(id));
	}

	@PostMapping("/add")
	public ResponseEntity<String> addData(@Valid @RequestBody EmployeeForm emp) {

		boolean data = employeeService.addData(emp);

		if (data)

			return new ResponseEntity<>(nameMsg, HttpStatus.CREATED);
		else {
			return new ResponseEntity<>("failed", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

}
