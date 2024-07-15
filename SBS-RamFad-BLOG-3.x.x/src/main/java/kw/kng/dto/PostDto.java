package kw.kng.dto;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto 
{
/* One Post -> Many Comments -> One to Many Mapping Bi-directional*/
	/* 1 Category -> Many blog posts -> 1 to Many  Bi-Directional Mapping  */
	private Long id;
	
	@NotEmpty(message="Post Title must not be Empty")
	private String title;
	
	@NotEmpty(message="Post Description must not be Empty")
	private String description;

	@NotEmpty(message="Post Content must not be Empty")
	//@Size(max=30, message="Post Content size should not be more than 30 characters")
	private String content;
	
	//private Set<CommentDto> comments;
	
	private Long categoryId;

}
