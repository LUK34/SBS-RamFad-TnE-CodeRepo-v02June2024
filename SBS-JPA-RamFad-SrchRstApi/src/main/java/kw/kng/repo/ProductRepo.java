package kw.kng.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kw.kng.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Long> 
{
	@Query("SELECT p from Product p"
			+ " where p.name LIKE CONCAT('%', :query, '%')"
			+ "OR p.description LIKE CONCAT('%', :query, '%')")
	List<Product> searchProducts(String query);
	
	
	@Query(value="SELECT * FROM JPA_PRODUCT p"
			+ " where p.name LIKE CONCAT('%', :query, '%')"
			+ "OR p.description LIKE CONCAT('%', :query, '%')", nativeQuery=true)
	List<Product> searchProductsSQL(String query);
}
