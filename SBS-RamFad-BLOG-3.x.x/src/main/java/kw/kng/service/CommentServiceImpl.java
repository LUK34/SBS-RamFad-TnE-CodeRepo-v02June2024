package kw.kng.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kw.kng.dto.CommentDto;
import kw.kng.entites.Comment;
import kw.kng.entites.Post;
import kw.kng.exceptions.BlogApiException;
import kw.kng.exceptions.ResourceNotFoundException;
import kw.kng.repo.CommentRepo;
import kw.kng.repo.PostRepo;

@Service
public class CommentServiceImpl implements CommentService 
{

	private CommentRepo crepo;
	private ModelMapper modelMapper;
	private PostRepo prepo;
	
	public CommentServiceImpl(CommentRepo crepo,ModelMapper modelMapper,PostRepo prepo) 
	{
		this.crepo = crepo;
		this.modelMapper=modelMapper;
		this.prepo=prepo;
	}

	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) 
	{
		//Transfer data from Comment DTO to Comment ENTITY class
		Comment comment = modelMapper.map(commentDto, Comment.class);
		
		//Retrieve Post ENTITY by id;
		Post post = prepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post with id: "+postId+"  does not exist in DB."));
		
		
		//Set post to Comment ENTITY
		comment.setPost(post);
		
		//Save comment ENTITY to PERSISTENCE
		Comment savedComment = crepo.save(comment);
		
		//Transfer comment ENTITY to DTO
		return modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public List<CommentDto> getCommentsByPostId(long postId) 
	{
		//Retrieve Post ENTITY by id;
		Post post = prepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post with id: "+postId+"  does not exist in DB."));
				
		//Retrieve Comment by Post id and store the data in ENTITY list
		List<Comment> comments = crepo.findByPostId(post.getId());
				
		//Transfer the data from ENTITY to DTO
		return comments.stream().map( c -> modelMapper.map(c, CommentDto.class)).collect(Collectors.toList());
	}

	@Override
	public CommentDto getCommentById(Long postId, Long commentId) 
	{
		
        // retrieve post entity by id
        Post post = prepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post with id: "+postId+"  does not exist in DB."));

        // retrieve comment by id
        Comment comment = crepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment with id= " + commentId + " for post id= "+postId+" does not exist in DB."));

        if( !(comment.getPost().getId().equals(post.getId())) )
        	{
        	 throw new BlogApiException("Comment with id= "+commentId+" does not belong to post with id= "+postId);
        }
        
        return modelMapper.map(comment, CommentDto.class);
		
	}

	@Override
	public CommentDto updateCommentById(Long postId, Long commentId,CommentDto commentDto) 
	{
		
		        // retrieve post entity by id
		        Post post = prepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post with id: "+postId+"  does not exist in DB."));
		
		        // retrieve comment by id
		        Comment commentById = crepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment with id= " + commentId + " for post id= "+postId+" does not exist in DB."));
		
		        if( !(commentById.getPost().getId().equals(post.getId())) )
		        	{
		        	 throw new BlogApiException("Comment with id= "+commentId+" does not belong to post with id= "+postId);
		        }
        
				//Transfer the DTO details from DTO to that specific `Comment` by comment id ENTITY
				commentById.setName(commentDto.getName());
				commentById.setBody(commentDto.getBody());
				commentById.setEmail(commentDto.getEmail());
				
				//Save the updated `Comment` ENTITY to PERSISTENCE
				Comment updatedComment = crepo.save(commentById);
				
				
				return modelMapper.map(updatedComment, CommentDto.class);
	}

	@Override
	public void deleteCommentByCommentId(Long postId, Long commentId) 
	{
		   // retrieve post entity by id
        Post post = prepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post with id: "+postId+"  does not exist in DB."));

        // retrieve comment by id
        Comment commentById = crepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment with id= " + commentId + " for post id= "+postId+" does not exist in DB."));

        if( !(commentById.getPost().getId().equals(post.getId())) )
        	{
        	 throw new BlogApiException("Comment with id= "+commentId+" does not belong to post with id= "+postId);
        }

		crepo.delete(commentById);
		
	}

	
	
}

/*
  @Override
    public CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment = commentRepository.save(comment);
        return mapToDTO(updatedComment);
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return mapToDTO(comment);
    }



 */



