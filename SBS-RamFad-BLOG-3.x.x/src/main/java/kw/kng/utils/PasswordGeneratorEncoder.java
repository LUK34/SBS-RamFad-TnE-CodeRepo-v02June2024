package kw.kng.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGeneratorEncoder
{
	public static void main(String[] args)
	{
		PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
		System.out.println("Admin Password: " +passwordEncoder.encode("admin"));
		System.out.println("abcd Password: " + passwordEncoder.encode("abcd"));
	}

}

/*
 
 Admin Password: $2a$10$u.Q6sSiWOGsMCfcmExtjmuesR5fjtA9KUtK5TdQTLOp5/yDZy9Wou
 abcd Password: $2a$10$mySg9C2aNShzlJFIa9VpW.KdrzvYzxuPms8CanCQ4I6RlaAkVYNFu

*/