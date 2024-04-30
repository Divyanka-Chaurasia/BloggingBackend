package com.dollop.app.service;

import java.util.Map;

import com.dollop.app.bean.Albums;

public interface IAlbumService {

   public Map<String,Object> createAlbums(Albums albums);
   public void deleteAlbums(Integer id);
   public Map<String,Object> updateAlbums(Albums albums,Integer id,Integer userId);
   public Map<String,Object> getAlbum(Integer id);
   public Map<String,Object> getAllAlbums();
   public Map<String,Object> deActivate(Albums albums,Integer id,Integer userId);
}
