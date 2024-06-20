package kw.kng.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.User;

public interface UserRepo extends JpaRepository<User, Long> 
{

}
