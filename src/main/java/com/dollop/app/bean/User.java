package com.dollop.app.bean;
import java.sql.Timestamp;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String userFirstName;
	private String userLastName;
	
	@Column(unique = true)
	@Size(min=5 ,max=100)
	
	@NotBlank(message = "usenrname is required")
	private String userName; 
	@NotBlank(message = "password is required")
	 @Pattern(
		        regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
		        message = "Password must be contain atleast 1 uppercase, lowecase and number")
	private String userPassword;
	 
	@Column(unique = true)
	@NotBlank(message = "email is required")
	@NotNull
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "Invalid email address")
	private String userEmail; 
	@NotBlank(message = "phone is required")
	@Pattern(regexp = "\\d{10}", message = "invalid Phone number it should be 10 digit")
	private String userPhone;
	private String userWebsite;
	@CreationTimestamp
	private Timestamp createdAt;
	@UpdateTimestamp
	private Timestamp updatedAt;
   
   @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
   @JoinColumn(name="userId")
   @JsonIgnoreProperties(value = "user")
   private List<Address> userAddress;
   
   @OneToMany
   @JoinColumn(name="userId")
   @JsonIgnoreProperties(value = "user")
  private List<UserToDoList> usertoDolist;
  
   @OneToMany
   @JoinColumn(name="userId")
   @JsonIgnoreProperties(value = "user")
   private List<Albums> userAlbums;
  
   @OneToMany(cascade = CascadeType.ALL)
   @JoinColumn(name = "userId")
   @JsonIgnoreProperties(value = "user")
   private Set<UserRoles> userRoles;
  
   @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
   @JsonIgnoreProperties(value = "user")
   @JoinColumn(name="userId")
   private List<Post> userPost;
   
   @OneToMany
   @JoinColumn(name="userId")
   @JsonIgnoreProperties(value = "user")
   private List<Comments> userComments;
   private boolean isActive=true;
}