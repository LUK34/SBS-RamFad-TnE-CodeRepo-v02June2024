package kw.kng.dto;

import kw.kng.entities.Order;
import kw.kng.entities.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest 
{
	private Order order;
	private Payment payment;

}
