package kw.kng.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.Student;

public interface StudentRepo extends JpaRepository<Student, Long> 
{
	

}
