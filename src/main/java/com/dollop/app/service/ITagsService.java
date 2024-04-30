package com.dollop.app.service;

import java.util.Map;

import com.dollop.app.bean.Tags;

public interface ITagsService {

	public Map<String,Object> createTags(Tags tags);
	public Map<String,Object> updateTags(Tags tags,Integer tagsId);
	public void deleteTags(Integer id);
	public Map<String,Object> deActivate(Integer tagsId);
}
