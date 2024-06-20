package kw.kng.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.dto.OrderRequest;
import kw.kng.dto.OrderResponse;
import kw.kng.service.OrderService;

@RestController
@RequestMapping("/api/v1")
public class OrderController 
{
	private OrderService os;

	public OrderController(OrderService os) 
	{
		this.os = os;
	}
	
	@PostMapping
	public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest)
	{
		return ResponseEntity.ok(os.placeOrder(orderRequest));
	}

}

/*

 Type:POST
 URL:http://localhost:8080/api/v1 
 Body:
 
 {
    "order":{
        "totalQuantity":2,
        "shoppingCartId":1,
        "totalPrice":2000
    },
    "payment":{
        "type":"DEBIT",
        "cardName":"Ramesh Fadatare",
        "cardNumber":"1234 1234 1234",
        "expiryMonth":2,
        "expiryYear":2025,
        "cvc":123
    }
}
 
 */
