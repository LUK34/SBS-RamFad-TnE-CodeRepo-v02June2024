package kw.kng.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.Transaction;



public interface TransactionRepo extends JpaRepository<Transaction, Long> 
{
	List<Transaction> findByAccountIdOrderByTimestampDesc(Long accountId);
	
}
