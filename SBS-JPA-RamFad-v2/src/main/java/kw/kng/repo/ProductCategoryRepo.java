package kw.kng.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.ProductCategory;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> 
{

}
