package kw.kng.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kw.kng.entities.Product;

@SpringBootTest
public class JpqlQueryTest 
{
	@Autowired
	private ProductRepo prepo;
	
	@Test
	void findByNameOrDescriptionMethod()
	{
		Product pro= prepo.findByNameOrDescriptionJPQLIndexParam("product 15", "product 15 description");
		System.out.println(pro.getId());
		System.out.println(pro.getName());
	}

	
	@Test
	void findByNameOrDescriptionJPQLPositionParamMethod()
	{
		Product pro= prepo.findByNameOrDescriptionJPQLPositionParam("product 15", "product 15 description");
		System.out.println(pro.getId());
		System.out.println(pro.getName());
	}
	
	@Test
	void findByNameOrDescriptionNativeQueryPositionParamMethod()
	{
		Product pro= prepo.findByNameOrDescriptionNativeQueryPositionParam("product 15", "product 15 description");
		System.out.println(pro.getId());
		System.out.println(pro.getName());
	}
	
	@Test
	void findByNameOrDescriptionNativeQueryNamedParamMethod()
	{
		Product pro= prepo.findByNameOrDescriptionNativeQueryNamedParam("product 15", "product 15 description");
		System.out.println(pro.getId());
		System.out.println(pro.getName());
	}
	
}
