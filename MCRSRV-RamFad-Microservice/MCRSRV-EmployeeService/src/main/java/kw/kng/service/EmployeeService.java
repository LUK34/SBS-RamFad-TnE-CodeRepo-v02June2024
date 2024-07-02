package kw.kng.service;

import java.util.List;

import kw.kng.dto.EmployeeDto;

public interface EmployeeService 
{
	EmployeeDto createEmployeeSingle(EmployeeDto employeeDto);
	List<EmployeeDto> createEmployeeMultiple( List<EmployeeDto> employeeDto);
	EmployeeDto getEmployeeById(Long empId);
	List<EmployeeDto> getAllEmployee();
	EmployeeDto updateEmployeeById (Long empId, EmployeeDto empDto);
	void deleteEmployeeById(Long empId);
}
