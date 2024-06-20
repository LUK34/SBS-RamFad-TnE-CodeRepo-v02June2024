package kw.kng.service;

import kw.kng.dto.OrderRequest;
import kw.kng.dto.OrderResponse;

public interface OrderService 
{
	OrderResponse placeOrder(OrderRequest orderRequest);
	
	

}
