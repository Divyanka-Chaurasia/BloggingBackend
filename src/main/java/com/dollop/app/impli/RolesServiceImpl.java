package com.dollop.app.impli;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dollop.app.bean.RoleNameEnum;
import com.dollop.app.bean.Roles;
import com.dollop.app.repo.IRolesRepo;
import com.dollop.app.service.IRolesService;
@Service
public class RolesServiceImpl implements IRolesService{
	@Autowired
	private IRolesRepo repo;	
	Map<String,Object> map = new HashMap<>();
	@Override
	public Roles createRoles() {
		Roles role1 = new Roles();
		role1.setRolesId(102l);
		role1.setRolesName(RoleNameEnum.Role_Admin);
		return repo.save(role1);
	} 
}