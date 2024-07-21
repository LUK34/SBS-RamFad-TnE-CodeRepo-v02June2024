package kw.kng.service;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kw.kng.dto.PostDto;
import kw.kng.dto.PostResponse;
import kw.kng.entites.Category;
import kw.kng.entites.Post;
import kw.kng.exceptions.ResourceNotFoundException;
import kw.kng.repo.CategoryRepo;
import kw.kng.repo.PostRepo;

@Service
public class PostServiceImpl implements PostService 
{
	private PostRepo prepo;
	private CategoryRepo crepo;
	private ModelMapper modelMapper;

	public PostServiceImpl(PostRepo prepo,CategoryRepo crepo, ModelMapper modelMapper) 
	{
		this.prepo = prepo;
		this.crepo=crepo;
		this.modelMapper= modelMapper;
	}

	@Override
	public PostDto createPostSingle(PostDto postDto) 
	{
		//Find whether the `category id` for that given `post` exist or not. If exist transfer that `category id` to `Category` ENTITY.
		Category category = crepo.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("The Category id: "+postDto.getCategoryId()+" for the given Post does not exist in DB!!!"));
		
		// Transfer DTO to ENTITY
		Post post = modelMapper.map(postDto, Post.class);
		post.setCategory(category);
		
		//Save the ENTITY details into PERSISTANCE layer
		Post savedPost = prepo.save(post);
		
		//After saving transfer ENTITY to DTO
		return modelMapper.map(savedPost, PostDto.class); 
				
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public List<PostDto> createPostMultiple(List<PostDto> postDto)
	{
		// Transfer the List of POST from DTO to ENTITY
		List<Post> postList = postDto.stream().map(dto -> 
		{	
			//Map each DTO to ENTITY
			Post post = modelMapper.map(dto, Post.class);
			
			//Find whether the `category id` for that given `post` exist or not. If exist transfer that `category id` to `Category` ENTITY.
			Category category = crepo.findById(dto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("The Category id: "+dto.getCategoryId()+" for the given Post does not exist in DB!!!"));
		
			post.setCategory(category);
			
			return post;
			
		}).collect(Collectors.toList());
				
		//Save the List of POST in ENTITY using PERSISTENCE
		List<Post> savedPostList = prepo.saveAll(postList);
		
		//Transfer savedPostList to DTO
		return  savedPostList.stream().map(d -> modelMapper.map(d, PostDto.class)).collect(Collectors.toList());
		
	}


	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public PostResponse getAllPostPage(int pageNo, int pageSize, String sortBy, String sortDir) 
	{
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
						  Sort.by(sortBy).ascending() :
						  Sort.by(sortBy).descending();
		
		//Create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		//.of(pageNo, pageSize, sortBy);
		
		//Find the List of POSTs and save it in ENTITY as LIST
		Page<Post> postPages = prepo.findAll(pageable);
		
		//Get content for page object
		List<Post> postList = postPages.getContent();
		
		//Transfer ENTITY to DTO
		 List<PostDto> content= postList.stream().map((p) ->modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
	
			PostResponse postResponse = new PostResponse();
			postResponse.setContent(content);
			postResponse.setPageNo(postPages.getNumber());
			postResponse.setPageSize(postPages.getSize());
			postResponse.setTotalElements(postPages.getTotalElements());
			postResponse.setTotalPages(postPages.getTotalPages());
			postResponse.setLast(postPages.isLast());
		
	//	return content;
			return postResponse;
	}
	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public List<PostDto> getAllPostList()
	{
		//Find the List of Post and save it in ENTITY as List
		List<Post> postList = prepo.findAll();
		
		//Transfer ENTITY to DTO
		return postList.stream().map((u) -> modelMapper.map(u, PostDto.class)).collect(Collectors.toList());
	}

	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public PostDto getPostById(Long postId) 
	{
		//Search in DB if the data exist or not. If not throw `ResourceNotFoundException`
		Post post = prepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post with id: "+postId+"  does not exist in DB."));
		
		//Transfer ENTITY to DTO
		return modelMapper.map(post, PostDto.class);
	}
	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public PostDto updatePost(Long postId, PostDto postDto) 
	{
		//Search in DB if the data exist or not. If not throw `ResourceNotFoundException`
		Post post = prepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post with id:  "+postId+"  does not exist in DB."));
	
		//Find whether the `category id` for that given `post` exist or not. If exist transfer that `category id` to `Category` ENTITY.
		Category category = crepo.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("The Category id: "+postDto.getCategoryId()+" for the given Post does not exist in DB!!!"));
				
		
		//Transfer the DTO details to ENTITY
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		post.setCategory(category);
		
		//Save the ENTITY details to PERSISTANCE
		Post updatedPost = prepo.save(post);
		
		//Transfer ENTITY to DTO
		return modelMapper.map(updatedPost, PostDto.class);
	}
	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public void deletePostById(Long postId) 
	{
		//Search in DB if the data exist or not. If not throw `ResourceNotFoundException`
		Post post = prepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post with id:  "+postId+"  does not exist in DB."));
				
		//Delete the POST in PERSISTENCE
		prepo.delete(post);
	}
	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public List<PostDto> getPostsByCategory(Long categoryId) 
	{
		//Find whether the `category id` for that given `post` exist or not. If exist transfer that `category id` to `Category` ENTITY.
		Category category = crepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("The Category id: "+categoryId+" for the given Post does not exist in DB!!!"));
	
		List<Post> posts= prepo.findByCategoryId(categoryId);
		
		return posts.stream().map((p) -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		
	}
	// ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	

}
