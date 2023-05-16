package com.example.demo.junittest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.serviceimpl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {
	
	
	@InjectMocks
	private EmployeeServiceImpl empService;
	
	@Mock
	private EmployeeRepository empRepo;
	
	
	
	@Test
	 void getEmployeeTest() {
		Employee employee=getEmployee();
		EmployeeDto empDto=getEmployeeDto();
		when(empRepo.save(any(Employee.class))).thenReturn(employee);
		EmployeeDto newEmployee=empService.createEmployee(empDto);
		assertNotNull(newEmployee);
		assertThat(newEmployee.getFirstName()).isEqualTo(employee.getFirstName());
	}
	
	@Test
	void testGetAllEmployees() {
		Employee empDto=new Employee();
		empDto.setEmpId(1L);
		empDto.setFirstName("rohith");
		empDto.setLastName("parimella");
		empDto.setEmailId("rohith@gmail.com");
		
		Employee empDto1=new Employee();
		empDto1.setEmpId(2L);
		empDto1.setFirstName("rahul");
		empDto1.setLastName("parimella");
		empDto1.setEmailId("rahul@gmail.com");
		
		List<Employee> list=new ArrayList<>();
		list.add(empDto);
		list.add(empDto1);
		when(empRepo.findAll()).thenReturn(list);
		List<EmployeeDto> employees=empService.getAllEmployees();
		assertEquals(2,employees.size());
		
	}
	
	@Test
	void getEmployeeByIdTest() {
		EmployeeDto empDto=getEmployeeDto();
		Optional<Employee> emp=Optional.of(getEmployee());
		when(empRepo.findById(empDto.getEmpId())).thenReturn(emp);
		EmployeeDto newEmployee=empService.getEmployeeById(empDto.getEmpId());
		assert(emp.get().getEmpId()==newEmployee.getEmpId());
		
		
	}
	
	@Test
	void updateEmployeeTest() {
		EmployeeDto empDto=getEmployeeDto();
		Optional<Employee> emp=Optional.of(getEmployee());
		when(empRepo.findById(empDto.getEmpId())).thenReturn(emp);
		when(empRepo.save(any(Employee.class))).thenReturn(emp.get());
		empDto.setFirstName("Suresh");
		String newEmployee=empService.updateEmployee(1, empDto);
		assertNotNull(newEmployee);
		assertThat(newEmployee.toString()==empDto.getFirstName());
	}
	
	@Test 
	void deleteEmployeeTest() {
		EmployeeDto empDto=getEmployeeDto();
		Optional<Employee> emp=Optional.of(getEmployee());
		when(empRepo.findById(empDto.getEmpId())).thenReturn(emp);
		doNothing().when(empRepo).deleteById(empDto.getEmpId());
		empService.deleteEmployee(1L);
		verify(empRepo,times(1)).deleteById(empDto.getEmpId());
		
	}

	@Test
	void deleteAllTest() {
		EmployeeDto empDto=getEmployeeDto();
		doNothing().when(empRepo).deleteAll();
		empService.deleteAll();
		verify(empRepo,times(1)).deleteAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
private EmployeeDto getEmployeeDto() {
		
		EmployeeDto empDto=new EmployeeDto();
		empDto.setEmpId(1L);
		empDto.setFirstName("rohith");
		empDto.setLastName("parimella");
		empDto.setEmailId("rohith@gmail.com");
		return empDto;
		
	}
private Employee getEmployee() {
	
	Employee empDto=new Employee();
	empDto.setEmpId(2L);
	empDto.setFirstName("Rahul");
	empDto.setLastName("parimella");
	empDto.setEmailId("rahul@gmail.com");
	return empDto;
	
}

}


