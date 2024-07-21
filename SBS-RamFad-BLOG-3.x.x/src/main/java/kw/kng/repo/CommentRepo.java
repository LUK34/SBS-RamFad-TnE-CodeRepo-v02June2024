package kw.kng.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entites.Comment;
import kw.kng.entites.Post;

public interface CommentRepo extends JpaRepository<Comment, Long> 
{
		List<Comment> findByPostId(long postId);
		
		List<Comment> findByEmail(String email);
}
