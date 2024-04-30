package com.dollop.app.bean;

import java.sql.Timestamp;

import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="post_tab")
public class Post {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
   	 private Integer postId;
	 
	 @Size(min = 5,max = 30,message = "invalid name")
	 @NotBlank(message="post name is required")
	 private String postName;
	
	 @NotBlank(message="post body is required")
	 @Size(min = 20,max = 150,message = "invalid name")
	 private String postBody;
	 
	 @CreationTimestamp
	 private Timestamp createdAt;
	 @UpdateTimestamp
	private Timestamp updatedAt;
	private Integer createdBy;
	private Integer updatedBy;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userId")
	@JsonIgnoreProperties(value = {"userPost","userComments"})
	private User user;
	
	@OneToMany
	@JoinColumn(name="postId")
	@JsonIgnoreProperties(value = {"post","user"})
	private List<Comments> postComments;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="postId")
	@JsonIgnoreProperties(value = "post")
	private List<PostTags> postTag;
	private boolean isActive=true;
}
