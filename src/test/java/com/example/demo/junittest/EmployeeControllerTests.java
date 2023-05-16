package com.example.demo.junittest;

import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.controller.EmployeeController;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.service.EmployeeDtoService;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTests {

	@InjectMocks
	private EmployeeController employeeController;
	
	@Mock
	private EmployeeDtoService empService;
	

	@Test
	public void getEmployeeTest() {
		long id = 1;
		EmployeeDto empDto=getEmployee();
		when(empService.getEmployeeById(id)).thenReturn(empDto);
		EmployeeDto empInfo=employeeController.getUserById(id);
		assert(empDto.getEmpId() == empInfo.getEmpId());
	}
	
	@Test
	 public void testGetAllEmployees() {
		EmployeeDto empDto=getEmployee();
		List<EmployeeDto> employees = new ArrayList<>();
	      employees.add(empDto);
	    when(empService.getAllEmployees()).thenReturn(employees);
	    List<EmployeeDto> empInfo=employeeController.getEmployee();
	    assert(employees.size()==empInfo.size());
	}

	@Test
	public void testDeleteEmployee() {
		long id = 1;
		String msg="Deleted successfully";
		EmployeeDto empDto=getEmployee();
		when(empService.deleteEmployee(id)).thenReturn(msg);
		String empInfo=employeeController.deleteUserById(id, empDto);
		assert(msg.equals(empInfo));
		
	}
	
	@Test
	 public void testUpdateEmployee() {
		long id = 1;
		String msg="updated successfully";
		EmployeeDto empDto=getEmployee();
		when(empService.updateEmployee(id, empDto)).thenReturn(msg);
		String empInfo=employeeController.updateUser(id, empDto);
		assert(msg.equals(empInfo));
		
	 }
	
	@Test
	public void testDeleteAll() {
		String msg="updated successfully";
		EmployeeDto empDto=getEmployee();
		when(empService.deleteAll()).thenReturn(msg);
		String empInfo=employeeController.deleteUsers();
		assert(msg.equals(empInfo));
	}
	
	
	
	
	
	

private EmployeeDto getEmployee() {
		
		EmployeeDto empDto=new EmployeeDto();
		empDto.setEmpId(1);
		empDto.setFirstName("rohith");
		empDto.setLastName("parimella");
		empDto.setEmailId("rohith@gmail.com");
		return empDto;
		
	}

	
	
}
