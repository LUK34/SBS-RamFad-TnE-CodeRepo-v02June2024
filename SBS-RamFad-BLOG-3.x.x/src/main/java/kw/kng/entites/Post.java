package kw.kng.entites;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
		name="ramfad_post",
		uniqueConstraints= {
											@UniqueConstraint(columnNames= {"title"})
										  }
)
@EqualsAndHashCode(exclude = {"comments" ,  "category"})
public class Post
{
	/* One Post -> Many Comments -> One to Many Mapping Bi-directional*/
	/* 1 Category -> Many blog posts -> 1 to Many  Bi-Directional Mapping  */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@Column(name="description", nullable=false)
	private String description;
	
	@Column(name="content", nullable=false)
	private String content;
	
	@OneToMany(mappedBy="post", cascade = CascadeType.ALL, orphanRemoval= true)
	private Set<Comment> comments = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
	private Category category;
	
}

/*
 
 
The orphanRemoval attribute in the @OneToMany and @OneToOne annotations is used to specify the cascading delete behavior for "orphaned" entities.
 When orphanRemoval is set to true, it means that if a child entity (in your case, a Comment) is no longer referenced by the parent entity (in your case, a Post), 
 that child entity will be removed from the database automatically.
 
 
 */










