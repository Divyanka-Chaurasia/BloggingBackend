package com.dollop.app.repo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dollop.app.bean.Post;
import com.dollop.app.bean.User;

public interface IPostRepo extends JpaRepository<Post, Integer> {
	   @Query("SELECT p FROM Post p WHERE p.isActive = true")
	   List<Post> findAllActivePost();
	   
	   Optional<Post> findById(Integer postId);
	   
	   Optional<Post> findPostByUser(User user);
}
