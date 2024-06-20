package kw.kng.repo;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kw.kng.entities.Address;
import kw.kng.entities.Order;

@SpringBootTest
public class OneToOneUniTest 
{
	@Autowired
	private OrderRepo orepo;
	
	/* ----------------------------------------------------- ONE TO ONE UNI-DIRECTIONAL MAPPING ----------------------------------------------------- */
	@Test
	void saveOrderMethod()
	{
		Order ord = new Order();
		ord.setOrderTrackingNumber("150ABC");
		ord.setTotalQuantity(5);
		ord.setStatus("IN PROGRESS");
		ord.setTotalPrice(new BigDecimal(100));
		
		Address addr= new Address();
		addr.setCity("Triandrum");
		addr.setStreet("Nalanchira");
		addr.setState("Kerala");
		addr.setCountry("India");
		addr.setZipcode("411987");
		addr.setOrder(ord);
		
		ord.setBillingAddress(addr);
		
		orepo.save(ord);
	}
	
	@Test
	void updateOrderMethod()
	{
		Long id=2L;
		Order ord = orepo.findById(id).get();
		ord.setStatus("DELIVERED");
		ord.getBillingAddress().setZipcode("421987");
		orepo.save(ord);
		
	}
	
	@Test
	void deleteOrderMethod()
	{
		Long id=1L;
		orepo.deleteById(id);
		System.out.println("The order with id: "+id+" is deleted successfully from DB");
	}

}
