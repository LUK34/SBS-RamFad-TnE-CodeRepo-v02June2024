package kw.kng.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="JPA_PRODUCT_CATEGORY")
public class ProductCategory
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String categoryName;
	private String categoryDescription;
	
	//One Product Category have Many Products
	//One to Many Bi-Directional Mapping
	@OneToMany(cascade= {
						CascadeType.PERSIST,
						CascadeType.MERGE
	}, fetch = FetchType.LAZY,
	mappedBy="category")
	private List<Product> products = new ArrayList<>();
}
	