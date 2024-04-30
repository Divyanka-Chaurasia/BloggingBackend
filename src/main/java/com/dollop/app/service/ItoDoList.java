package com.dollop.app.service;

import java.util.Map;

import com.dollop.app.bean.UserToDoList;

public interface ItoDoList {

	public Map<String,Object> createToDoList(UserToDoList toDoList);
	public void deleteToDoList(Integer id);
	public Map<String,Object> update(UserToDoList toDoList,Integer id,Integer userId);
	public Map<String,Object> getAll();
	public Map<String,Object> get(Integer id);
	public Map<String,Object> deActivate(Integer id,Integer userId);
}
