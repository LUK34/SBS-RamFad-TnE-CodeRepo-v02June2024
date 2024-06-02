package kw.kng.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kw.kng.entities.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> 
{

}
