package kw.kng.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import kw.kng.entities.Employee;

@DataJpaTest
public class EmployeeRepoTest
{
	
	@Autowired
	private EmployeeRepo erepo;

	private Employee emp;
	/*
	@BeforeEach
	public void setup()
	{
		 emp =  Employee.builder()
				  .firstName("Virat")
				  .lastName("Kholi")
				  .email("v.kohli@gmail.com")
				  .build();
	}
	*/
	//Junit test for save employee operation
	@DisplayName("Junit test for save employee operation")
	@Test
	public void givenEmployeeObject_whenSave_thenReturnSavedEmployee()
	{
		
		//given - precondition or setup
		
		Employee emp =  Employee.builder()
													  .firstName("Virat")
													  .lastName("Kholi")
													  .email("v.kohli@gmail.com")
													  .build();
		
		//when -action or the behaviour that we are going to test
		Employee savedEmp = erepo.save(emp);
		
		//then -verify the output
		Assertions.assertThat(savedEmp).isNotNull();
		Assertions.assertThat(savedEmp.getId()).isGreaterThan(0);
	}
	
	//Junit test for get a;; employee operation
	@DisplayName("Junit test for get all employees operation")
	@Test
	public void givenEmployeeList_whenfindAll_thenEmployeeList()
	{
		//given - precondition or setup
		
		Employee emp =  Employee.builder()
				  .firstName("John")
				  .lastName("Cena")
				  .email("j.cena@gmail.com")
				  .build();
	
		Employee emp2 =  Employee.builder()
				  .firstName("Roman")
				  .lastName("Reigns")
				  .email("r.reigns@gmail.com")
				  .build();
		erepo.save(emp);
		erepo.save(emp2);
		
		//when -action or the behaviour that we are going to test
		List<Employee> listEmployee=erepo.findAll();
		
		//then -verify the output
		assertThat(listEmployee).isNotNull();
		assertThat(listEmployee.size()).isEqualTo(2);
		
	}
	
	//For template refer video 139
	
	//Junit test for get employee by id operation
	@Test
	public void givenEmployeeObject_whenFindById_thenreturnEmployee()
	{
		//given - precondition or setup
		
		Employee emp =  Employee.builder()
				  .firstName("Seth")
				  .lastName("Rollins")
				  .email("s.rolls@gmail.com")
				  .build();
			
		erepo.save(emp);
		
		//when - action or the behaviour that we are going test
		Employee empDb= erepo.findById(emp.getId()).get();
		
		//then = verify the output
		assertThat(empDb).isNotNull();
		
	}
	
	//Junit test to get employee by email operation
	@DisplayName("JUnit test for get employee by email operation")
	@Test
	public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject()
	{
		//given -precondition or setup
		
		Employee emp =  Employee.builder()
				  .firstName("Dean")
				  .lastName("Ambrose")
				  .email("d.ambr@gmail.com")
				  .build();
				  
		erepo.save(emp);
		
		//when - action or the behaviour that we are going test
		Employee empDb=erepo.findByEmail(emp.getEmail()).get();
		
		//then - verify the output
		assertThat(empDb).isNotNull();
		
	}
	
	@DisplayName("JUnit test for Update employee operation")
	@Test
	public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee()
	{
		//given - precondition or setup
		
		Employee emp =  Employee.builder()
				  .firstName("Dean")
				  .lastName("Ambrose")
				  .email("d.ambr@gmail.com")
				  .build();
				  
		erepo.save(emp);
	
		
		//when - action or the behaviour that we are going to test
		Employee savedEmp=erepo.findById(emp.getId()).get();
		savedEmp.setEmail("ram@gmail.com");
		savedEmp.setFirstName("Ram");
		Employee updEmp=erepo.save(savedEmp);
		
		//then - verify the output
		assertThat(updEmp.getEmail()).isEqualTo("ram@gmail.com");
		assertThat(updEmp.getFirstName()).isEqualTo("Ram");
	}
	
	@DisplayName("JUnit test for Delete employee operation")
	@Test
	public void givenEmployeeObject_whenDelete_thenRemoveEmployee()
	{
			//given - precondition or setup
	
		Employee emp =  Employee.builder()
				  .firstName("Dean")
				  .lastName("Ambrose")
				  .email("d.ambr@gmail.com")
				  .build();
			  
		erepo.save(emp);
	
		
		//when - action or the behaviour that we are going to test
		erepo.delete(emp);
		Optional<Employee> empOpt= erepo.findById(emp.getId());
		
		//then - verify the output
		assertThat(empOpt).isEmpty();
		
	}
	
	//Junit test for custom query using JPQL with index
	@DisplayName("JUnit test for custom query using JPQL with index")
	@Test
	public void givenFirstNameAndLastNAME_whenFindByJPQL_thenReturnEmployeeObject()
	{
		//given - precondition or setup
		
		Employee emp =  Employee.builder()
				  .firstName("Maxwell")
				  .lastName("Freidman")
				  .email("max.mjf@gmail.com")
				  .build();
			 
		erepo.save(emp);
		String firstName="Maxwell";
		String lastName="Freidman";
		
		//when -action or the behaviour that we are going test
		Employee savedEmployee=erepo.findByJPQL(firstName, lastName);
		
		//then - verify the output
		assertThat(savedEmployee).isNotNull();	
	}
	
	
	@DisplayName("JUnit test for custom query using JPQL Named Parameter with index")
	@Test
	public void givenFirstNameAndLastNAME_whenFindByJPQLNamedParam_thenReturnEmployeeObject()
	{
		//given - precondition or setup
	
		Employee emp =  Employee.builder()
				  .firstName("Maxwell")
				  .lastName("Freidman")
				  .email("max.mjf@gmail.com")
				  .build();
		
		erepo.save(emp);
		String firstName="Maxwell";
		String lastName="Freidman";
		
		//when -action or the behaviour that we are going test
		Employee savedEmployee=erepo.findByJPQLNamedParam(firstName, lastName);
		
		//then - verify the output
		assertThat(savedEmployee).isNotNull();
	}
	
	
	@DisplayName("JUnit test for custom query using Native SQL Query with index")
	@Test
	public void givenFirstNameAndLastNAME_whenFindByNativeSQL_thenReturnEmployeeObject()
	{
		//given - precondition or setup
		
		Employee emp =  Employee.builder()
				  .firstName("Maxwell")
				  .lastName("Freidman")
				  .email("max.mjf@gmail.com")
				  .build();
	
		erepo.save(emp);
		String firstName="Maxwell";
		String lastName="Freidman";
		
		//when -action or the behaviour that we are going test
		Employee savedEmployee=erepo.findByNativeSQL(firstName, lastName);
		
		//then - verify the output
		assertThat(savedEmployee).isNotNull();	
	}
	
	

	@DisplayName("JUnit test for custom query using Native SQL Query Named Param with index")
	@Test
	public void givenFirstNameAndLastNAME_whenFindByNativeSQLNamedParam_thenReturnEmployeeObject()
	{
		//given - precondition or setup
		
		Employee emp =  Employee.builder()
				  .firstName("Maxwell")
				  .lastName("Freidman")
				  .email("max.mjf@gmail.com")
				  .build();
		
		erepo.save(emp);
		String firstName="Maxwell";
		String lastName="Freidman";
		
		//when -action or the behaviour that we are going test
		Employee savedEmployee=erepo.findByNativeSQLNamedParam(firstName, lastName);
		
		//then - verify the output
		assertThat(savedEmployee).isNotNull();	
	}
	
	
	
	
	
	/*
	@DisplayName("JUnit test ")
	@Test
	public void given_when_then()
	{
		//given - precondition or setup
		
		//when -action or the behaviour that we are going test
		
		//then - verify the output
	}
	*/
}

/*
 Spring boot provide the @DataJpa Test annotation to test the persistence layer components
  that will autoconfigure in memory embedded database for testing purposes.

 */




