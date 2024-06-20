package kw.kng.repo;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import kw.kng.entities.Product;
import kw.kng.entities.ProductCategory;

@SpringBootTest
public class ProductCategoryRepoTest 
{
	@Autowired
	private ProductCategoryRepo pcrepo;
	
	@Test
	void saveProductCategory()
	{
		ProductCategory pc= new ProductCategory();
		pc.setCategoryName("Books");
		pc.setCategoryDescription("Books Description");
		
		Product pro1= new Product();
		pro1.setName("Core Java");
		pro1.setPrice(new BigDecimal(1000));
		pro1.setImageUrl("image1.png");
		pro1.setSku("ABCD");
		pro1.setActive(true);
		pro1.setCategory(pc);
		pc.getProducts().add(pro1);
		
		Product pro2= new Product();
		pro2.setName("Advanced Java");
		pro2.setPrice(new BigDecimal(2000));
		pro2.setImageUrl("image2.png");
		pro2.setSku("EFGH");
		pro2.setActive(true);
		pro2.setCategory(pc);
		pc.getProducts().add(pro2);
		
		Product pro3= new Product();
		pro3.setName("Spring");
		pro3.setPrice(new BigDecimal(3000));
		pro3.setImageUrl("image3.png");
		pro3.setSku("IJKL");
		pro3.setActive(true);
		pro3.setCategory(pc);
		pc.getProducts().add(pro3);
		
		
		Product pro4= new Product();
		pro4.setName("Spring Boot");
		pro4.setPrice(new BigDecimal(4000));
		pro4.setImageUrl("image4.png");
		pro4.setSku("MNOP");
		pro4.setActive(true);
		pro4.setCategory(pc);
		pc.getProducts().add(pro4);
		
		pcrepo.save(pc);
	}
	
	@Test
	@Transactional
	void fetchProductCategory()
	{
		ProductCategory cat= pcrepo.findById(1L).get();
		System.out.println(cat.getProducts());
	}
	
}
