package com.dollop.app.controller;

import java.io.IOException;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.dollop.app.bean.Photos;
import com.dollop.app.dtos.ImageResponse;
import com.dollop.app.service.IPhotoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/photos")
public class PhotosController {

	@Autowired
	private IPhotoService service;
	
	@Value("${user.profile.image.path}")
	private String imageUploadpath;

	private Photos readValue;
	
	@PostMapping("/image")
	public ResponseEntity<ImageResponse> uploadImage(@Valid @RequestPart ("imagePath") MultipartFile image,@RequestPart String photos) throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		readValue = mapper.readValue(photos, Photos.class);
		String imageName = service.uploadPhotos(image,imageUploadpath);
		readValue.setImagePath(imageName);
		Photos photo = service.createPhoto(readValue);		
		ImageResponse response = ImageResponse.builder()
				.imageName(imageName)
				.message("image is uploaded successfully")
				.success(true)
				.status(HttpStatus.CREATED)
				.build();
		return new ResponseEntity<ImageResponse>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/{photoId}")
	public void serveImage(@PathVariable Integer photoId,HttpServletResponse response) throws IOException
	{
	    Photos photos = service.getOnePhoto(photoId);
	    InputStream resource = service.getPhotos(imageUploadpath, photos.getImagePath());
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    StreamUtils.copy(resource,response.getOutputStream());
	    
	}
	
	@DeleteMapping("/{photoId}")
	public ResponseEntity<String> delete(@PathVariable Integer photoId)
	{
		service.deletePhotos(photoId);
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
	}
}