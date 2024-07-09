package kw.kng.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entites.User;

public interface UserRepo extends JpaRepository<User, Long> 
{
	
	Optional<User> findByEmail(String email);
	Optional<User> findByUsernameOrEmail(String username, String email);
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	
}
