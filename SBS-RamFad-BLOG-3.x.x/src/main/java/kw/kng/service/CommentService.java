package kw.kng.service;

import java.util.List;

import kw.kng.dto.CommentDto;

public interface CommentService 
{
	CommentDto createComment(long postId, CommentDto commentDto);
	List<CommentDto> getCommentsByPostId(long postId);
	CommentDto getCommentById(Long postId, Long commentId);
	CommentDto updateCommentById(Long postId, Long commentId, CommentDto commentDto);
	void deleteCommentByCommentId(Long postId, Long commentId);
}
