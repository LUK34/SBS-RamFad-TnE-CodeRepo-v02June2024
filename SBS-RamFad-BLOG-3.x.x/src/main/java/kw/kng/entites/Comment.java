package kw.kng.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ramfad_comment")
@EqualsAndHashCode(exclude = {"post"})
public class Comment 
{
	/* One Post -> Many Comments -> One to Many Mapping Bi-directional*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String body;

	@ManyToOne(fetch= FetchType.LAZY) //It will fetch the related entities from the database when you use the relationship
	@JoinColumn(name="post_id", nullable= false)
	private Post post;
}
