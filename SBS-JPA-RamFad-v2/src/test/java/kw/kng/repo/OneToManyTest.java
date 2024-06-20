package kw.kng.repo;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kw.kng.entities.Address;
import kw.kng.entities.Order;
import kw.kng.entities.OrderItem;

@SpringBootTest
public class OneToManyTest 
{
	@Autowired
	private OrderRepo orepo;
	
	@Autowired
	private ProductRepo prepo;

	//save order along with alsoe save its order items
	// execute ProductCategoryRepoTest ->  saveProductCategory method first and then execute the below method 
	@Test
	void saveOrderMethod()
	{
		Order ord=new Order();
		ord.setOrderTrackingNumber("100ABC");
		ord.setStatus("In Progress");
		
		OrderItem orderItem1 = new OrderItem();
		orderItem1.setProduct(prepo.findById(1L).get());
		orderItem1.setQuantity(2);
		orderItem1.setPrice(orderItem1.getProduct().getPrice().multiply(new BigDecimal(2)));
		orderItem1.setImageUrl("image1.png");
		orderItem1.setOrder(ord);
		ord.getOrderItems().add(orderItem1);
		
		OrderItem orderItem2 = new OrderItem();
		orderItem2.setProduct(prepo.findById(2L).get());
		orderItem2.setQuantity(3);
		orderItem2.setPrice(orderItem2.getProduct().getPrice().multiply(new BigDecimal(3)));
		orderItem2.setImageUrl("image2.png");
		orderItem2.setOrder(ord);
		ord.getOrderItems().add(orderItem2);
		
		ord.setTotalPrice(ord.getTotalAmount());//custom method defined in entity class
		ord.setTotalQuantity(2);
		
		Address addr=new Address();
		addr.setCity("Pune");
		addr.setStreet("Kothrud");
		addr.setState("Maharashtra");
		addr.setCountry("India");
		addr.setZipcode("411047");
		addr.setOrder(ord);
		
		ord.setBillingAddress(addr);
		
		orepo.save(ord);
	}
	
	@Test
	void fetchOrderMethod()
	{
		Order ord=orepo.findById(1L).get();
		System.out.println(ord.getStatus());
		System.out.println(ord.toString());
		for(OrderItem i : ord.getOrderItems())
		{
			System.out.println(i.getProduct().getName());
		}
	}

	@Test
	void deleteOrderMethod()
	{
		Long id=1L;
		orepo.deleteById(id);
		System.out.println("The order with id: "+id+" is deleted from Database.");
	}
	
}

