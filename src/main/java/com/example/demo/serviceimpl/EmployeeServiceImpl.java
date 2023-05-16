package com.example.demo.serviceimpl;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.modelmapper.UserMapper;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeDtoService;

@Service
public class EmployeeServiceImpl implements EmployeeDtoService{

	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private ModelMapper userMapper;
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		
		// convert dto to entity
		Employee employee=UserMapper.mapToEmployee(employeeDto);
		Employee savedEmployee=empRepo.save(employee);
		// convert entity to dto
		//EmployeeDto empDto=userMapper.map(savedEmployee, EmployeeDto.class);
		EmployeeDto empDto=UserMapper.mapToEmployeeDto(empRepo.save(employee));
		
		return empDto;
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		// TODO Auto-generated method stub
		List<Employee> employees=empRepo.findAll();
		return employees.stream().map(UserMapper::mapToEmployeeDto).collect(Collectors.toList());

	}

	@Override
	public EmployeeDto getEmployeeById(long id) {
		// TODO Auto-generated method stub
//		Optional<Employee> emp=empRepo.findById(id);
//		return userMapper.map(emp, EmployeeDto.class);
//		Employee emp=empRepo.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee id not found"+id));
//		return userMapper.map(emp, EmployeeDto.class);
		Optional<Employee> emp=empRepo.findById(id);
		Employee employee=emp.get();
		return UserMapper.mapToEmployeeDto(employee);
	}

	@Override
	public String updateEmployee(long id, EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		Optional<Employee> emp=empRepo.findById(id);
		if(emp.isPresent()) {
			Employee updateEmpoyee=emp.get();
			updateEmpoyee.setFirstName(employeeDto.getFirstName());
			updateEmpoyee.setLastName(employeeDto.getLastName());
			updateEmpoyee.setEmailId(employeeDto.getEmailId());;
		
			empRepo.save(updateEmpoyee);
			return "updated Successfully";
			
			}
		else {
			throw new EmployeeNotFoundException("Employee id not found"+id);
		}
		
	}

	@Override
	public String deleteEmployee(long id) {
		// TODO Auto-generated method stub
		Optional<Employee> user=empRepo.findById(id);
		if(user.isPresent()) {
			empRepo.deleteById(id);
			return "Employee:" +id+ "deleted Successfully";
		}
		else {
			throw new EmployeeNotFoundException("Employee id not found"+id);
		}
	}

	@Override
	public String deleteAll() {
		// TODO Auto-generated method stub
		empRepo.deleteAll();
		return "All users deleted successfully";
	}

}
