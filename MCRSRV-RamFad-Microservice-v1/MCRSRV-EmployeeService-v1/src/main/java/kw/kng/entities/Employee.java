package kw.kng.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="mcrsrv_employees")
public class Employee 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	
	@Column(nullable=false, unique= true)
	private String email;
	
	private String departmentCode;
}

/*
 DepartmentDto class must be created in the dto package of `EmployeeService-1` so that the data coming from `DepartmentService-v1` microservice will be 
 pass to `DepartmentDto` class. In this specific step we are following `RestTemplate` approach to handle the request/response
 communication between `EmployeeService` and `DepartmentService`.
 
  
 */
