package kw.kng.controller;

import org.apache.kafka.common.Uuid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.dto.Order;
import kw.kng.dto.OrderEvent;
import kw.kng.producer.OrderProducer;

@RestController
@RequestMapping("/api/v1")
public class OrderController 
{
	private OrderProducer op;

	public OrderController(OrderProducer op) 
	{
		this.op = op;
	}
	

	@PostMapping("/order")
	public String placeOrder(@RequestBody Order order) //Order is from `KAFKA-Base-Domain`
	{
		order.setOrderId(Uuid.randomUuid().toString());
		OrderEvent oe= new OrderEvent();
		oe.setStatus("PENDING");
		oe.setMessage("Order status is in Pending State");
		oe.setOrder(order);
		
		op.sendMessage(oe);
		
		return "Order with id = "+order.getOrderId()+" placed successfully...";
	}

}
