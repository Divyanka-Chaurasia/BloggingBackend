package com.dollop.app.service;

import java.util.Map;

import com.dollop.app.bean.Post;

public interface IPostService {

	public Map<String,Object> createPost(Post post);
	public Map<String,Object> updatePost(Post post,Integer postId);
	public void deletePost(Integer postId);
	public Map<String,Object> getAllPost();
	public Map<String,Object> getPost(Integer postId);
	public Map<String,Object> deActivate(Integer postId,Integer userId);
}
