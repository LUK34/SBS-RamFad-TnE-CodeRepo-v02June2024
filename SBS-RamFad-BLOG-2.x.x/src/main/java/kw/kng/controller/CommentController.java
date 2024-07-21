package kw.kng.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kw.kng.dto.CommentDto;
import kw.kng.service.CommentService;

@Tag(
		name="BLOG REST APIs for Comment Resource",
		description= "BLOG REST APIs for Comment Resource = Create Comment, Update Comment, Get Comment and Delete Comment" 
)
@RestController
@RequestMapping("/api")
public class CommentController 
{
	private CommentService cs;

	public CommentController(CommentService cs)
	{
		this.cs = cs;
	}
	
	
	//POST -> Create a comment based on Post id -> 1 post , many comment
	@SecurityRequirement(
			name="Bear Authentication"
	)
	@Operation(
			summary="POST -> Single Comment by Post id REST API",
			description="POST -> Single Comment by Post id REST API and save the details to DB"
	 )
	@ApiResponse(
			responseCode="201",
			description="HTTP STATUS 201 CREATED"
	 )
	@PostMapping("/posts/{id}/comments/single")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value="id") Long id, 
																								@RequestBody @Valid CommentDto commentDto)
	{
		CommentDto savedCommentDto = cs.createComment(id, commentDto);
		return new ResponseEntity<>(savedCommentDto, HttpStatus.CREATED);
	}
	
	
	@Operation(
			summary="GET -> List of Comments by Post id REST API",
			description="GET -> List of Comment by Post id REST API from DB"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	@GetMapping("/posts/{id}/comments")
	public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable(value="id") Long postId)
	{
		List<CommentDto> commentList = cs.getCommentsByPostId(postId);
		return ResponseEntity.ok(commentList);
	}
	
	@Operation(
			summary="GET -> List of Comments by Comment id and Post id REST API",
			description="GET -> List of Comments by Comment id and Post id  REST API from DB"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	@GetMapping("/posts/{postid}/comments/{commentid}")
	public ResponseEntity<CommentDto> getCommentByCommentIdAndPostId(
																																		@PathVariable(value="postid") Long postId,
																																		@PathVariable(value="commentid") Long commentId
																																	)
	{
		CommentDto commentDto = cs.getCommentById(postId, commentId);
		return ResponseEntity.ok(commentDto);
	}
	
	@SecurityRequirement(
			name="Bear Authentication"
	)
	@Operation(
			summary="PUT -> Update Comments by Comment id and Post id REST API",
			description="PUT -> Update Comments by Comment id and Post id REST API and save the details to DB"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	@PutMapping("/posts/{postid}/comments/{commentid}")
	public ResponseEntity<CommentDto> updateCommentByCommentIdAndPostId(
																																		@PathVariable(value="postid") Long postId,
																																		@PathVariable(value="commentid") Long commentId,
																																		@RequestBody @Valid CommentDto commentDto
																																	)
	{
		CommentDto updatedCommentDto = cs.updateCommentById(postId, commentId, commentDto);
		return ResponseEntity.ok(updatedCommentDto);
	}
	
	@SecurityRequirement(
			name="Bear Authentication"
	)
	@Operation(
			summary="DELETE -> Delete Comments by Comment id and Post id REST API",
			description="DELETE -> Delete Comments by Comment id and Post id REST API from DB"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	@DeleteMapping("/posts/{postid}/comments/{commentid}")
	public ResponseEntity<String> deleteCommentByCommentIdAndPostId(
																																		@PathVariable(value="postid") Long postId,
																																		@PathVariable(value="commentid") Long commentId
																																	)
	{
		 cs.deleteCommentByCommentId(postId, commentId);
		 return ResponseEntity.ok("Comment with id: "+commentId+" for the post with id: "+postId+" deleted successfully from DB!!!");
	}
	
	
	
	
	
}
