package com.dollop.app.controller;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dollop.app.service.IRolesService;
@RestController
@RequestMapping("/api/role")
public class RolesController {

	@Autowired
	private IRolesService service;
	
	@PostMapping("/create")
	public ResponseEntity<Map<String,Object>> createRole()
	{
		Map<String,Object> map = new HashMap<>();
		map.put("roles", service.createRoles());
		return new ResponseEntity<>(map,HttpStatus.CREATED);
	}
}
