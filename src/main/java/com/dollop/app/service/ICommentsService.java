package com.dollop.app.service;
import java.util.Map;
import com.dollop.app.bean.Comments;
public interface ICommentsService {

	public Map<String,Object> createComments(Comments comments);
	public Map<String,Object> updateComments(Comments comments, Integer commentsId,Integer userId,Integer postId);
	public void deleteComments(Integer commentsId);
	public Map<String,Object> getCommentsByid(Integer id,Integer pid);
	public Map<String,Object> getAllComments();
	public Map<String,Object> getCommentsByPostId(Integer postId);
	public Map<String,Object> deActivate(Integer commentsId,Integer userId,Integer postId);
}
