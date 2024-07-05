package kw.kng.service;

import java.util.List;

import kw.kng.dto.DepartmentDto;

public interface DepartmentService 
{
	DepartmentDto saveDepartmentSingle(DepartmentDto departmentDto);
	List<DepartmentDto> saveDepartmentMultiple(List<DepartmentDto> departmentDto);
	DepartmentDto getDepartmentById(Long departmentId);
	List<DepartmentDto> getAllDepartment();
	DepartmentDto updateDepartment(Long depid, DepartmentDto departmentDto);
	void deleteDepartmentById(Long depid);
	
	DepartmentDto getDepartmentByCode(String code) ;
}
