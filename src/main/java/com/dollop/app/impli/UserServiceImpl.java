package com.dollop.app.impli;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dollop.app.bean.Post;
import com.dollop.app.bean.User;
import com.dollop.app.bean.UserToDoList;
import com.dollop.app.exception.DuplicateEntryException;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.repo.IPostRepo;
import com.dollop.app.repo.IToDoListRepo;
import com.dollop.app.repo.IUserRepo;
import com.dollop.app.service.IUserService;
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserRepo userRepo; 	
	
	@Autowired
	private IPostRepo postRepo;
	
	@Autowired
	private IToDoListRepo toDoListRepo;
	
	@Override
	public Map<String, Object> create(User u) {
	    Optional<User> user = userRepo.findByUserNameOrUserEmail(u.getUserName(), u.getUserEmail());
	    if(user.isPresent())
	    {
	    	throw new DuplicateEntryException("username or email is already exist");
	    }
	     u.setUserName(u.getUserName().trim());
	    userRepo.save(u);
	    Map<String,Object> map = new HashMap<>();
	    map.put("user", u);
		return map;
	}
	@Override
	public Map<String, Object> update(User u, Integer userId) {
	    User user = userRepo.findById(userId).orElseThrow(
	            () -> new ResourceNotFoundException("User not found")
	    );
	    if (user.isActive()) {
	        String newUserName = u.getUserName();
	        String newUserEmail = u.getUserEmail();

	        if (!newUserName.equals(user.getUserName()) || !newUserEmail.equals(user.getUserEmail())){
	        	
	            if (userRepo.existsByUserName(newUserName) || userRepo.existsByUserEmail(newUserEmail) ) 
	                throw new DuplicateEntryException("User name or email is duplicate");
	        }
	        user.setUserEmail(u.getUserEmail());
	        user.setUserFirstName(u.getUserFirstName());
	        user.setUserLastName(u.getUserLastName());
	        user.setUserName(newUserName);
	        user.setUserPhone(u.getUserPhone());
	        user.setUserWebsite(u.getUserWebsite());
	        Map<String, Object> map = new HashMap<>();
	        map.put("user", userRepo.save(user));
	        return map;
	    } else {
	        throw new ResourceNotFoundException("User is deActivate");
	    }
	    }	
	@Override
	public void delete(Integer userId) {
		User u = userRepo.findById(userId).get();
		if(u.isActive())
		{
			u.setActive(false);
		    userRepo.save(u);
		}
		else{
			throw new ResourceNotFoundException("user "+u.getUserId()+" is deactivate");
		}
	}
	@Override
	public Map<String, Object> getUser(Integer userId) {
		Map<String,Object> map = new HashMap<>();
	    User user = userRepo.findById(userId).orElseThrow(()->
	   			new ResourceNotFoundException("User not found "+userId)
			   );
	   if(!user.isActive()) 
		   map.put("message", "User is deActivate  "+userId);
	   else
		   map.put("user", user);
		return map;
	}
	@Override
	public Map<String, Object> getAllUser() {
		Map<String,Object> map = new HashMap<>();
		map.put("user", userRepo.findActiveUsers());
        return map;
	}
	@Override
	public Map<String, Object> findByUserName(String userName) {
		Map<String,Object> map = new HashMap<>();
		  String msg = null;
	   User user =userRepo.findUserByUserName(userName);
	   if(user.isActive())
	   {
		 
			 if(user.getUserName()!=null)
			 {
				msg  = "username is exist";
				 map.put("msg", msg);
			 }
			 else 
				 msg = "user is not exist";
			 map.put("msg", msg);
			 return map;
	   }
	   else 
		   throw new ResourceNotFoundException("user is deactivate");
	}
	@Override
	public Map<String, Object> login(String password, String userName) {
		Optional<User> user = userRepo.findByUserNameAndUserPassword(userName, password);
		Map<String,Object> map = new HashMap<>();
	    if(user.isPresent())
	    {
	    	map.put("msg", "login successfully");
	    	map.put("user", user);
	    	return map;	    	
	    }
	    else {
	    	throw new ResourceNotFoundException("user not found");	    
	    }
	}
	@Override
	public Map<String, Object> deActivate(Integer userId) {
	    User user = userRepo.findById(userId).orElseThrow(
	            () -> new ResourceNotFoundException("User not found")
	    );
	   if(!user.isActive())
	   {
		   user.setActive(true);
	        Map<String, Object> map = new HashMap<>();
	        map.put("user", userRepo.save(user));
	        return map;
	    } else {
	        throw new ResourceNotFoundException("User is already active");
	    }	
	}
	@Override
	public Map<String, Object> getPostById(Integer userId, Integer postId) {
		Map<String, Object> map = new HashMap<>();
	   User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found"));
	   Post post = postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post not found"));
	   Optional<Post> newPost = postRepo.findPostByUser(user);
	   if(newPost.isPresent())
	   {
		   map.put("post",newPost);
		   return map;
	   }
	   else 
		   map.put("msg","post not found with this user");
	   return map;
	}
	@Override
	public Map<String,Object> getToDoListById(Integer userId, Integer toDoListId) {
		 User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found"));
		 UserToDoList toDoList = toDoListRepo.findById(toDoListId).orElseThrow(()->new ResourceNotFoundException("toDoList not found"));
		 Map<String,Object> map = new HashMap<>();
		 map.put("toDoList", toDoList);
		 return map;
	}	
}