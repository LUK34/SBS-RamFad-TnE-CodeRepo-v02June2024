package kw.kng.repo;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kw.kng.entities.Product;

@SpringBootTest
public class JpaTest 
{
	@Autowired
	private ProductRepo prepo;
	
	@Test
	void saveMethod()
	{
		//create Product
		Product pro = new Product();
		pro.setName("product 1");
		pro.setDescription("product 1 description");
		pro.setSku("100ABC");
		pro.setPrice(new BigDecimal(100));
		pro.setActive(true);
		pro.setImageUrl("pro1.png");
		
		//save Product
		Product savedObject= prepo.save(pro);
		
		//Display product Info
		System.out.println("Product with id " + savedObject.getId() + " is saved into DB.");
		System.out.println(savedObject.toString());
	}
	
	@Test
	void updateUsingSaveMethod()
	{
		//Find or Retrieve the id from the DB
		Long id=5L;
		Product pro= prepo.findById(id).orElseThrow(() -> new RuntimeException("product with id: "+id+" does not exist in DB"));
		
		//Update entity information
		pro.setName("Updated Product 5");
		pro.setDescription("Update product 5 description");
		
		//save updated entity
		prepo.save(pro);
	}
	
	@Test
	void findByIdMethod()
	{
		//Find or Retrieve the id from the DB
		Long id=1L;
		Product pro= prepo.findById(id).orElseThrow(() -> new RuntimeException("product with id: "+id+" does not exist in DB"));
		System.out.println(pro.toString());
	}
	
	@Test
	void saveAllObject()
	{
		//create Product 1
		Product pro1 = new Product();
		pro1.setName("product 8");
		pro1.setDescription("product 8 description");
		pro1.setSku("100ABC");
		pro1.setPrice(new BigDecimal(50));
		pro1.setActive(true);
		pro1.setImageUrl("pro8.png");

		//create Product 2
		Product pro2 = new Product();
		pro2.setName("product 9");
		pro2.setDescription("product 9 description");
		pro2.setSku("100ABC");
		pro2.setPrice(new BigDecimal(150));
		pro2.setActive(true);
		pro2.setImageUrl("pro9.png");
		
		//create Product 3
		Product pro3 = new Product();
		pro3.setName("product 10");
		pro3.setDescription("product 10 description");
		pro3.setSku("100ABC");
		pro3.setPrice(new BigDecimal(400));
		pro3.setActive(true);
		pro3.setImageUrl("pro10.png");

		//create Product 4
		Product pro4 = new Product();
		pro4.setName("product 11");
		pro4.setDescription("product 11 description");
		pro4.setSku("100ABC");
		pro4.setPrice(new BigDecimal(550));
		pro4.setActive(true);
		pro4.setImageUrl("pro11.png");
				
		//create Product 5
		Product pro5 = new Product();
		pro5.setName("product 12");
		pro5.setDescription("product 12 description");
		pro5.setSku("100ABC");
		pro5.setPrice(new BigDecimal(1400));
		pro5.setActive(true);
		pro5.setImageUrl("pro12.png");

		//create Product 2
		Product pro6 = new Product();
		pro6.setName("product 13");
		pro6.setDescription("product 13 description");
		pro6.setSku("100ABC");
		pro6.setPrice(new BigDecimal(1575));
		pro6.setActive(true);
		pro6.setImageUrl("pro13.png");
		
		//create Product 3
		Product pro7 = new Product();
		pro7.setName("product 14");
		pro7.setDescription("product 14 description");
		pro7.setSku("100ABC");
		pro7.setPrice(new BigDecimal(50));
		pro7.setActive(true);
		pro7.setImageUrl("pro14.png");

		//create Product 4
		Product pro8 = new Product();
		pro8.setName("product 15");
		pro8.setDescription("product 15 description");
		pro8.setSku("100ABC");
		pro8.setPrice(new BigDecimal(250));
		pro8.setActive(true);
		pro8.setImageUrl("pro15.png");
		
		
		Product pro9 = new Product();
		pro9.setName("product 16");
		pro9.setDescription("product 16 description");
		pro9.setSku("100ABC");
		pro9.setPrice(new BigDecimal(750));
		pro9.setActive(true);
		pro9.setImageUrl("pro16.png");

		//create Product 2
		Product pro10 = new Product();
		pro10.setName("product 17");
		pro10.setDescription("product 17 description");
		pro10.setSku("100ABC");
		pro10.setPrice(new BigDecimal(700));
		pro10.setActive(true);
		pro10.setImageUrl("pro17.png");
		
		//create Product 3
		Product pro11 = new Product();
		pro11.setName("product 18");
		pro11.setDescription("product 18 description");
		pro11.setSku("100ABC");
		pro11.setPrice(new BigDecimal(400));
		pro11.setActive(true);
		pro11.setImageUrl("pro18.png");

		//create Product 4
		Product pro12 = new Product();
		pro12.setName("product 19");
		pro12.setDescription("product 19 description");
		pro12.setSku("100ABC");
		pro12.setPrice(new BigDecimal(550));
		pro12.setActive(true);
		pro12.setImageUrl("pro19.png");
				
		//create Product 5
		Product pro13 = new Product();
		pro13.setName("product 20");
		pro13.setDescription("product 20 description");
		pro13.setSku("100ABC");
		pro13.setPrice(new BigDecimal(1400));
		pro13.setActive(true);
		pro13.setImageUrl("pro20.png");

		//create Product 2
		Product pro14 = new Product();
		pro14.setName("product 21");
		pro14.setDescription("product 21 description");
		pro14.setSku("100ABC");
		pro14.setPrice(new BigDecimal(1575));
		pro14.setActive(true);
		pro14.setImageUrl("pro21.png");
		
		//create Product 3
		Product pro15 = new Product();
		pro15.setName("product 22");
		pro15.setDescription("product 22 description");
		pro15.setSku("100ABC");
		pro15.setPrice(new BigDecimal(50));
		pro15.setActive(true);
		pro15.setImageUrl("pro22.png");

		//create Product 4
		Product pro16 = new Product();
		pro16.setName("product 23");
		pro16.setDescription("product 23 description");
		pro16.setSku("100ABC");
		pro16.setPrice(new BigDecimal(250));
		pro16.setActive(true);
		pro16.setImageUrl("pro23.png");
		
		
		prepo.saveAll(List.of(pro1,pro2, pro3, pro4,pro5,pro6, pro7, pro8, pro9,pro10,pro11,pro12,pro13,pro14,pro15,pro16));
		
		
	}
	
	
	@Test
	void findAllMethod()
	{
		List<Product> prods= prepo.findAll();
		prods.forEach((p) -> System.out.println(p.getName()) );
	}
	
	
	@Test
	void deleteById()
	{
		Long id=1L;
		Product pro= prepo.findById(id).orElseThrow(() -> new RuntimeException("product with id: "+id+" does not exist in DB"));
		
		prepo.deleteById(id);
		System.out.println("Product with id: "+id+" was deleted from database successfully.");
	}
	
	/*
	 @Test
	 void deleteByEntityMethod()
	 {
	 	//find an entity by id
	 	 Long id=2L;
	 	 Product pro= prepo.findById(id).orElseThrow(() -> new RuntimeException("product with id: "+id+" does not exist in DB"));
	 	 
	 	 //delete(entity) //this will delete the entire entity.
	 	 prepo.delete(pro);
	 	 
	 }
	 */
	
	/*
	@Test
	void deleteAllMethod()
	{
		//prepo.deleteAll();//This will delete all records in the database table
		
		Product pro1 = prepo.findById(1L).get();
		Product pro2 = prepo.findById(2L).get();
		
		prepo.deleteAll(List.of(pro1,pro2));
	}
	*/
	
	@Test
	void countMethod()
	{
		long count= prepo.count();
		System.out.println(count);
	}
	
	@Test
	void existsByidMethod()
	{
		Long id=5L;
		
		boolean result= prepo.existsById(id);
		
		System.out.println("Value: "+result);
	}
	
	

}
