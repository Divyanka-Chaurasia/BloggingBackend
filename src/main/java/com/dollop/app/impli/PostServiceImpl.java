package com.dollop.app.impli;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dollop.app.bean.Post;
import com.dollop.app.bean.PostTags;
import com.dollop.app.bean.Tags;
import com.dollop.app.bean.User;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.repo.IPostRepo;
import com.dollop.app.repo.IRepoTags;
import com.dollop.app.repo.IUserRepo;
import com.dollop.app.service.IPostService;
@Service
public class PostServiceImpl implements IPostService {
	@Autowired
	private IPostRepo repo;
	@Autowired
	private IUserRepo userRepo;
	
	@Autowired
	private IRepoTags tagsRepo;
	@Override
	public Map<String, Object> createPost(Post post) {        
	    User newUser = post.getUser();
	    Integer userId = newUser.getUserId();
	    Optional<User> user = userRepo.findById(userId);
	    
	    if (user.isPresent() && user.get().isActive()) 
	    {
	        List<PostTags> postTagsList = post.getPostTag();
	        // Check if post tags list is not null before iterating
	        if (postTagsList != null) {
	            for (PostTags postTags : postTagsList) {
	                Optional<Tags> tags = tagsRepo.findByTagsId(postTags.getTag().getTagsId());
	                if (tags.isPresent())
	                    postTags.setPost(post);
	                else
	                    throw new ResourceNotFoundException("Tags not present");
	            }   
	        } 
	        else 
	        	  throw new ResourceNotFoundException("Tags not present");
	        Map<String, Object> map = new HashMap<>();
	        post.setCreatedBy(userId);
	        post.setUpdatedBy(userId);
	        map.put("msg", "Post is created");
	        map.put("Post", repo.saveAndFlush(post));
	        return map;
	    } else {
	        throw new ResourceNotFoundException("User not found or not active");
	    }
	}

	@Override
	public Map<String, Object> updatePost(Post post, Integer postId) {
	  Post newPost = repo.findById(postId).orElseThrow(
			  ()-> new ResourceNotFoundException("post not found")
			  );
	  if(newPost.isActive())
	  {
	  newPost.setPostBody(post.getPostBody());
	  newPost.setPostName(post.getPostName());	  
	  Map<String,Object> map = new HashMap<>();
	  map.put("post", repo.save(newPost));
	  return map;
	  }
	  else 
		  throw new ResourceNotFoundException("post is deActivate");
	}
	@Override
	public void deletePost(Integer postId) {
	Post p = repo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post not found"));
	if(p.isActive())
	{
		p.setActive(false);
		repo.save(p);
	}
	else 
		throw new ResourceNotFoundException("post is deActivate");	
	}
	@Override
	public Map<String, Object> getAllPost() {
		Map<String , Object> map = new HashMap<>();
		map.put("post", repo.findAllActivePost());
	  	return map;
	
	}
	@Override
	public Map<String, Object> getPost(Integer postId) {
		Post post = repo.findById(postId).get();
		Map<String , Object> map = new HashMap<>();
		if(post.isActive())
		{
			map.put("post", repo.findById(postId).get());
			return map;
		}
		else 
		   throw new ResourceNotFoundException("post is deActivate");		
	}
	@Override
	public Map<String, Object> deActivate(Integer postId, Integer userId) {
		  Post newPost = repo.findById(postId).orElseThrow(
				  ()-> new ResourceNotFoundException("post not found")
				  );
		  if(newPost.isActive())
		  {
		  newPost.setActive(true);
		  Map<String,Object> map = new HashMap<>();
		  map.put("post", repo.save(newPost));
		  return map;
		  }
		  else 
			  throw new ResourceNotFoundException("post is deActivate");
	}
}