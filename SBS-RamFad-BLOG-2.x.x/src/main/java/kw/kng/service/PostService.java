package kw.kng.service;

import java.util.List;

import kw.kng.dto.PostDto;
import kw.kng.dto.PostResponse;

public interface PostService 
{
	PostDto createPostSingle(PostDto postDto);
	List<PostDto> createPostMultiple(List<PostDto> postDto);
	List<PostDto> getAllPostList();
	PostResponse getAllPostPage(int pageNo, int pageSize, String sortBy, String sortDir);
	PostDto getPostById(Long postId);
	PostDto updatePost(Long postId, PostDto postDto);
	void deletePostById(Long postId);
	List<PostDto> getPostsByCategory(Long categoryId);

}
