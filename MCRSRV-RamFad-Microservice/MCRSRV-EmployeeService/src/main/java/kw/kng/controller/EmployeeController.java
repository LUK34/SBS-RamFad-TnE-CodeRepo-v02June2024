package kw.kng.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kw.kng.dto.EmployeeDto;
import kw.kng.service.EmployeeService;

@Tag(
		name="Simple Employee Service REST APIs",
		description="Employee Service REST APIs - Create Employee, Update Employee, Get Employee, Get All Employee, Delete Employee"
)
@RestController
@RequestMapping("/api/employees")  									// URL : http://localhost:8081
public class EmployeeController 
{
	private EmployeeService es;

	public EmployeeController(EmployeeService es) 
	{
		this.es = es;
	}
	
	//POST - Create SINGLE User
	@Operation(
			summary="Create Single Employee REST API",
			description="Create Single Employee REST API is used to save Employee in a DB"
	)
	@ApiResponse(
			responseCode="201",
			description="HTTP Status 201 Created"
	)
	@PostMapping("/single")
	public ResponseEntity<EmployeeDto> addEmployee_Single(@RequestBody @Valid EmployeeDto empDto)
	{
		EmployeeDto savedEmpDto = es.createEmployeeSingle(empDto);
		return new ResponseEntity<>(savedEmpDto, HttpStatus.CREATED);
	}
	
	//POST -> Create MULTIPLE User
	@Operation(
			summary="Create Multiple Employee REST API",
			description="Create Multiple Employee REST API is used to save Employee in a DB"
	)
	@ApiResponse(
			responseCode="201",
			description="HTTP Status 201 Created"
	)
	@PostMapping("/multiple")
	public ResponseEntity<List<EmployeeDto>> addEmployee_Multiple(@RequestBody @Valid List<@Valid EmployeeDto> empDto)
	{
		List<EmployeeDto> savedEmployeeList = es.createEmployeeMultiple(empDto);
		return new ResponseEntity<>(savedEmployeeList, HttpStatus.CREATED);
	}
	
	
	@Operation(
			summary="Get Employee REST API by id",
			description="Get Employee REST API by id from the DB"
	)
	@ApiResponse(
			responseCode="200",
			description="HTTP Status 200 OK"
	)
	//GET -> Get Employee by id
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long  empId)
	{
		EmployeeDto empDto = es.getEmployeeById(empId);
		return ResponseEntity.ok(empDto);
	}
	
	@Operation(
			summary="Get Employee List REST API",
			description="Get Employee List REST API from the DB"
	)
	@ApiResponse(
			responseCode="200",
			description="HTTP Status 200 OK"
	)
	//GET -> Get All Employee List
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmployeeList()
	{
		List<EmployeeDto> empList = es.getAllEmployee();
		return ResponseEntity.ok(empList);
	}
	
	
	@Operation(
			summary="Update Employee REST API by id",
			description="Update Employee REST API by id from the DB"
	)
	@ApiResponse(
			responseCode="200",
			description="HTTP Status 200 OK"
	)
	//PUT -> Update Employee by id
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long empId, @RequestBody @Valid EmployeeDto empDto)
	{
		EmployeeDto updatedEmpDto = es.updateEmployeeById(empId, empDto);
		return ResponseEntity.ok(updatedEmpDto);
	}
	
	
	@Operation(
			summary="Delete Employee REST API by id",
			description="Delete Employee REST API by id from the DB"
	)
	@ApiResponse(
			responseCode="200",
			description="HTTP Status 200 OK"
	)
	//DELETE -> Delete Employee by id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletEmployeeById(@PathVariable("id") Long empId)
	{
		es.deleteEmployeeById(empId);
		return ResponseEntity.ok("Employee with id: "+empId+" deleted successfully from DB !!!");
	}
	
	

}
