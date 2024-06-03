package kw.kng.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entites.Expense;

public interface ExpenseRepo extends JpaRepository<Expense, Long> 
{

}
