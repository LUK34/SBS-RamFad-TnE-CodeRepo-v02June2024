package kw.kng.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto 
{
	@NotEmpty(message="Name must not be Empty")
	private String name;
	
	@NotEmpty(message="Username must not be Empty")
	private String username;
	
	@NotEmpty(message="Email must not be Empty")
	@Email(message="Email must be VALID.")
	private String email;
	
	@NotEmpty(message="Password must not be Empty")
	private String password;
	
}
