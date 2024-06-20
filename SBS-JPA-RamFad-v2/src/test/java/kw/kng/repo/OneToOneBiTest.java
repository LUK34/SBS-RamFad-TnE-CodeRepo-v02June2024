package kw.kng.repo;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kw.kng.entities.Address;
import kw.kng.entities.Order;

@SpringBootTest
public class OneToOneBiTest 
{
	/* ----------------------------------------------------- ONE TO ONE BI-DIRECTIONAL MAPPING ----------------------------------------------------- */
	//Order ->Source Class
	//Address -> Target Class

	@Autowired
	private AddressRepo arepo;

	@Test
	void saveAddressMethod()
	{
		//Order ->Source Class
		//Address -> Target Class
		
		Order ord = new Order();
		ord.setOrderTrackingNumber("150ABC");
		ord.setTotalQuantity(5);
		ord.setStatus("IN PROGRESS");
		ord.setTotalPrice(new BigDecimal(100));
		
		Address addr= new Address();
		addr.setCity("Triandrum");
		addr.setStreet("Peroorkada");
		addr.setState("Kerala");
		addr.setCountry("India");
		addr.setZipcode("411087");
		
		ord.setBillingAddress(addr);
		addr.setOrder(ord); //This is the added code of 1 to 1 Bi directional
		
		arepo.save(addr);
		
	}
	
	@Test
	void updateAddressMethod()
	{
		//Order ->Source Class
		//Address -> Target Class
		
		Address addr= arepo.findById(1L).get();
		addr.setZipcode("555555");
		
		addr.getOrder().setStatus("DELIVERED");
		arepo.save(addr);
	}
	
	@Test
	void fetchAddressMethod()
	{
		Address addr=arepo.findById(1L).get();
	}
	
	@Test
	void deleteAddrMethod()
	{
		long id=1l;
		arepo.deleteById(id);
		System.out.println("Address with id: "+id+" deleted successfully.");
	}
	
}
