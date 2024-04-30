package com.dollop.app.impli;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dollop.app.bean.Comments;
import com.dollop.app.bean.Post;
import com.dollop.app.bean.User;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.repo.ICommentsRepo;
import com.dollop.app.repo.IPostRepo;
import com.dollop.app.repo.IUserRepo;
import com.dollop.app.service.ICommentsService;

@Service
public class CommentsServiceImpl implements ICommentsService {

	@Autowired
	private ICommentsRepo repo;
	@Autowired
	private IPostRepo postRepo;
	@Autowired
	private IUserRepo userRepo;
	Map<String,Object> map = new HashMap<>();
	@Override
	public Map<String, Object> createComments(Comments comments) {
		Optional<Post> post = postRepo.findById(comments.getPost().getPostId());
		Optional<User> user = userRepo.findById(comments.getUser().getUserId());
		if(post.isPresent())
		{
			comments.setCreatedBy(user.get().getUserId());
			comments.setUpdatedBy(user.get().getUserId());
			map.put("comments", repo.save(comments));
			return map;
		}
		else 
			throw new ResourceNotFoundException("post not found");
	}

	@Override
	public Map<String, Object> updateComments(Comments comments, Integer commentsId,Integer userId,Integer postId) {
		Optional<User> user = userRepo.findByUserId(userId);
		Optional<Post> post = postRepo.findById(postId);
		if(user.isPresent())
		{
			if(post.isPresent())
			{
				Comments newComments =  repo.findById(commentsId).orElseThrow(
						()-> new ResourceNotFoundException("comments not found"));
				if(newComments.isActive())
				{
					newComments.setCommentsBody(comments.getCommentsBody());
					repo.save(newComments);
				    map.put("comments", newComments);
				return map;
				}
				else 
					throw new ResourceNotFoundException("comments is deActivate");
			}
			else 
				throw new ResourceNotFoundException("post not found for this comment");
			}
			
		else 
			throw new ResourceNotFoundException("user not found with this id");
	}

	@Override
	public void deleteComments(Integer commentsId) 
	{
        Comments comments = repo.findById(commentsId).get();
        if(comments.isActive())
        {
        	comments.setActive(false);
        	repo.save(comments);
        }
        else 
        	throw new ResourceNotFoundException("comment is deActivate");
	}

	@Override
	public Map<String, Object> getCommentsByid(Integer cid,Integer pid) {
		Comments com = repo.findById(cid).get();
		if(com.isActive())
		{
			Post post = new Post();
			post.setPostId(pid);
			Optional<Comments> comments =this.repo.findByCommentsIdAndPost(cid, post);
		   if(comments.isPresent())
		  {
		     map.put("comments", com);
		    return map;
		  }
		  else 
			throw new ResourceNotFoundException("comment not exist with this post");
		  }
		else 
			throw new ResourceNotFoundException("comment is deactivate");
 	}
	@Override
	public Map<String, Object> getAllComments() {
		map.put("comments", repo.findAllComments());
		return map;
	}

	@Override
	public Map<String, Object> getCommentsByPostId(Integer postId) 
	{	
		Map<String, Object> map = new HashMap<>();
		Post post = new Post();
		post.setPostId(postId);
		if(post.getPostId()!=null)
		{
		   List<Comments> comments = repo.findCommentsByPost(post); 
		   if(comments.isEmpty())
		   {
			 map.put("comments", "post has no comments");
		   }
		   else
		    map.put("comments", comments);
		    return map;		
		}
		else 
			throw new ResourceNotFoundException("post not found");
	}

	@Override
	public Map<String, Object> deActivate(Integer commentsId, Integer userId, Integer postId) {
		Optional<User> user = userRepo.findByUserId(userId);
		Optional<Post> post = postRepo.findById(postId);
		if(user.isPresent())
		{
			if(post.isPresent())
			{
				Comments newComments =  repo.findById(commentsId).orElseThrow(
						()-> new ResourceNotFoundException("comments not found"));
				if(newComments.isActive())
				{
					newComments.setActive(true);
					repo.save(newComments);
				    map.put("comments", newComments);
				return map;
				}
				else 
					throw new ResourceNotFoundException("comments is deActivate");
			}
			else 
				throw new ResourceNotFoundException("post not found for this comment");
			}
			
		else 
			throw new ResourceNotFoundException("user not found with this id");
	}

}
