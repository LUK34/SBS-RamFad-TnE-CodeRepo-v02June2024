package kw.kng.controller;

import java.util.List;

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

import jakarta.validation.Valid;
import kw.kng.dto.CommentDto;
import kw.kng.service.CommentService;

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
	@PostMapping("/posts/{id}/comments/single")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value="id") Long id, 
																								@RequestBody @Valid CommentDto commentDto)
	{
		CommentDto savedCommentDto = cs.createComment(id, commentDto);
		return new ResponseEntity<>(savedCommentDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/posts/{id}/comments")
	public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable(value="id") Long postId)
	{
		List<CommentDto> commentList = cs.getCommentsByPostId(postId);
		return ResponseEntity.ok(commentList);
	}
	
	@GetMapping("/posts/{postid}/comments/{commentid}")
	public ResponseEntity<CommentDto> getCommentByCommentIdAndPostId(
																																		@PathVariable(value="postid") Long postId,
																																		@PathVariable(value="commentid") Long commentId
																																	)
	{
		CommentDto commentDto = cs.getCommentById(postId, commentId);
		return ResponseEntity.ok(commentDto);
	}
	
	
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
