package kw.kng.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

//Many User Many Role
@Getter
@Setter
@Entity
@Table(name="JPA_USER", 
			  uniqueConstraints = @UniqueConstraint(
															name="unique_email",
															columnNames="email"
															)
)
public class User 
{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String email;
	
	private String password;
	
	//Many To Many - Unidirectional
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(
							name="JPA_USERS_ROLES",
							joinColumns = @JoinColumn(name="user_id", referencedColumnName="id"),
							inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName="id")
						)
	//private Collection<Role> roles;
	private Set<Role> roles = new HashSet<>();
	
	
	/*
	 	//Many to Many -Uni Directional
	 	@ManyToMany(fetch=FetchType.EAGER, cascadeType.ALL)
	 	@JoinTable(
	 							name="users_roles",
	 							joinColumn=@JoinColumn(name="user_id", referencedColumnName="id")
	 					   )
	 	private Set<Role> roles;
	 */
}
