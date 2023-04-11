package com.springboot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.EmployeeEntity;
import com.springboot.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeController {
	
	@Autowired
	private  EmployeeRepository employeeRepository ;
	
	// Get All Empleyee 
	
	@GetMapping("/employees")
	public List<EmployeeEntity> getAllEmployees(){
		return (List<EmployeeEntity>) employeeRepository.findAll() ;
	}

//	Create Employee 
	@PostMapping("employees")
	public EmployeeEntity createEmployee(@RequestBody  EmployeeEntity employee ) {
		return employeeRepository.save(employee); 
	}
	
	//get Employee By id
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable Long  id) {
		EmployeeEntity employee =  employeeRepository.findById(id).get();
		return ResponseEntity.ok(employee) ;
	} 
	
	//update emplyee
	@PutMapping("/employees/{id}")
	public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable Long id , @RequestBody EmployeeEntity employeeDetails) {
		EmployeeEntity employee =  employeeRepository.findById(id).get();
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName()) ;
		employee.setEmailId(employeeDetails.getEmailId());
		
		EmployeeEntity updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee) ;
	}
	
}
