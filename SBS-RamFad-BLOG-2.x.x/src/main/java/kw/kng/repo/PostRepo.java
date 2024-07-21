package kw.kng.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entites.Post;

public interface PostRepo extends JpaRepository<Post, Long> 
{
	
	List<Post> findByCategoryId(Long categoryId);
	
}
