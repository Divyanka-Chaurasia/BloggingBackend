package com.dollop.app.bean;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles_tab")
public class Roles {

	 @Id
	private Long rolesId;
	@Enumerated(EnumType.STRING)
	private RoleNameEnum rolesName;
	
	@OneToMany
	@JoinColumn(name="rolesId")
	private List<UserRoles> userRoles;
	private boolean isActive;
}
