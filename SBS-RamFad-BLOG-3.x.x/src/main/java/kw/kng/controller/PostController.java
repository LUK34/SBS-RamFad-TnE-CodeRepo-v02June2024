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

import jakarta.validation.Valid;
import kw.kng.dto.PostDto;
import kw.kng.dto.PostResponse;
import kw.kng.service.PostService;


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
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/single")
	public ResponseEntity<PostDto> createPost(@RequestBody @Valid PostDto postDto)
	{
			PostDto savedPostDto = ps.createPostSingle(postDto);
			return new ResponseEntity<>(savedPostDto,  HttpStatus.CREATED);
	}
	
	//POST - Create MULTIPLE Post
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/multiple")
	public ResponseEntity<List<PostDto>> createPostMultiple(@RequestBody @Valid List<@Valid PostDto> postDto)
	{
			List<PostDto> savedPostList = ps.createPostMultiple(postDto);
			return new ResponseEntity<>(savedPostList, HttpStatus.CREATED);
	}
	
	//GET - Posts by Page : 
	// Link:    {{url}}/api/posts/post-page?pageNo=0&pageSize=5&sortBy=title&sortDir=DESC
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
	@GetMapping("/post-list")
	public ResponseEntity<List<PostDto>> getAllPostList()
	{
		List<PostDto> getPostList = ps.getAllPostList();
		return ResponseEntity.ok(getPostList);
	}
	
	//GET - Get Post by id
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long postId)
	{
		PostDto postDto = ps.getPostById(postId);
		return ResponseEntity.ok(postDto);
	}
	
	//PUT - Update POST by id
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@PathVariable("id") Long postId, @RequestBody @Valid PostDto postDto)
	{
		PostDto updatedPostDto = ps.updatePost(postId, postDto);
		return ResponseEntity.ok(updatedPostDto);
	}
	
	//DELETE- Delete POST by id
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePostById(@PathVariable("id") Long postId)
	{
		ps.deletePostById(postId);
		return ResponseEntity.ok("Post with id: "+postId+" deleted successfully in DB!!!");
	}
	
	
	
}
