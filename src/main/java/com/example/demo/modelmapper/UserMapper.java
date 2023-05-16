package com.example.demo.modelmapper;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;

public class UserMapper {
	
	public static EmployeeDto mapToEmployeeDto(Employee employee) {
		
		EmployeeDto empDto=new EmployeeDto();
		empDto.setEmpId(employee.getEmpId());
		empDto.setFirstName(employee.getFirstName());
		empDto.setLastName(employee.getLastName());
		empDto.setEmailId(employee.getEmailId());
		return empDto;
		
	}
	public static Employee mapToEmployee(EmployeeDto empDto)
	{
		Employee emp=new Employee();
		emp.setEmpId(empDto.getEmpId());
		emp.setFirstName(empDto.getFirstName());
		emp.setLastName(empDto.getLastName());
		emp.setEmailId(empDto.getEmailId());
		return emp;
		
	}
}
