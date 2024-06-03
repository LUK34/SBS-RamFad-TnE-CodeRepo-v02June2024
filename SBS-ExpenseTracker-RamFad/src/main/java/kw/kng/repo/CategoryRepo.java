package kw.kng.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entites.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> 
{

}
