package kw.kng.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
		description="EmployeeDto Model Information"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto
{
	private Long id;
	
	@Schema(
			description="EmployeeDto First Name"
	)
	@NotEmpty(message="Employee First Name must not be Empty")
	private String firstName;
	
	@Schema(
			description="EmployeeDto Last Name"
	)
	@NotEmpty(message="Employee Last Name must not be Empty")
	private String lastName;
	
	@Schema(
			description="EmployeeDto Email"
	)
	@NotEmpty(message="Employee Email must not be Empty")
	@NotBlank(message="Employee Email should not be NULL nor should have WHITE SPACE") //should not be null or have white space
	@Email(message="Employee Email should be VALID") //should have VALID Email
	private String email;

}
