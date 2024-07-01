package kw.kng.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.Users;

public interface UserRepo extends JpaRepository<Users, Long> 
{
	Optional<Users> findByEmail(String email);
}
