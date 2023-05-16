package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeDtoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeDtoService empService;
	
	@PostMapping("/employees")
	public EmployeeDto addUser(@RequestBody @Valid EmployeeDto empDto)
	{
		return empService.createEmployee(empDto);
		
	}
	

	@GetMapping("/employees")
	public List<EmployeeDto> getEmployee(){
		List<EmployeeDto> emp=empService.getAllEmployees();
		return emp;
		
	}
	
	@GetMapping("employees/{id}")
	public EmployeeDto getUserById( @PathVariable long id) {
		return empService.getEmployeeById(id);
	}
	
	@PutMapping("/employees/{id}")
	public String updateUser(@PathVariable long id,@RequestBody EmployeeDto empDto)
	{
		return empService.updateEmployee(id, empDto);
		
	}
	
	@DeleteMapping("/employees/{id}")
	public String deleteUserById(@PathVariable long id,@RequestBody EmployeeDto empDto) {
		return empService.deleteEmployee(id);
		
		
	}
	
	@DeleteMapping("/employees")
	public String deleteUsers()
	{
		return empService.deleteAll();
		
	}
	
	
	
}
