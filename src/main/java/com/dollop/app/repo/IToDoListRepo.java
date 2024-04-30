package com.dollop.app.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.dollop.app.bean.UserToDoList;

public interface IToDoListRepo extends JpaRepository<UserToDoList, Integer> {
	 @Query("SELECT u FROM UserToDoList u WHERE u.isActive = true")
	   List<UserToDoList> findAllToDoList();
}
