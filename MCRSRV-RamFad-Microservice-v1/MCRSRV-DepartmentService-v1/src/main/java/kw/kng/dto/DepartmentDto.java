package kw.kng.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
		description="DepartmentDto Model Information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto
{
	private Long id;
	
	@Schema(
			description="DepartmentDto Department Name"
	)
	@NotEmpty(message="Department Name must not be Empty")
	private String departmentName;
	
	@Schema(
			description="DepartmentDto Department Description"
	)
	private String departmentDescription;
	
	@Schema(
			description="DepartmentDto Department Code"
	)
	@NotEmpty(message="Department Code should not be NULL or EMPTY")//should not be null or empty
	@NotBlank(message="Department Code should not be NULL nor should have WHITE SPACE") //should not be null or have white space
	private String departmentCode;

}
