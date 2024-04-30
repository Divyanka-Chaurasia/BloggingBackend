package com.dollop.app.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.dollop.app.bean.Photos;

public interface IPhotoService {

	   public String uploadPhotos(MultipartFile file,String path) throws IOException;
	   public void deletePhotos(Integer id);
	   public Map<String,Object> updatePhotos(Photos photos,Integer id);
	   public InputStream getPhotos(String path,String name) throws FileNotFoundException;
	   public Map<String,Object> getAllPhotos();
       public Photos createPhoto(Photos photo);
       public Photos getOnePhoto(Integer photoId);
}
