package kw.kng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import kw.kng.security.JwtAuthenticationEntryPoint;
import kw.kng.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@SecurityScheme(
		name= "Bear Authentication",
		type=SecuritySchemeType.HTTP,
		bearerFormat= "JWT",
		scheme="bearer"
)
public class SecurityConfig
{
	private UserDetailsService uds;
	
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	private JwtAuthenticationFilter authenticationFilter;
	
	public SecurityConfig(UserDetailsService uds, JwtAuthenticationEntryPoint authenticationEntryPoint,  JwtAuthenticationFilter authenticationFilter) 
	{
		this.uds = uds;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationFilter = authenticationFilter;
	}

	@Bean
	public static PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
	{
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		//http.csrf((csrf)->csrf.disable())
	
		http.csrf((csrf)->csrf.disable())
										.authorizeHttpRequests((authorize) ->
														//authorize.anyRequest().authenticated()
														authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll() 
																		//.requestMatchers(HttpMethod.GET,"/api/categories/**").permitAll() 
																		.requestMatchers("/api/auth/**").permitAll()
																		.requestMatchers("/swagger-ui/**").permitAll()
																		.requestMatchers("/v3/api-docs/**").permitAll()
																		.anyRequest().authenticated()
												).exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
												.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
										
										//httpBasic(Customizer.withDefaults()); 
		
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		/*
		 	enables the windows pop up basic authentication request 
		 	instead of the default form based authentication request.
		*/
		
		return http.build();
	}
	
	/*
	//In Memory Authentication
	@Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails ud = User.builder().username("abcd").password(passwordEncoder().encode("abcd")).roles("USER").build();
		
		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();

		return new InMemoryUserDetailsManager(ud,admin);
	}
	*/
	
	
}

/*
 Postman:
 Authorization -> Basic Auth ->luke/helloworld
 
*/