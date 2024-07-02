package kw.kng.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.Employee;


public interface EmployeeRepo extends JpaRepository<Employee, Long> 
{
	Optional<Employee> findByEmail(String email);

}
