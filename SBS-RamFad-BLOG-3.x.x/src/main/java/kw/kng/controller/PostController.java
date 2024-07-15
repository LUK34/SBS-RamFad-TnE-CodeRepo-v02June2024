package kw.kng.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kw.kng.dto.PostDto;
import kw.kng.dto.PostResponse;
import kw.kng.service.PostService;

@Tag(
		name="BLOG REST APIs for Post Resource",
		description= "BLOG REST APIs for Post Resource = Create Post, Update Post, Get Post and Delete Post" 
)
@RestController
@RequestMapping("/api/posts")
public class PostController 
{
	private PostService ps;

	public PostController(PostService ps) 
	{
		this.ps = ps;
	}
	
	//POST -> Create a SINGLE Post
	@SecurityRequirement(
			name="Bear Authentication"
	)
	@Operation(
			summary="POST -> Single Post REST API",
			description="POST -> Single Post REST API and save details to Database"
	 )
	@ApiResponse(
			responseCode="201",
			description="HTTP STATUS 201 CREATED"
	 )
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/single")
	public ResponseEntity<PostDto> createPost(@RequestBody @Valid PostDto postDto)
	{
			PostDto savedPostDto = ps.createPostSingle(postDto);
			return new ResponseEntity<>(savedPostDto,  HttpStatus.CREATED);
	}
	
	//POST - Create MULTIPLE Post
	@SecurityRequirement(
			name="Bear Authentication"
	)
	@Operation(
			summary="POST -> Multiple Post REST API",
			description="POST -> Multiple Post REST API and save details to Database"
	 )
	@ApiResponse(
			responseCode="201",
			description="HTTP STATUS 201 CREATED"
	 )
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/multiple")
	public ResponseEntity<List<PostDto>> createPostMultiple(@RequestBody @Valid List<@Valid PostDto> postDto)
	{
			List<PostDto> savedPostList = ps.createPostMultiple(postDto);
			return new ResponseEntity<>(savedPostList, HttpStatus.CREATED);
	}
	
	//GET - Posts by Page : 
	// Link:    {{url}}/api/posts/post-page?pageNo=0&pageSize=5&sortBy=title&sortDir=DESC
	@Operation(
			summary="GET -> All POST by page REST API",
			description="GET -> All POST by page REST API from Database"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	@GetMapping("/post-page") 
	public PostResponse getAllPostPage(
				@RequestParam(value="pageNo", defaultValue="${default.page.number}", required=false) int pageNo,
				@RequestParam(value="pageSize", defaultValue="${default.page.size}", required=false) int pageSize,
				@RequestParam(value="sortBy", defaultValue="${default.page.sort.by}",  required=false) String sortBy,
				@RequestParam(value="sortDir", defaultValue="${default.page.sort.direction}", required=false)String sortDir
			)
	{
		return ps.getAllPostPage(pageNo, pageSize, sortBy, sortDir);
			
	}
	
	//GET -Posts by List
	@Operation(
			summary="GET -> List of POST Details REST API",
			description="GET -> List of POST Details REST API from Database"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	@GetMapping("/post-list")
	public ResponseEntity<List<PostDto>> getAllPostList()
	{
		List<PostDto> getPostList = ps.getAllPostList();
		return ResponseEntity.ok(getPostList);
	}
	
	//GET - Get Post by id
	@Operation(
			summary="GET -> POST Details by id REST API",
			description="GET -> POST Details by id from Database"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long postId)
	{
		PostDto postDto = ps.getPostById(postId);
		return ResponseEntity.ok(postDto);
	}
	
	//PUT - Update POST by id
	@SecurityRequirement(
			name="Bear Authentication"
	)
	@Operation(
			summary="PUT -> Update POST Details by id REST API",
			description="PUT -> Update POST Details by id REST API and save details to Database"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@PathVariable("id") Long postId, @RequestBody @Valid PostDto postDto)
	{
		PostDto updatedPostDto = ps.updatePost(postId, postDto);
		return ResponseEntity.ok(updatedPostDto);
	}
	
	//DELETE- Delete POST by id
	@SecurityRequirement(
			name="Bear Authentication"
	)
	@Operation(
			summary="DELETE -> Delete POST Details by id REST API",
			description="DELETE -> Delete POST Details by id REST API from DB"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePostById(@PathVariable("id") Long postId)
	{
		ps.deletePostById(postId);
		return ResponseEntity.ok("Post with id: "+postId+" deleted successfully in DB!!!");
	}
	
	@Operation(
			summary="GET -> Get POST Details by categoryId REST API",
			description="GET -> Get POST Details by categoryId REST API from DB"
	 )
	@ApiResponse(
			responseCode="200",
			description="HTTP STATUS 200 OK"
	 )
	//GET - Get POSTS by categoryId
	@GetMapping("/category/{id}")
		public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("id") Long categoryId)
		{
			List<PostDto> postDto = ps.getPostsByCategory(categoryId);
			return ResponseEntity.ok(postDto);
		}
	
	
}
