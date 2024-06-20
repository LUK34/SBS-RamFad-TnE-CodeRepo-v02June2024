package kw.kng.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//One OrderItem can have One Product
//One Order can have many OrderItem
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="JPA_ORDER_ITEM")
public class OrderItem
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String imageUrl;
	private BigDecimal price;
	private int quantity;
	
	//One OrderItem can have One Product
	@OneToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@ManyToOne(fetch=FetchType.LAZY) //for Bi -directional workflow
	@JoinColumn(name="order_id", referencedColumnName="id")
	private Order order;
	
}

/*
 Default Fetch Types for Mappings:
 OneToMany: LAZY
 ManyToOne:EAGER
 ManyToMany:LAZY
 OneToOne:EAGER
 */

