package kw.kng.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto)
	{
		String response = as.login(loginDto);
		
		return ResponseEntity.ok(response);
	}

	//Build Register REST API
	@PostMapping(value = {"/register", "/signup"})
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto)
	{
		String response = as.register(registerDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	

}
