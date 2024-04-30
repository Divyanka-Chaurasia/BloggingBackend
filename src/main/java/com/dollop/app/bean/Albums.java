package com.dollop.app.bean;
import java.sql.Timestamp;

import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name="albums_tab")
public class Albums {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer albumsId;	 
	@NotBlank(message = "name is required")
	@Column(unique = true)
	private String albumsName;
	@CreationTimestamp
	private Timestamp createdAt;
	@UpdateTimestamp
	private Timestamp updatedAt;
	private Integer createdBy;
	private Integer updatedBy;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	@OneToMany
	@JoinColumn(name="albumsId")
	@JsonIgnoreProperties(value="albums")
	private List<Photos> photo;
	private boolean isActive=true;
}
