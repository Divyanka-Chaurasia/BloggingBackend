package com.dollop.app.bean;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name="tags_tab")
public class Tags {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tagsId;
	 @Column(unique = true)
	 @Size(min = 2 ,max = 20 ,message = "invalid tags name")
	 @NotBlank(message="it is compulsory to give name")
	private  String tagsName;
	 @CreationTimestamp
	private Timestamp createdAt;
	 @UpdateTimestamp
	private Timestamp updatedAt;
	private Integer createdBy;
	private Integer updatedBy;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="tagsId")
	private List<PostTags> tags;
	private boolean isActive=true;
}
