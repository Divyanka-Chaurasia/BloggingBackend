package com.dollop.app.bean;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="phots_tab")
public class Photos {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer photoId;
	 @NotBlank(message = "name is required")
	private String photoName;
	private String photoType;
	private String photoThumbNail;
	
	@NotBlank(message = "it is required")
	private String imagePath;
	@CreationTimestamp
	private Timestamp createdAt;
	@UpdateTimestamp
	private Timestamp updatedAt;
	private Integer createdBy;
	private Integer updatedBy;
	@ManyToOne
	@JoinColumn(name="albumsId")
	
	private Albums albums;
	private boolean isActive=true;
	
}
