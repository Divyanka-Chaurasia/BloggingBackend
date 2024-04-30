package com.dollop.app.impli;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dollop.app.bean.Tags;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.repo.IRepoTags;
import com.dollop.app.service.ITagsService;

@Service
public class tagsServiceImpl implements ITagsService {

	@Autowired
	private IRepoTags repo;
	
	Map<String ,Object> map = new HashMap<>();
	
	@Override
	public Map<String, Object> createTags(Tags tags) {
		map.put("tags", repo.save(tags));
		map.put("msg", "tags is created");
		return map;
	}
	@Override
	public Map<String, Object> updateTags(Tags tags, Integer tagsId) {
	    Tags newTags = repo.findById(tagsId).orElseThrow(
	    		()->new ResourceNotFoundException("tags not found")
	    		);
	    if(newTags.isActive())
		  {
	    newTags.setTagsName(tags.getTagsName());
	    map.put("tags",  repo.save(newTags)); 
		return map;
		  }
	    else 
	    	throw new ResourceNotFoundException("tags is deActivate");
	}
	@Override
	public void deleteTags(Integer id) {
		Tags tags = repo.findById(id).get();
		if(tags.isActive())
		{
			tags.setActive(false);
			repo.save(tags);
		}
		else 
		throw new ResourceNotFoundException("tags is deActivate");
	}
	@Override
	public Map<String, Object> deActivate(Integer tagsId) {
		Tags newTags = repo.findById(tagsId).orElseThrow(
	    		()->new ResourceNotFoundException("tags not found")
	    		);
	    if(newTags.isActive())
		  {
	    newTags.setActive(true);
	    map.put("tags",  repo.save(newTags)); 
		return map;
		  }
	    else 
	    	throw new ResourceNotFoundException("tags is deActivate");
	}
}