package kw.kng.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kw.kng.entites.Role;
import kw.kng.entites.User;
import kw.kng.exceptions.BlogApiException;
import kw.kng.payload.LoginDto;
import kw.kng.payload.RegisterDto;
import kw.kng.repo.RoleRepo;
import kw.kng.repo.UserRepo;
import kw.kng.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService 
{
	private AuthenticationManager am;
	private UserRepo urepo;
	private RoleRepo rrepo;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider jwtTokenProvider;
	
	
	public AuthServiceImpl(AuthenticationManager am, 
			UserRepo urepo,
			RoleRepo rrepo,
			PasswordEncoder passwordEncoder,
			JwtTokenProvider jwtTokenProvider)
	{
		this.am = am;
		this.urepo = urepo;
		this.rrepo = rrepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider= jwtTokenProvider;
	}

	@Override
	public String login(LoginDto loginDto) 
	{
		Authentication authentication = am.authenticate(new UsernamePasswordAuthenticationToken(
				loginDto.getUsernameOrEmail(), loginDto.getPassword()));
	
		SecurityContextHolder.getContext().setAuthentication(authentication);
	
		String token = jwtTokenProvider.generateToken(authentication);
		
		//return "User: " +loginDto.getUsernameOrEmail()+ " logged in successfully!!!";
		return token;
	}

	@Override
	public String register(RegisterDto registerDto) 
	{
			//Register -> Check whether the username exists or not in DB
		if(urepo.existsByUsername(registerDto.getUsername()))
		{
			throw new BlogApiException("Username: "+registerDto.getUsername()+" already exists in DB !!!");
		}
		
		//Register -> Check whethere the email exist or not in DB.
		if(urepo.existsByEmail(registerDto.getEmail()))
		{
			throw new BlogApiException("Username: "+registerDto.getEmail()+" already exists in DB !!!");
		}
		
		User user= new User();
		user.setName(registerDto.getName());
		user.setUsername(registerDto.getUsername());
		user.setEmail(registerDto.getEmail());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		
		Set<Role> roles = new HashSet<>();
		Role userRole = rrepo.findByName("ROLE_USER").get();
		roles.add(userRole);
		user.setRoles(roles);

		urepo.save(user);
		
		return "User with id : "+user.getId()+" registered successfully in DB";
	}

}
