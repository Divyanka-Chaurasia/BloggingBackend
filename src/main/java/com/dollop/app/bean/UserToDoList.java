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
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="toDoList_tab")
public class UserToDoList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer toDoListId;
	@NotBlank(message = "Field is required")
	@Size(max = 5,min = 2, message = "lenght should be 2 or 5")
	private String toDoListTitle;
	private boolean completed=false;
	@CreationTimestamp
	private Timestamp createdAt;
	@UpdateTimestamp
	private Timestamp updatedAt;
	private Integer createdBy;
	private Integer updatedBy;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	private boolean isActive=true;
}
