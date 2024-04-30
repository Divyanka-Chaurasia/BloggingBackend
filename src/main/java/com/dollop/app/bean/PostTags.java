package com.dollop.app.bean;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name="postTags_tab")
public class PostTags {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postTagId;
	@ManyToOne
	@JoinColumn(name="postId")	
	private Post post;
	@ManyToOne()
	@JoinColumn(name="tagsId")
	@JsonIgnoreProperties(value="tags")
	private Tags tag;
	private boolean isActive;
}
