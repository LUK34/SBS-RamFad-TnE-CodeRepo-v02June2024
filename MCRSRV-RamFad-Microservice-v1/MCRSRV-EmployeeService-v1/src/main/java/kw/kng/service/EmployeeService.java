package kw.kng.service;

import java.util.List;

import kw.kng.dto.APIResponseDto;
import kw.kng.dto.EmployeeDto;

public interface EmployeeService 
{
	EmployeeDto createEmployeeSingle(EmployeeDto employeeDto);
	List<EmployeeDto> createEmployeeMultiple( List<EmployeeDto> employeeDto);
	APIResponseDto getEmployeeById(Long empId);
	List<APIResponseDto> getAllEmployee();
	EmployeeDto updateEmployeeById (Long empId, EmployeeDto empDto);
	void deleteEmployeeById(Long empId);
}
