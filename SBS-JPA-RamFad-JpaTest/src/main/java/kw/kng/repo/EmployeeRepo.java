package kw.kng.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kw.kng.entities.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long>
{
	Optional<Employee> findByEmail(String email);
	
	//define custom query using JPQL with inde params
	@Query("select e from Employee e where e.firstName=?1 and e.lastName=?2")
	Employee findByJPQL(String firstName, String lastName);

	@Query("select e from Employee e where e.firstName=:firstName and e.lastName=:lastName")
	Employee findByJPQLNamedParam(@Param("firstName") String firstName, @Param("lastName") String lastName);
	//Employee findByJPQLNamedParam(String firstName, String lastName);
	
	@Query(value="select * from JPA_TEST_EMPLOYEE e where e.first_name=?1 and e.last_name=?2", nativeQuery=true)
	Employee findByNativeSQL(String firstName, String lastName);
	
	@Query(value="select * from JPA_TEST_EMPLOYEE e where e.first_name=:firstName and e.last_name=:lastName", nativeQuery=true)
	Employee findByNativeSQLNamedParam(@Param("firstName") String firstName, @Param("lastName") String lastName);
	
	
}
