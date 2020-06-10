package com.vmware.repository;

import org.springframework.data.repository.CrudRepository;

import com.vmware.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	
}
