package com.dollop.app.impli;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.dollop.app.bean.Photos;
import com.dollop.app.exception.BadApiRequestException;
import com.dollop.app.exception.ResourceNotFoundException;
import com.dollop.app.repo.IPhotoRepo;
import com.dollop.app.service.IPhotoService;
@Service
public class PhotoServiceImpl implements IPhotoService {

	@Autowired
	private IPhotoRepo repo;
	@Value("${user.profile.image.path}")
	private String imagepath;	
	Map<String,Object> map = new HashMap<>();	
	@Override
	public String uploadPhotos(MultipartFile file,String path) throws IOException {
		String originalFileName = file.getOriginalFilename();
		String fileName = UUID.randomUUID().toString();
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		String fileNameWithExtenstion = fileName+extension;
		String fullPathWithFileName = path+fileNameWithExtenstion;
		if(extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpeg"))
		{
			File folder = new File(path);
			if(!folder.exists())
			{
				folder.mkdirs();
			}
		  Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
		}
		else 
		{
			throw new BadApiRequestException("file with this "+extension+" not allowed");
		}
		return fileNameWithExtenstion;
	}
	@Override
	public void deletePhotos(Integer id) 
	{
		Photos photos = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("photo not found"));                
         String fullPath = imagepath+photos.getImagePath();
     	Path path=Paths.get(fullPath);
    	try {
    		Files.delete(path);
    	
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	repo.delete(photos);
	}	
	@Override
	public Map<String, Object> updatePhotos(Photos photos, Integer id) {
		Photos newPhotos = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("photo not found"));
		newPhotos.setPhotoName(photos.getPhotoName());
		newPhotos.setPhotoThumbNail(photos.getPhotoThumbNail());
		newPhotos.setPhotoType(photos.getPhotoType());
		map.put("newPhotos", repo.save(newPhotos)); 
		return map;
	}

	@Override
	public InputStream getPhotos(String path,String name) throws FileNotFoundException {
		String fullPath = path+File.separator+name;
		InputStream inputStream = new FileInputStream(fullPath);
		return inputStream;
	}

	public Photos getOnePhoto(Integer photoId) {
		Photos photos = repo.findById(photoId).orElseThrow(
				()-> new ResourceNotFoundException("photo not found")
				);
		return photos;
	}
	@Override
	public Map<String, Object> getAllPhotos() {
	   map.put("photos",repo.findAllPhotosByphotoName());
		return map;
	}
	@Override
	public Photos createPhoto(Photos photo) {
	  return repo.save(photo);
	}
}
