package com.dollop.app.impli;
import java.util.HashMap;
import java.util.Optional;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dollop.app.bean.User;
import com.dollop.app.bean.UserToDoList;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.repo.IToDoListRepo;
import com.dollop.app.repo.IUserRepo;
import com.dollop.app.service.ItoDoList;

@Service
public class ToDoListServiceImpl implements ItoDoList {
    @Autowired
	private IToDoListRepo repo;
    @Autowired
    private IUserRepo userRepo;
    Map<String, Object> map = new HashMap<>();
	@Override
	public Map<String, Object> createToDoList(UserToDoList toDoList) {
	 Integer userId = toDoList.getUser().getUserId();
	 Optional<User> user = userRepo.findByUserId(userId);
	 if(user.isPresent())
	 {
		 toDoList.setCreatedBy(userId);
		 toDoList.setUpdatedBy(userId);
		 map.put("toDoList", repo.save(toDoList));
			return map;
	 }
		throw new ResourceNotFoundException("user not found");
	}
	@Override
	public void deleteToDoList(Integer id) {
		UserToDoList toDoList = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("toDo list not exist"));
        if(toDoList.isActive())
        {
        	toDoList.setActive(false);
        	repo.save(toDoList);
        }
        else 
        	throw new ResourceNotFoundException("toDoList is deActivate");
	}
	@Override
	public Map<String, Object> update(UserToDoList toDoList, Integer id,Integer userId) {
		Optional<User> user = userRepo.findById(userId);
		if(user.isPresent())
		{
			UserToDoList	newtoDoList =  repo.findById(id).orElseThrow(
					()-> new ResourceNotFoundException("toDoList not found"));
			 if(newtoDoList.isActive())
			  {
				 newtoDoList.setToDoListTitle(toDoList.getToDoListTitle());
				 newtoDoList.setCompleted(false);
					map.put("newtoDoList", repo.save(newtoDoList));
					return map;
			  }
			  else 
				  throw new ResourceNotFoundException("user is deActivate");
		}
		else 
		throw new ResourceNotFoundException("user not found with this id");
	}
	@Override
	public Map<String, Object> getAll() {
		map.put("UserToDoList", repo.findAllToDoList());
		return map;		
	}

	@Override
	public Map<String, Object> get(Integer id) {
		UserToDoList toDoList = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("toDoList not found"));
		if(toDoList.isActive())
		{
			map.put("toDoList",toDoList);
			return map;
		}
		else 
			throw new ResourceNotFoundException("toDoList is deActivate");
	}
	@Override
	public Map<String, Object> deActivate(Integer id, Integer userId) {
		Optional<User> user = userRepo.findById(userId);
		if(user.isPresent())
		{
			UserToDoList	newtoDoList =  repo.findById(id).orElseThrow(
					()-> new ResourceNotFoundException("toDoList not found"));
			 if(newtoDoList.isActive())
			  {
				 newtoDoList.setActive(true);
					map.put("newtoDoList", repo.save(newtoDoList));
					return map;
			  }
			  else 
				  throw new ResourceNotFoundException("user is deActivate");
		}
		else 
		throw new ResourceNotFoundException("user not found with this id");
	}

}
