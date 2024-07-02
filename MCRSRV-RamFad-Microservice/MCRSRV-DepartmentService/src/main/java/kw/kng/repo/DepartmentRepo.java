package kw.kng.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.Department;

public interface DepartmentRepo extends JpaRepository<Department, Long> 
{
	//Department findByDepartmentCode(String departmentCode);
	
	Optional<Department> findByDepartmentCode(String departmentCode);

}
