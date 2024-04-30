package com.dollop.app.service;
import java.util.Map;
import com.dollop.app.bean.User;
public interface IUserService 
{
    public Map<String,Object> create(User u);
    public Map<String,Object> update(User u,Integer userId);
    public void delete(Integer userId);
    public Map<String,Object> getUser(Integer userId);
    public Map<String,Object> getAllUser();
    public Map<String,Object> findByUserName(String userName); 
    public Map<String,Object> login(String email,String userName);
    public Map<String,Object> deActivate(Integer userId);
    public Map<String,Object> getPostById(Integer userId,Integer postId);
	public Object getToDoListById(Integer userId, Integer toDoListId);
}