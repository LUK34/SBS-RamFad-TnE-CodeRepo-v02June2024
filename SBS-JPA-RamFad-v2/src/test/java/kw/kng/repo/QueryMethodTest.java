package kw.kng.repo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kw.kng.entities.Product;

@SpringBootTest
public class QueryMethodTest 
{
	
	@Autowired
	private ProductRepo	 prepo;

	@Test
	void findByNameMethod()
	{
		Product pro = prepo.findByName("product 8");
		System.out.println(pro.getId());
		System.out.println(pro.getName());
		System.out.println(pro.getDescription());
	}
	
	@Test
	void findByIdMethod()
	{
		Product product = prepo.findById(8L).get();
		
		System.out.println(product.getId());
		System.out.println(product.getName());
		System.out.println(product.getDescription());
	}

	@Test
	void findByNameOrDescriptionMethod()
	{
		List<Product> products=prepo.findByNameOrDescription("product 8", "product 8 description");
		products.forEach((p) ->{
								System.out.println(p.getId());
								System.out.println(p.getName());
		});
	}
	
	@Test
	void findByNameAndDescription()
	{
		List<Product> products=prepo.findByNameAndDescription("product 2", "product 2 description");
		products.forEach((p) ->{
								System.out.println(p.getId());
								System.out.println(p.getName());
		});
	}
	
	@Test
	void findDistinctByNameMethod()
	{
		Product pro= prepo.findDistinctByName("product 8").get();
		System.out.println(pro.getId());
		System.out.println(pro.getName());
		System.out.println(pro.getDescription());
	}

	@Test
	void findByPriceGreaterThanMethod()
	{
		List<Product> pro = prepo.findByPriceGreaterThan(new BigDecimal(100));
		pro.forEach((p) ->
		{
			System.out.println(p.getId());
			System.out.println(p.getName());
		});
	}
	
	@Test
	void findByPriceLessThanMethod()
	{
		List<Product> pro = prepo.findByPriceLessThan(new BigDecimal(100));
		pro.forEach((p) ->
		{
			System.out.println(p.getId());
			System.out.println(p.getName());
		});
	}
	
	@Test
	void findByNameContainingMethod()
	{
		List<Product> pros = prepo.findByNameContaining("product 8");
		pros.forEach((p) ->
		{
			System.out.println(p.getId());
			System.out.println(p.getName());
		});
		
	}
	
	@Test
	void findByNameLikeMethod()
	{
		List<Product> pro = prepo.findByNameLike("product 14");
		pro.forEach((p) ->
		{
			System.out.println(p.getId());
			System.out.println(p.getName());
		});
	}
	
	@Test
	void findByPriceBetweenMethod()
	{
		List<Product> products = prepo.findByPriceBetween(new BigDecimal(100), new BigDecimal(300));
		
		products.forEach((p) ->
		{
			System.out.println(p.getId());
			System.out.println(p.getName());
		});
	}
	
	@Test
	void findByDateCreatedBetweenMethod()
	{
		//Start date
		LocalDateTime startDate= LocalDateTime.of(2022, 02,8,17,48,33);
		
		//End date
		LocalDateTime endDate= LocalDateTime.of(2022, 04,8,18,15,33);	
		
		List<Product> pros= prepo.findByCreatedDateBetween(startDate, endDate);
		
		pros.forEach((p) ->
		{
			System.out.println(p.getId());
			System.out.println(p.getName());
		});
		
	}
	
	@Test
	void findByNameInMethod()
	{
		List<Product> products = prepo.findByNameIn(List.of("product 8", "product 9"));
		products.forEach((p) ->
		{
			System.out.println(p.getId());
			System.out.println(p.getName());
		});
	}
	
	@Test
	void findFirst2ByOrderByNameAscMethod()
	{
		List<Product> products= prepo.findFirst2ByOrderByNameAsc();
		products.forEach((p) ->{
			System.out.println(p.getId());
			System.out.println(p.getName());
		});
	}
	
	
	@Test
	void findTop2ByOrderByPriceDescMethod()
	{
		List<Product> products= prepo.findTop2ByOrderByPriceDesc();
		products.forEach((p) ->{
			System.out.println(p.getId());
			System.out.println(p.getName());
		});
	}
	
}
