package kw.kng.repo;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kw.kng.entities.Product;

@SpringBootTest
public class NamedQueryTest 
{
	@Autowired
	private ProductRepo	 prepo;
	
	@Test
	void namedJPQLQuery()
	{
		List<Product> pro= prepo.findByPrice(new BigDecimal(100));
		pro.forEach((p) ->{
			System.out.println("Product Id: " + p.getId());
			System.out.println("Product Desription: " + p.getDescription());
		});
	}

	@Test
	void namedJPQLQueries()
	{
		List<Product> pro= prepo.findAllOrderByNameDesc();
		pro.forEach((p) ->{
			System.out.println("Product Id: " + p.getId());
			System.out.println("Product Desription: " + p.getDescription());
		});
	}
	
	@Test
	void nameNativeQuery()
	{
		List<Product> pro= prepo.findByDescription("product 2 description");
		pro.forEach((p) ->{
			System.out.println("Product Id: " + p.getId());
			System.out.println("Product Desription: " + p.getDescription());
		});
	}
	
	@Test
	void nameNativeQueries()
	{
		List<Product> pro= prepo.findAllOrderByNameASC();
		pro.forEach((p) ->{
			System.out.println("Product Id: " + p.getId());
			System.out.println("Product Desription: " + p.getDescription());
		});
	}

}
