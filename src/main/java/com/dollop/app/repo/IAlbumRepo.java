package com.dollop.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dollop.app.bean.Albums;
import com.dollop.app.bean.User;


public interface IAlbumRepo extends JpaRepository<Albums, Integer>{
	 @Query("SELECT a FROM Albums a WHERE a.isActive = true")
	   List<Albums> findAllAlbums();
	 
	Boolean existsByUserAndAlbumsName(User user,String albumsName);

}
