package com.dollop.app.controller;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.dollop.app.bean.UserToDoList;
import com.dollop.app.service.ItoDoList;

@RestController
@RequestMapping("/api/toDoList")
public class toDoListController {
	@Autowired
	private ItoDoList service;
	
	@PostMapping("/create")
	public ResponseEntity<Map<String,Object>> createtoDoList(@Validated @RequestBody UserToDoList toDoList)
	{
		return new ResponseEntity<>(service.createToDoList(toDoList),HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String,Object>> deletetoDoList(@PathVariable Integer id)
	{
		service.deleteToDoList(id);
		Map<String,Object> map = new HashMap<>();
		map.put("msg", "deleted ");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}/{userId}")
	public ResponseEntity<Map<String,Object>> updatetoDoList(@Validated @RequestBody UserToDoList toDoList,@PathVariable Integer id,@PathVariable Integer userId)
	{
		return new ResponseEntity<>(service.update(toDoList,id,userId),HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Map<String,Object>> gettoDoList(@PathVariable Integer id)
	{
		return new ResponseEntity<>(service.get(id),HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Map<String,Object>> getAll()
	{
		return new ResponseEntity<>(service.getAll(),HttpStatus.CREATED);
	}
	
	@PutMapping("/deActivate/{id}/{userId}")
	public ResponseEntity<Map<String,Object>> deActivate(@PathVariable Integer id,@PathVariable Integer userId)
	{
		return new ResponseEntity<>(service.deActivate(id,userId),HttpStatus.CREATED);
	}

}
