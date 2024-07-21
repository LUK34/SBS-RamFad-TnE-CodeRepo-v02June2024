package kw.kng.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entites.Role;

public interface RoleRepo extends JpaRepository<Role, Long> 
{
	
	Optional<Role> findByName(String name);
	
	
}
