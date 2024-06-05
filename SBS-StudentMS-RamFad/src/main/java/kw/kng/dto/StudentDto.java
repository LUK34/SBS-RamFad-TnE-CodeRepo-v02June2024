package kw.kng.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto 
{	
	private Long id;
	
	@NotEmpty(message= "Student first name should not be empty")
	private String firstName;
	
	@NotEmpty(message= "Student last name should not be empty")
	private String lastName;
	
	@NotEmpty(message= "Student email should not be empty")
	@Email
	private String email;
	
	private String status;
	
}


/*
We need to apply validations in dto class. Because data is transfered from dto <-> entity. 
So priortize the validations in dto class.

*/
