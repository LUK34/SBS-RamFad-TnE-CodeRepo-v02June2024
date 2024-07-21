package kw.kng.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kw.kng.payload.JwtAuthResponse;
import kw.kng.payload.LoginDto;
import kw.kng.payload.RegisterDto;
import kw.kng.service.AuthService;

@Tag(
		name="BLOG REST APIs for Authentication Resource",
		description= "BLOG REST APIs for Authentication Resource = Login/Signin, Register/Signup" 
)
@RestController
@RequestMapping("/api/auth")
public class AuthController 
{
	private AuthService as;

	public AuthController(AuthService as) 
	{
		this.as = as;
	}
	
	@SecurityRequirement(
			name="Bear Authentication"
	)
	@Operation(
			summary="Login/Signin REST API",
			description="Login/Signin REST API to get a specific category by id from Database"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	//Build Login REST API
	@PostMapping(value = {"/login", "/signin"})
	public ResponseEntity<JwtAuthResponse> login(@RequestBody @Valid LoginDto loginDto)
	{
		String token = as.login(loginDto);
		
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		
		return ResponseEntity.ok(jwtAuthResponse);
	}

	@SecurityRequirement(
			name="Bear Authentication"
	)
	@Operation(
			summary="Register/Signup REST API",
			description="Register/Signup REST API to get a specific category by id from Database"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	//Build Register REST API
	@PostMapping(value = {"/register", "/signup"})
	public ResponseEntity<String> register(@RequestBody @Valid RegisterDto registerDto)
	{
		String response = as.register(registerDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	

}


/*
 
 1. For JWT Token
 
 Type: POST,  raw,  JSON
 
 Input: 
 {
    "usernameOrEmail": "zapdos",
    "password": "zapdos"
}
 
 Output:
 
 {
    "accessToken": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ6YXBkb3NAZ21haWwuY29tIiwiaWF0IjoxNzIwODkzNDQ4LCJleHAiOjE3MjE0OTgyNDh9.hOQZKLuvfFmAjQxOAZFNz3EvnMPYIpIxGAAma7n9WzFjJsd3AnxTeyj-xVvvLgOE",
    "tokenType": "Bearer"
}
  
 As per the Output the JWT Token has 3 part:
 * First Part -> eyJhbGciOiJIUzM4NCJ9
 * Second Part -> eyJzdWIiOiJ6YXBkb3NAZ21haWwuY29tIiwiaWF0IjoxNzIwODkzNDQ4LCJleHAiOjE3MjE0OTgyNDh9
 * Third Part -> hOQZKLuvfFmAjQxOAZFNz3EvnMPYIpIxGAAma7n9WzFjJsd3AnxTeyj-xVvvLgOE
 
 
 
 */


