package kw.kng.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kw.kng.payload.JwtAuthResponse;
import kw.kng.payload.LoginDto;
import kw.kng.payload.RegisterDto;
import kw.kng.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController 
{
	private AuthService as;

	public AuthController(AuthService as) 
	{
		this.as = as;
	}
	
	//Build Login REST API
	@PostMapping(value = {"/login", "/signin"})
	public ResponseEntity<JwtAuthResponse> login(@RequestBody @Valid LoginDto loginDto)
	{
		String token = as.login(loginDto);
		
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setAccessToken(token);
		
		return ResponseEntity.ok(jwtAuthResponse);
	}

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


