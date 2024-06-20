package kw.kng.repo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kw.kng.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Long>
{
	/*
	 Returns the found product entry by using its name as search criteria. If no product enty is
	 found, this method return null.
	 */
	public Product findByName(String name);

	/*
	 Return an Optional which contains the found product entry by using its id
	 as search criteria. If no product entry is found, this method returns an empty Optional.
	 */
	Optional<Product> findById(Long id);
	
	/*
	 	
	 	Returns the found list of product entries whose titel or description is given
	 	as a method parameter. If no product entries is found, this method return an empty list.
	 
	 */
	List<Product> findByNameOrDescription(String name, String description);
	
	List<Product> findByNameAndDescription(String name, String description);
	
	Optional<Product> findDistinctByName(String name);
	
	List<Product> findByPriceGreaterThan(BigDecimal price);
	
	List<Product> findByPriceLessThan(BigDecimal price);
	
	//`%product%`;
	List<Product> findByNameContaining(String name);
	
	//`%product%`
	List<Product> findByNameLike(String name);
	
	List<Product> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);
	
	List<Product> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
	
	List<Product> findByNameIn(List<String> names);
	
	List<Product> findFirst2ByOrderByNameAsc();
	
	List<Product> findTop2ByOrderByPriceDesc();
	
	//Define JPQL query using @Query annotaion with index or position parameters
	@Query("select p from Product p where p.name=?1 or p.description=?2")
	Product findByNameOrDescriptionJPQLIndexParam(String name, String description);
	
	//Define JPQL query using @Query annotaion with Named parameters
	@Query("select p from Product p where p.name=:name or p.description= :description")
	Product findByNameOrDescriptionJPQLPositionParam(@Param("name") String name,@Param("description") String description);
	
	//Define Native Query
	@Query(value="select * from JPA_PRODUCT p where p.name =?1 or p.description =?2", nativeQuery=true)
	Product  findByNameOrDescriptionNativeQueryPositionParam(String name, String description);
	
	@Query(value="select * from JPA_PRODUCT p where p.name = :name or p.description =:description", nativeQuery=true)
	Product  findByNameOrDescriptionNativeQueryNamedParam(@Param("name") String name, @Param("description")  String description);
	
	//Named Query START
	List<Product> findByPrice(@Param("price") BigDecimal price);
	List<Product> findAllOrderByNameDesc();
	//Named Query END
	
	//Named Native Query START
	List<Product>  findByDescription(String description);
	List<Product> findAllOrderByNameASC();
	//Named Native Query END


}
