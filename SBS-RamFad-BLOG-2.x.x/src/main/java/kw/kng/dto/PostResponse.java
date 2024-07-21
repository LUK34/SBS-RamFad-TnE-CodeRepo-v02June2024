package kw.kng.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
		description="PostResponse DTO(Data Transfer Object) to transfer the data between CLIENT and SERVER"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse 
{
	// This is for Pagination logic
	
	private List<PostDto> content;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;

}
