package kw.kng.entites;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ramfad_category")
@EqualsAndHashCode(exclude = {"post"})
public class Category 
{
	/* 1 Category -> Many blog posts -> 1 to Many  Bi-Directional Mapping  */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;

	@OneToMany(mappedBy="category",  cascade=CascadeType.ALL,  orphanRemoval=true)
	private List<Post> posts;
}
