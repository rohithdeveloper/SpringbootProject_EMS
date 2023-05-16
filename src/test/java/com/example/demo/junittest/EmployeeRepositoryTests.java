package com.example.demo.junittest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@DataJpaTest
public class EmployeeRepositoryTests {

	@Autowired
	private EmployeeRepository empRepo;
	
	@Test
	void getEmployeeTest() {
		//Arrange
//		Employee emp=getEmployee();
		Employee employee=new Employee();
		employee.setFirstName("Avatar");
		employee.setEmpId(1L);
		employee.setLastName("waterbender");
		employee.setPassword("rohith");
		employee.setEmailId("rohith@gmail.com");
		//Act
		Employee newEmployee=empRepo.save(employee);
		//Assert
		assertNotNull(newEmployee);
		assertThat(newEmployee.getEmpId()==employee.getEmpId());
	}
	
	@Test
	void getAllEmployeesTest() {
		Employee employee1=new Employee();
		employee1.setEmpId(1L);
		employee1.setFirstName("Rahul");
		employee1.setLastName("parimella");
		employee1.setEmailId("rahul@gmail.com");
		
		Employee newEmployee1=empRepo.save(employee1);
		
		Employee employee2=new Employee();
		employee2.setEmpId(2L);
		employee2.setFirstName("Suresh");
		employee2.setLastName("raina");
		employee2.setEmailId("raina@gmail.com");
		
		Employee newEmployee2=empRepo.save(employee2);
		
		List<Employee> list=empRepo.findAll();
		assertNotNull(list);
		assertThat(list).isNotNull();
		assertThat(employee2.getFirstName()==employee1.getFirstName());
	
	}
	
	@Test
	void getByIdTest() {
		Employee employee=new Employee();
		employee.setFirstName("Avatar");
		employee.setEmpId(1L);
		employee.setLastName("waterbender");
		employee.setPassword("rohith");
		employee.setEmailId("rohith@gmail.com");
		empRepo.save(employee);
		Optional<Employee> existingEmployee=empRepo.findById(employee.getEmpId());
		assertNotNull(existingEmployee);
		assertThat(employee.getFirstName()==existingEmployee.get().getFirstName());
	}
	
	@Test
	void updateEmployeeTest() {
		Employee employee=new Employee();
		employee.setFirstName("Avatar");
		employee.setEmpId(1L);
		employee.setLastName("waterbender");
		employee.setPassword("rohith");
		employee.setEmailId("rohith@gmail.com");
		empRepo.save(employee);
		Employee existingEmployee=empRepo.findById(employee.getEmpId()).get();
		existingEmployee.setEmailId("rahul@gmail.com");
		Employee newEmployee=empRepo.save(existingEmployee);
		assertEquals("rahul@gmail.com",newEmployee.getEmailId());
		
	}
	
	@Test
	void deleteEmployeeTest() {
		Employee employee1=new Employee();
		employee1.setEmpId(1L);
		employee1.setFirstName("Rahul");
		employee1.setLastName("parimella");
		employee1.setEmailId("rahul@gmail.com");
		Employee newEmployee1=empRepo.save(employee1);
		
		Long id=employee1.getEmpId();
		
		Employee employee2=new Employee();
		employee2.setEmpId(2L);
		employee2.setFirstName("Suresh");
		employee2.setLastName("raina");
		employee2.setEmailId("raina@gmail.com");
		Employee newEmployee2=empRepo.save(employee2);
		
		empRepo.delete(newEmployee1);
		Optional<Employee> existingEmployee=empRepo.findById(id);
		List<Employee> list=empRepo.findAll();
		assertEquals(1,list.size());
		assertThat(existingEmployee).isEmpty();
	}
	
	@Test
	void deleteAllTest() {
		Employee employee1=new Employee();
		employee1.setEmpId(1L);
		employee1.setFirstName("Rahul");
		employee1.setLastName("parimella");
		employee1.setEmailId("rahul@gmail.com");
		Employee newEmployee1=empRepo.save(employee1);
		
		//Long id=employee1.getEmpId();
		
		Employee employee2=new Employee();
		employee2.setEmpId(2L);
		employee2.setFirstName("Suresh");
		employee2.setLastName("raina");
		employee2.setEmailId("raina@gmail.com");
		Employee newEmployee2=empRepo.save(employee2);
		
		empRepo.deleteAll();
	
		Optional<Employee> existingEmployee=empRepo.findById(employee1.getEmpId());
		Optional<Employee> existingEmployee1=empRepo.findById(employee2.getEmpId());
		assertThat(existingEmployee).isEmpty();
		assertThat(existingEmployee1).isEmpty();
	
	}
	
	
	
}
