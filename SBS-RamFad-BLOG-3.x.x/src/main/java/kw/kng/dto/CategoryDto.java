package kw.kng.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import kw.kng.entites.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto 
{
	private Long id;
	
	@NotEmpty(message="Category Name must not be Empty")
	private String name;
	
	@NotEmpty(message="Category Description must not be Empty")
	@Size(max=30, message="Category Description size should not be more than 30 characters")
	private String description;
	
}
