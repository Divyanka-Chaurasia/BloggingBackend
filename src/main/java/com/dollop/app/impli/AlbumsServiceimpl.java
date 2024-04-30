package com.dollop.app.impli;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dollop.app.bean.Albums;
import com.dollop.app.exception.DuplicateEntryException;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.repo.IAlbumRepo;
import com.dollop.app.bean.User;
import com.dollop.app.repo.IUserRepo;
import com.dollop.app.service.IAlbumService;

@Service
public class AlbumsServiceimpl implements IAlbumService {

	@Autowired
	private IAlbumRepo repo;
	@Autowired
	private IUserRepo userRepo;
	Map<String, Object> map = new HashMap<>();
	@Override
	public Map<String, Object> createAlbums(Albums albums) {
		Integer userId = albums.getUser().getUserId();
		Optional<User> user = userRepo.findById(userId);
		System.err.println(repo.existsByUserAndAlbumsName(albums.getUser(),albums.getAlbumsName()));
		if(repo.existsByUserAndAlbumsName(albums.getUser(),albums.getAlbumsName()))
		{
			throw new DuplicateEntryException("same album name not allowed to same user");
		}
		else
		{
			if(user.isPresent())
			{
				albums.setCreatedBy(userId);
				albums.setUpdatedBy(userId);
				 map.put("albums", repo.save(albums));
					return map;				
			}
			else
				throw new ResourceNotFoundException("user not found for create album");
		}
	}

	@Override
	public void deleteAlbums(Integer id) {
		Albums albums = repo.findById(id).get();
        if(albums.isActive())
        {
        	albums.setActive(false);
        	repo.save(albums);
        }
        else 
        	throw new ResourceNotFoundException("albums is deActivate");
	}
	@Override
	public Map<String, Object> updateAlbums(Albums albums, Integer id,Integer userId) {
		Optional<User> user = userRepo.findByUserId(userId);
		if(user.isPresent())
		{
			Albums	album =  repo.findById(id).orElseThrow(
					()-> new ResourceNotFoundException("comments not found"));
			if(album.isActive())
			{
				String newAlbums = albums.getAlbumsName();
				if(!newAlbums.equals(album.getAlbumsName()))
				{
					if(repo.existsByUserAndAlbumsName(albums.getUser(),albums.getAlbumsName()))
					{
						   throw new DuplicateEntryException("album name is duplicate");
					}
				}
				album.setAlbumsName(albums.getAlbumsName());
				map.put("newAlbums", repo.save(album));
				return map;
			}
			else 
				throw new ResourceNotFoundException("address is deActivate");
		}
		else 
			throw new ResourceNotFoundException("user not found with this id");
	}
	@Override
	public Map<String, Object> getAlbum(Integer id) {
		Albums albums = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("albums id not found"));
		if(albums.isActive())
		{
			map.put("albums",albums);
			return map;
		}
		else 
			throw new ResourceNotFoundException("album is deActivate");
	}
	@Override
	public Map<String, Object> getAllAlbums() {
		map.put("albums", repo.findAllAlbums());
		return map;
	}

	@Override
	public Map<String, Object> deActivate(Albums albums, Integer id, Integer userId) {
		Optional<User> user = userRepo.findByUserId(userId);
		if(user.isPresent())
		{
			Albums	album =  repo.findById(id).orElseThrow(
					()-> new ResourceNotFoundException("comments not found"));
			if(album.isActive())
			{
				String newAlbums = albums.getAlbumsName();
				if(!newAlbums.equals(album.getAlbumsName()))
				{
					if(repo.existsByUserAndAlbumsName(albums.getUser(),albums.getAlbumsName()))
					{
						   throw new DuplicateEntryException("album name is duplicate");
					}
				}
				album.setAlbumsName(albums.getAlbumsName());
				album.setActive(true);
				map.put("newAlbums", repo.save(album));
				return map;
			}
			else 
				throw new ResourceNotFoundException("address is deActivate");
		}
		else 
			throw new ResourceNotFoundException("user not found with this id");
	}

}
