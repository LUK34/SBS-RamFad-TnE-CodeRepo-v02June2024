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
import kw.kng.dto.DepartmentDto;
import kw.kng.service.DepartmentService;

@Tag(
		name="Simple Employee Service REST APIs",
		description="Employee Service REST APIs - Create Employee, Update Employee, Get Employee, Get All Employee, Delete Employee"
)
@RestController
@RequestMapping("/api/departments")
public class DepartmentController 
{
	private DepartmentService ds;

	public DepartmentController(DepartmentService ds) 
	{
		this.ds = ds;
	}

	//POST ->SINGLE
	@Operation(
			summary="Create Single Department REST API",
			description="Create Single Department REST API is used to save Department in a DB"
	)
	@ApiResponse(
			responseCode="201",
			description="HTTP Status 201 Created"
	)
	@PostMapping("/single")
	public ResponseEntity<DepartmentDto> addDepartmentSingle(@RequestBody @Valid DepartmentDto departmentDto)
	{
			DepartmentDto savedDepartmentDto = ds.saveDepartmentSingle(departmentDto);
			return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
	}
	
	
	@Operation(
			summary="Create Multiple Department REST API",
			description="Create Multiple Department REST API is used to save Department in a DB"
	)
	@ApiResponse(
			responseCode="201",
			description="HTTP Status 201 Created"
	)
	//POST -> MULTIPLE
	@PostMapping("/multiple")
	public ResponseEntity<List<DepartmentDto>> createDepartmentList(@RequestBody @Valid List<@Valid DepartmentDto> departmentDto)
	{
		List<DepartmentDto> savedDepartmentList = ds.saveDepartmentMultiple(departmentDto);
		return new ResponseEntity<>(savedDepartmentList, HttpStatus.CREATED);
	}
	
	
	
	@Operation(
			summary="Get Department Details REST API by id",
			description="Get Department Details REST API by id from the DB"
	)
	@ApiResponse(
			responseCode="200",
			description="HTTP Status 200 OK"
	)
	//GET ->Get Department by id
	@GetMapping("/{id}")
	public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long depId)
	{
		DepartmentDto depDto = ds.getDepartmentById(depId);
		return ResponseEntity.ok(depDto);
	}
	
	
	@Operation(
			summary="Get Department Details REST API by code",
			description="Get Department Details REST API by code from the DB"
	)
	@ApiResponse(
			responseCode="200",
			description="HTTP Status 200 OK"
	)
	//GET ->Get Department by Department Code
	@GetMapping("/code/{code}")
	public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("code") String depCode)
	{
		DepartmentDto depDto = ds.getDepartmentByCode(depCode);
		return ResponseEntity.ok(depDto);
	}
	
	
	@Operation(
			summary="Get Department List REST API by code",
			description="Get Department List REST API by code from the DB"
	)
	@ApiResponse(
			responseCode="200",
			description="HTTP Status 200 OK"
	)
	//GET -> ALL Departments in LIST
	@GetMapping
	public ResponseEntity<List<DepartmentDto>> getAllDepartmentList()
	{
		List<DepartmentDto> depList = ds.getAllDepartment();
		return ResponseEntity.ok(depList);
	}
	
	
	@Operation(
			summary="Update Department  Details REST API by id",
			description="Update Department  Details REST API by id"
	)
	@ApiResponse(
			responseCode="200",
			description="HTTP Status 200 OK"
	)
	//PUT -> Update department id
	@PutMapping("/{id}")
	public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") Long depId, @RequestBody @Valid DepartmentDto departmentDto)
	{
		DepartmentDto depDto = ds.updateDepartment(depId, departmentDto);
		
		return ResponseEntity.ok(depDto);
	}
	
	@Operation(
			summary="Delete Department  Details REST API by id",
			description="Delete Department  Details REST API by id"
	)
	@ApiResponse(
			responseCode="200",
			description="HTTP Status 200 OK"
	)
	//DELETE -> Delete a specific department by id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable("id") Long depId)
	{
		ds.deleteDepartmentById(depId);
		return ResponseEntity.ok("Department with id: " +depId+"  deleted successfully in DB!!!");
	}
	

}

/*

 [
    {
        "departmentName": "Electronics and Communication",
        "departmentDescription": "ECE Department",
        "departmentCode": "ECE001"
    },
    {
        "departmentName": "Electrical and Electronics Engineering",
        "departmentDescription": "EEE Department",
        "departmentCode": "EEE001"
    }
]


 */












