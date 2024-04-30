package com.dollop.app.repo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dollop.app.bean.User;
public interface IUserRepo extends JpaRepository<User, Integer> {
   public Optional<User> findByUserNameOrUserEmail(String userName,String userEmail);
   
   @Query("SELECT u FROM User u WHERE u.isActive = true")
   List<User> findActiveUsers();
   
   @Query("Select u From User u where u.userName = :name")
   User findUserByUserName(@Param("name") String userName);
   
   public Optional<User> findByUserNameAndUserPassword(String userName,String userPassword);
   
   public Boolean existsByUserName(String userName);
   public Boolean existsByUserEmail(String userEmail);
   
   Optional<User> findByUserId(Integer userId);
}