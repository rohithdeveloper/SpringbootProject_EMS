package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.EmployeeDto;


public interface EmployeeDtoService {

	 EmployeeDto createEmployee( EmployeeDto  employeeDto);
	List< EmployeeDto> getAllEmployees();
	 EmployeeDto getEmployeeById(long id);
	String updateEmployee(long id , EmployeeDto employeeDto);
	String deleteEmployee(long id);
	String deleteAll();
}
