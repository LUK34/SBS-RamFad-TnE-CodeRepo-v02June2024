package kw.kng.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="JPA_PRODUCT")
/*@NamedQuery(
name="Product.findByPrice",
query="Select p from Product p where p.price = :price"
)*/
@NamedQueries(
{
	@NamedQuery(
			name="Product.findAllOrderByNameDesc",
			query="Select p from Product p ORDER BY p.name DESC"
			),
	@NamedQuery(
			name="Product.findByPrice",
			query="Select p from Product p WHERE p.price = :price"
			)
}
)
/*
@NamedNativeQuery(
name = "Product.findByDescription",
query="SELECT * FROM JPA_PRO_SIMPLE p WHERE p.DESCRIPTION = :description",
resultClass = Product.class
)*/
@NamedNativeQueries(
{
        @NamedNativeQuery(
                name = "Product.findByDescription",
                query="SELECT * FROM JPA_PRODUCT p WHERE p.DESCRIPTION = :description",
                resultClass = Product.class
        ),
        @NamedNativeQuery(
                name = "Product.findAllOrderByNameASC",
                query="SELECT * FROM JPA_PRODUCT p ORDER BY p.NAME ASC",
                resultClass = Product.class
        )
}
)
public class Product
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="STOCK_KEEPING_UNIT", nullable=false)
	private String sku;
	
	@Column(nullable=false)
	private String name;
	private String description;
	private BigDecimal price;
	private boolean active;
	private String imageUrl;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	//One Product Category have Many Products
	@ManyToOne
	@JoinColumn(name="category_id", referencedColumnName="id")
	private ProductCategory category;
}
