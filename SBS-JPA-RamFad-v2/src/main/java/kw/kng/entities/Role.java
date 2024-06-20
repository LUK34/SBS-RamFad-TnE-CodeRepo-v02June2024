package kw.kng.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="JPA_ROLE")
public class Role 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@ManyToMany( cascade= {
								CascadeType.PERSIST, 
								CascadeType.MERGE,
								CascadeType.REMOVE //this will do delete in test case
								},	
								fetch=FetchType.EAGER ,
								mappedBy="roles"
								)
	private Set<User> users = new HashSet<>();
	/*
	public Role()
	{
		
	}
	public Role(String name)
	{
		this.name=name;
	}
	*/
}
