package kw.kng.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
		description="UserDto Model Information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto 
{
	private Long id;
	
	@Schema(
			description="User First Name"
	)
	@NotEmpty(message="User First name should not be NULL or EMPTY") //should not be null or empty
	private String firstName;
	
	@Schema(
			description="User Last Name"
	)
	@NotEmpty(message="User Last name should not be NULL or EMPTY")
	private String lastName;
	

	@Schema(
			description="User Email Address"
	)
	@NotEmpty(message="User Email should not be NULL or EMPTY")//should not be null or empty
	@NotBlank(message="User Email should not be NULL nor should have WHITE SPACE") //should not be null or have white space
	@Email(message="User Email should be VALID") //should have VALID Email
	private String email;
	
}
