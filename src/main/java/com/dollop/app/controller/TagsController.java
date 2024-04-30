package com.dollop.app.controller;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dollop.app.bean.Tags;
import com.dollop.app.service.ITagsService;

@RestController
@RequestMapping("/api/tags")
public class TagsController {
	
	@Autowired
	private ITagsService service;
	
	@PostMapping("/create")
	public ResponseEntity<Map<String,Object>> createTags(@Validated @RequestBody Tags tags )
	{
	   	return new ResponseEntity<>(service.createTags(tags),HttpStatus.CREATED);
	}	
	@PutMapping("/update/{tagsId}")
	public ResponseEntity<Map<String,Object>> updateTags(@RequestBody Tags tags, @PathVariable Integer tagsId)
	{
	   	return new ResponseEntity<>(service.updateTags(tags,tagsId),HttpStatus.CREATED);
	}
	@DeleteMapping("/delete/{tagsId}")
	public ResponseEntity<Map<String,Object>> deleteTags(@PathVariable Integer tagsId)
	{
		service.deleteTags(tagsId);
		Map<String,Object> map = new HashMap<>();
		map.put("msg", "tags deleted");
		
	   	return new ResponseEntity<>(map,HttpStatus.CREATED);
	}
	@PutMapping("/deActivate/{tagsId}")
	public ResponseEntity<Map<String,Object>> deActivate( @PathVariable Integer tagsId)
	{
	   	return new ResponseEntity<>(service.deActivate(tagsId),HttpStatus.CREATED);
	}
	
}
