package kw.kng.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Long> 
{

}
