package kw.kng.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto 
{
	
	@NotEmpty(message="Enter Username or Email. This field must not be Empty")
	private String usernameOrEmail;
	
	@NotEmpty(message="Password must not be Empty")
	
	@NotBlank(message="Password must not be Blank")
	private String password;

}
