package com.vmware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vmware.errors.EmployeeNotFoundException;
import com.vmware.model.Employee;
import com.vmware.repository.EmployeeRepository;

import io.swagger.annotations.ApiOperation;

@RestController
public class EmployeeController {
	
	@Autowired
	private final EmployeeRepository repository;
	
	EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}
	
	@ApiOperation(value = "Get all employees", notes = "Retrieve all information about all employees.", response = Employee.class)
	@GetMapping("/employees")
	@CrossOrigin
	Iterable<Employee> all() {
		return repository.findAll();
	}
	
	@ApiOperation(value = "Create new employees", notes = "Create a new employee, providing the name and role.", response = Employee.class)
	@PostMapping("/employees")
	Employee newEmployee(@RequestBody Employee newEmployee) {
		return repository.save(newEmployee);
	}
	
	@ApiOperation(value = "Get a single employee", notes = "Retrieve all information about a single employee specified by ID.", response = Employee.class)
	@GetMapping("/employees/{id}")
	Employee one(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}
	
	@ApiOperation(value = "Create a single employee", notes = "Create a single employee, providing the name, role, and ID.", response = Employee.class)
	@PutMapping("employees/{id}")
	Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		return repository.findById(id)
			.map(employee -> {
				employee.setName(newEmployee.getName());
				employee.setRole(newEmployee.getRole());
				return repository.save(employee);
			})
			.orElseGet(() -> {
				newEmployee.setId(id);
				return repository.save(newEmployee);
			});
	}
	
	@ApiOperation(value = "Delete a single employee", notes = "Delete all information about a single employee specified by ID.", response = Employee.class)
	@DeleteMapping("employees/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
