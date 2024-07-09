package kw.kng.service;

import kw.kng.payload.LoginDto;
import kw.kng.payload.RegisterDto;

public interface AuthService
{
	String login(LoginDto loginDto);
	String register(RegisterDto registerDto);

}
