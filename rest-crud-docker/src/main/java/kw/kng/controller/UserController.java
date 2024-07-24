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
import kw.kng.dto.UserDto;
import kw.kng.service.UserService;

@Tag(
		name="Simple CRUD v1 REST APIs for User Resource",
		description="CRUD REST APIs - Create User, Update User, Get User, Get All User, Delete User"
)
@RestController
@RequestMapping("/api/users")
public class UserController 
{
	private UserService us;

	public UserController(UserService us) 
	{
		this.us = us;
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@Operation(
			summary="Create Single User REST API",
			description="Create Single User REST API is used to save user in a DB"
	)
	@ApiResponse(
			responseCode="201",
			description="HTTP Status 201 Created"
	)
	//POST -> Create a SINGLE User
	@PostMapping("/single")
	public ResponseEntity<UserDto> addTodo(@RequestBody @Valid UserDto userDto)
	{
		UserDto savedUserDto= us.createUser(userDto);
		return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED );
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@Operation(
			summary="Create Multiple User REST API",
			description="Create Multiple User REST API and are used to save users in a DB"
	)
	@ApiResponse(
			responseCode="201",
			description="HTTP Status 201 Created"
	)
	//POST -> Create MULTIPLE User
	@PostMapping("/multiple")
	public ResponseEntity<List<UserDto>> createUserList(@RequestBody @Valid  List<@Valid UserDto> userDto)
	{
		List<UserDto> savedUserList = us.createUserList(userDto);
		return new ResponseEntity<>(savedUserList, HttpStatus.CREATED );
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	@Operation(
			summary="Get User by id REST API",
			description="Get User by id REST API  and save into DB"
	)
	@ApiResponse(
			responseCode="200",
			description="HTTP Status 200 SUCCESS"
	)
	//GET ->  Get User by id
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId)
	{
		UserDto userDto =  us.getUserById(userId);
		return ResponseEntity.ok(userDto);
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	@Operation(
			summary="Get List of Users REST API",
			description="Get List of User REST API and save into  DB"
	)
	@ApiResponse(
			responseCode="200",
			description="HTTP Status 200 SUCCESS"
	)
	//GET -> Get All User List
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUserList()
	{
		List<UserDto> userList= us.getAllUser();
		return ResponseEntity.ok(userList);
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	@Operation(
			summary="Update User by id REST API",
			description="Update User by id REST API and save into  DB"
	)
	@ApiResponse(
			responseCode="200",
			description="HTTP Status 200 SUCCESS"
	)
	//PUT -> Update a specific user by id
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody @Valid UserDto userDto)
	{
		UserDto usDto = us.updateUser(userId, userDto);
		return ResponseEntity.ok(usDto);
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	@Operation(
			summary="Delete User by id REST API",
			description="Delete User by id REST API. Remove the row from  DB"
	)
	@ApiResponse(
			responseCode="200",
			description="HTTP Status 200 SUCCESS"
	)
	//DELETE -> Delete a specific user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletUserById(@PathVariable("id") Long userId)
	{
		us.deleteUserByid(userId);
		return ResponseEntity.ok("User with id: "+userId+" deleted successfully in DB!!!");
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
}







