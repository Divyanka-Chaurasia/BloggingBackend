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

import com.dollop.app.bean.Comments;
import com.dollop.app.service.ICommentsService;
@RestController
@RequestMapping("/api/comments")
public class CommentsController {

	@Autowired
	private ICommentsService service;
	
	@PostMapping("/create")
	public ResponseEntity<Map<String,Object>> createComments(@Validated @RequestBody Comments comments)
	{
		return new ResponseEntity<>(service.createComments(comments),HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String,Object>> deleteComments(@PathVariable Integer id)
	{
		service.deleteComments(id);
		Map<String,Object> map = new HashMap<>();
		map.put("msg", "deleted ");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}/{userId}/{postId}")
	public ResponseEntity<Map<String,Object>> updateComments(@Validated @RequestBody Comments comments,@PathVariable Integer id,@PathVariable Integer userId,@PathVariable Integer postId)
	{
		return new ResponseEntity<>(service.updateComments(comments,id,userId,postId),HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{id}/{postId}")
	public ResponseEntity<Map<String,Object>> getComments(@PathVariable Integer id,@PathVariable Integer postId)
	{
		return new ResponseEntity<>(service.getCommentsByid(id,postId),HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Map<String,Object>> getAll()
	{
		return new ResponseEntity<>(service.getAllComments(),HttpStatus.OK);
	}
	
	@GetMapping("/getAll/{postId}")
	public ResponseEntity<Map<String,Object>> getCommentsById(@PathVariable Integer postId)
	{
		return new ResponseEntity<>(service.getCommentsByPostId(postId),HttpStatus.OK);
	}
	
	@PutMapping("/deactivate/{commentId}/{userId}/{postId}")
	public ResponseEntity<?> deActivate(@PathVariable Integer albumId,@PathVariable Integer userId,@PathVariable Integer postId)
	{
		return new ResponseEntity<>(service.deActivate(albumId,userId,postId),HttpStatus.CREATED);
	}
}