package com.dollop.app.repo;
import java.util.List;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dollop.app.bean.Comments;
import com.dollop.app.bean.Post;
public interface ICommentsRepo extends JpaRepository<Comments, Integer>{

	   @Query("SELECT c FROM Comments c WHERE c.isActive = true")
	   List<Comments> findAllComments();
	   
	   public Optional<Comments> findByCommentsIdAndPost(Integer id,Post post);

	   public List<Comments> findCommentsByPost(Post post);
	   
}