package kw.kng.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Schema(
		description="Category DTO(Data Transfer Object) to transfer the data between CLIENT and SERVER"
)
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
