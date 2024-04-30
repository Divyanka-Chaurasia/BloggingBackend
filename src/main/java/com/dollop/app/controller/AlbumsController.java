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
import com.dollop.app.bean.Albums;
import com.dollop.app.service.IAlbumService;

@RestController
@RequestMapping("/api/album")
public class AlbumsController {

	@Autowired
	private IAlbumService service;
	
	@PostMapping("/create")
	public ResponseEntity<Map<String,Object>> createAlbums(@Validated @RequestBody Albums album)
	{
		return new ResponseEntity<>(service.createAlbums(album),HttpStatus.CREATED);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String,Object>> deleteAlbums(@PathVariable Integer id)
	{
		service.deleteAlbums(id);
		Map<String,Object> map = new HashMap<>();
		map.put("msg", "deleted ");
		return new ResponseEntity<>(map,HttpStatus.OK);
	}	
	@PutMapping("/update/{id}/{userId}")
	public ResponseEntity<Map<String,Object>> updateAlbums (@Validated @RequestBody Albums albums,@PathVariable Integer id,@PathVariable Integer userId)
	{
		return new ResponseEntity<>(service.updateAlbums(albums,id,userId),HttpStatus.CREATED);
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<Map<String,Object>> getAlbum(@PathVariable Integer id)
	{
		return new ResponseEntity<>(service.getAlbum(id),HttpStatus.CREATED);
	}	
	@GetMapping("/getAll")
	public ResponseEntity<Map<String,Object>> getAll()
	{
		return new ResponseEntity<>(service.getAllAlbums(),HttpStatus.CREATED);
	}
	

}