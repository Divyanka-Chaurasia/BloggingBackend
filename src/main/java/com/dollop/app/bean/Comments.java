package com.dollop.app.bean;
import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="comments_tab")
public class Comments {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentsId;
	 
	@Size(min = 3 , max=50,message = "invalid tags name")
	@NotBlank(message="compulory")
	private String commentsBody;
	@CreationTimestamp
	private Timestamp createdAt;
	@UpdateTimestamp
	private Timestamp updatedAt;
	private Integer createdBy;
	private Integer updatedBy;
	
	@ManyToOne
	@JoinColumn(name="postId")
	@JsonIgnoreProperties(value = "post")
	private Post post;
	@ManyToOne
	@JoinColumn(name="userId")
	@JsonIgnoreProperties(value = "user")
	private User user;	
	private boolean isActive=true;
	
}
