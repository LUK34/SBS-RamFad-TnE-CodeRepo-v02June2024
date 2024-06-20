package kw.kng.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

//ONE Order can have ONE Address
//One Order can have many OrderItem
@Data
@Entity
@Table(name="JPA_ORDER")
public class Order
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String orderTrackingNumber;
	private int totalQuantity;
	private BigDecimal totalPrice;
	private String status;
	
	@CreationTimestamp
	private LocalDateTime dateCreated;
	@UpdateTimestamp
	private LocalDateTime lastUpdated;
	
	//One to One Bi-Directional Mapping for Source class
	@OneToOne(cascade = CascadeType.ALL, mappedBy="order")
	private Address billingAddress;
	
		//One Order can have many OrderItem
		//One to Many Mapping - Bi Directional
		//default fetch type for one to many is LAZY
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="order")
	private Set<OrderItem> orderItems = new HashSet<>();
	
	public BigDecimal getTotalAmount()
	{
		BigDecimal amt= new BigDecimal(0.0);
		for(OrderItem i: this.orderItems)
		{
			//amt=amt.add(i.getProduct().getPrice().multiply(new BigDecimal(totalQuantity)));
			amt=amt.add(i.getPrice());
		}
		return amt;
	}
	
	/*
	//One Order can have many OrderItem
	//One to Many Mapping - Uni Directional
	//default fetch type for one to many is LAZY
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="order_id", referencedColumnName="id")
	private Set<OrderItem> orderItems = new HashSet<>();
	*/
	
	//One to One Unidirectional Mapping for Source class
	/*
	 
	 @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="billing_address_id", referencedColumnName = "id")
	private Address billingAddress;
	 
	 */
	
}


/*
 	
 	Each Order has exactly one Address and each address belongs to one order.

1. The one-to-one mapping/association can be either unidirectional or bidirectional.

2. In uni-diretional association, source entity has a relationship fiedl that refers to the target
entity and the source entity table contains the foreign key.

3.In bi-directional association, each entity(i.e source and target) has a relationship field that referes to each other and the 
target entity table contains the foreign key. The source entity must use the mappedBy attribute to define 
the bi-directional one-to-one mapping.

4. In Bi directional association each entity(i.e source and target) has a realtionship fiel that refers to each other and the target entity`s table
contain the foreign key. The source entity must use the mappedBy attribute to define the bi directional one to one mapping.


 
 */