package com.dollop.app.service;
import java.util.Map;
import com.dollop.app.bean.PostTags;
public interface IPostTagsService {

	public Map<String,Object> createPostTags(PostTags postTags);
	public Map<String,Object> updatePostTags(PostTags postTags,Integer id);
	public Map<String,Object> deletePostTags(Integer id);

}
