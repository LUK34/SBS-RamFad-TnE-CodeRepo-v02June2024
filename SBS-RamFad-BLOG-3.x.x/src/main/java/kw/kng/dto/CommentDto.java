package kw.kng.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto 
{
	private Long id;
	
	@NotEmpty(message="Comment Name must not be Empty")
	private String name;
	
	@NotEmpty(message="Comment Name must not be Empty")
	@NotBlank(message="Comment Email should not be NULL nor should have WHITE SPACE") //should not be null or have white space
	@Email(message="Comment Email should be VALID") //should have VALID Email
	private String email;
	
	@NotEmpty(message="Comment Name must not be Empty")
	@Size(max=50, message="Comment body size should not be more than 50 characters")
	private String body;

}
