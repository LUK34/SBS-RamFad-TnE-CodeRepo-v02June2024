package kw.kng.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entites.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> 
{
		List<Comment> findByPostId(long postId);
}
