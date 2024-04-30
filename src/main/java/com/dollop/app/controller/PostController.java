package com.dollop.app.controller;

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

import com.dollop.app.bean.Post;
import com.dollop.app.service.IPostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private IPostService service;
	@PostMapping("/createpost")
	public ResponseEntity<Map<String,Object>> createPost(@Validated @RequestBody Post post)
	{
		return new ResponseEntity<>(service.createPost(post),HttpStatus.CREATED);
	}	
	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<?> updatePost(@Validated @RequestBody Post post,@PathVariable Integer postId)
	{
		return new ResponseEntity<>(service.updatePost(post, postId),HttpStatus.OK);
	}	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePost(@PathVariable Integer id)
	{
		service.deletePost(id);
		return new ResponseEntity<>("post has deleted",HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getPost(@PathVariable Integer id)
	{
		return new ResponseEntity<>(service.getPost(id),HttpStatus.OK);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllPost()
	{
		return new ResponseEntity<>(service.getAllPost(),HttpStatus.OK);
	}
	@PutMapping("/deactivate/{postId}/{userId}")
	public ResponseEntity<?> deActivate(@PathVariable Integer postId,@PathVariable Integer userId)
	{
		return new ResponseEntity<>(service.deActivate(postId,userId),HttpStatus.CREATED);
	}
}
