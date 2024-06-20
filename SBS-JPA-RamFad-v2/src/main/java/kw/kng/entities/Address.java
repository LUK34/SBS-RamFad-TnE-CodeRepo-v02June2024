package kw.kng.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

//ONE Order can have ONE Address
@Data
@Entity
@Table(name="JPA_ADDRESS")
public class Address 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String street;
	private String city;
	private String state;
	private String country;
	private String zipcode;

	//One toOne BiDirectional Mapping there is code to specified at target class
	//One to Many Bi-Directional Mapping
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="order_id", referencedColumnName="id")
	private Order order;
	
	//One to One Unidirectional Mapping there is no code to be specified at target class
}
