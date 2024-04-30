package com.dollop.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dollop.app.bean.Photos;

public interface IPhotoRepo extends JpaRepository<Photos, Integer> {
	@Query("SELECT photoName FROM Photos p")
	public List<Photos> findAllPhotosByphotoName();
}
