package kw.kng.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kw.kng.entites.User;
import kw.kng.repo.UserRepo;

@Service
public class CustomerUserDetailsService implements UserDetailsService
{

	private UserRepo urepo;
	
	public CustomerUserDetailsService(UserRepo urepo) 
	{
		this.urepo = urepo;
	}



	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException 
	{
		//Check if the Username/Email exist in DB or not
		User user= urepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
									.orElseThrow(() -> new UsernameNotFoundException("Username/Email: "+usernameOrEmail+"does'nt exist in DB."));
		
		//User -> Role (Many to Many Relationship) -> Many Users can have Many Role
		//User details entered i successfull. Based on the User`s `username` or `email`. Fetch all the roles associated by that specific user.
		Set<GrantedAuthority> authorities = user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
			
		//Return the User -> Email, Password, and all the roles that the user have been given for authorization.
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
		
	}
	

}
/*

GrantedAuthority: 
---------------------------
GrantedAuthority is an interface in Spring Security that represents an authority granted to an Authentication object. 
It is typically used to represent roles or permissions assigned to a user.

SimpleGrantedAuthority:
------------------------------------
SimpleGrantedAuthority is a concrete implementation of the GrantedAuthority interface.
It is a simple class that wraps a String representing an authority (such as a role name).

In the context of Spring Security, GrantedAuthority and SimpleGrantedAuthority play a crucial role in managing user roles and permissions. Here’s how they function in your code:

Roles to Authorities Mapping:
------------------------------------------- 
The roles associated with a user are mapped to authorities (GrantedAuthority). 
This mapping is essential for Spring Security to understand what permissions or roles a user has.

Authority Representation:
--------------------------------------
SimpleGrantedAuthority is used to wrap each role name as an authority. 
This allows Spring Security to use these authorities when making access control decisions.


 */





