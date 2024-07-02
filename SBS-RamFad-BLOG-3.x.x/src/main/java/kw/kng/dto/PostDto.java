package kw.kng.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto 
{
	private Long id;
	
	@NotEmpty(message="Post Title must not be Empty")
	private String title;
	
	@NotEmpty(message="Post Description must not be Empty")
	private String description;

	@NotEmpty(message="Post Content must not be Empty")
	private String content;

}
