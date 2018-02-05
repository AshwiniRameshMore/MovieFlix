package io.movieflix.api.repository;

import java.util.List;

import io.movieflix.api.entity.Video;

/**
 * This is the VideoRepository interface whose methods are implemented in VideoRepositoryImpl class
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
public interface VideoRepository {

	public Video create(Video media);
	
	public Video update(Video video);

	public void delete(Video existing);

	public List<Video> findAll();

	public List<Video> findTopRatedVideos(String type);

	public List<Video> findByFilter(String type, int year, String genre, String sort);

	public Video findByTitle(String title);
	
	public List<Video> findByTitleSubstring(String title);

	public String getImdbID(String title);

	public Video findByID(String videoId);
}
