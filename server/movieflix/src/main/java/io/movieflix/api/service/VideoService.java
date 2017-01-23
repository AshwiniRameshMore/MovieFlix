package io.movieflix.api.service;

import java.util.List;

import io.movieflix.api.entity.Video;

/**
 * This is the VideoService interface whose methods are implemented in VideoServiceImpl class
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
public interface VideoService {
	
    public Video create(Video video);
   
    public Video update(String title, Video video);

	public void delete(String title);
	
	public List<Video> findAll();
		
	public List<Video> findTopRatedVideos(String type);
	
	public List<Video> findByFilter(String type, int year, String genre, String sort);
	
	public Video findByTitle(String title);
	
	public List<Video> findByTitleSubstring(String title);
	
	public String getImdbID(String title);
	
	public Video findByID(String videoId);
}
