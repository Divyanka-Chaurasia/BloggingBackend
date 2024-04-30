package com.dollop.app.controller;
import java.util.Map;
import com.dollop.app.service.IUserService;
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
import com.dollop.app.bean.User;
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	//private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;
	@PostMapping("/addUser")
	public ResponseEntity<Map<String,Object>> createUser(@Validated @RequestBody User u )
	{
	  return new ResponseEntity<>(userService.create(u),HttpStatus.CREATED);
	}	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete( @PathVariable("id") Integer id)
	{
		userService.delete(id);
	   return new ResponseEntity<>("user is deactivated",HttpStatus.OK);		
	}	
	@GetMapping("/get/{id}")
	public ResponseEntity<Map<String,Object>> getUser(@PathVariable("id") Integer id)
	{
		return new ResponseEntity<>(userService.getUser(id),HttpStatus.OK);
	}	
	@PutMapping("/update/{id}")
	public ResponseEntity<Map<String,Object>> update(@Validated @RequestBody User u,@PathVariable("id") Integer id)
	{
		return new ResponseEntity<>(userService.update(u, id),HttpStatus.OK);
	}	
	@GetMapping("/getAll")
	public ResponseEntity<Map<String, Object>> listOfUser()
	{
		return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
	}
	@GetMapping("/{userName}")
	public ResponseEntity<Map<String, Object>> findUserByUserName(@PathVariable String userName)
	{
		return new ResponseEntity<>(userService.findByUserName(userName),HttpStatus.OK);
	}
	
	@GetMapping("/{userName}/{userPassword}/login")
	public ResponseEntity<Map<String, Object>> login(@PathVariable String userName,@PathVariable String userPassword)
	{
		return new ResponseEntity<>(userService.login(userPassword,userName),HttpStatus.OK);
	}
	@PutMapping("/active/{id}")
	public ResponseEntity<String> activeUser(@PathVariable Integer id)
	{
		userService.deActivate(id);
		return new ResponseEntity<>("user is activated",HttpStatus.OK);
	}
	@GetMapping("/find/{userId}/{postId}")
	public ResponseEntity<Map<String,Object>> findPostByUserId(@PathVariable Integer userId,@PathVariable Integer postId)
	{
		return new ResponseEntity<>(userService.getPostById(userId, postId),HttpStatus.OK);
	}
	@GetMapping("/find/{userId}/{toDoListId}")
	public Map<String,Object> getToDoListById(Integer userId,Integer toDoListId)
	{
	   //return new ResponseEntity<>(userService.getToDoListById(userId,toDoListId),HttpStatus.OK);
		return null;
	}
	
}