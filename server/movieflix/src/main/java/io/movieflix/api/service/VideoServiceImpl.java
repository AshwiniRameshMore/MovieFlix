package io.movieflix.api.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.movieflix.api.entity.Video;
import io.movieflix.api.exception.BadRequestException;
import io.movieflix.api.exception.EntityNotFoundException;
import io.movieflix.api.repository.VideoRepository;

/**
 * This is the Service class of Video Entity
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
@Service
public class VideoServiceImpl implements VideoService{

	private static final Logger LOGGER = Logger.getLogger( VideoServiceImpl.class.getName() );
	
	@Autowired
	private VideoRepository videoRepository;

	/**
	 * This method calls the VideoRepository method to create new Video object
	 * @param video Object of Video
	 * @return Newly created object of Video
	 */
	@Override
	@Transactional
	public Video create(Video video) {
		LOGGER.log(Level.INFO, "Calling videoRepository - findByTitle method");
		Video existing = videoRepository.findByTitle(video.getTitle());
		if (existing != null) {
			LOGGER.log(Level.SEVERE, "Throws Exception - Video with this title already exists");
			throw new BadRequestException("Video with this title already exists");
		}
		LOGGER.log(Level.INFO, "Calling videoRepository - create method");
		return videoRepository.create(video);
	}

	/**
	 * This method calls the VideoRepository method to update the video object 
	 * of the specific video title
	 * @param title Title of the video
	 * @param video Modified video object
	 * @return Updated Video object
	 */
	@Override
	@Transactional
	public Video update(String title, Video video) {
		LOGGER.log(Level.INFO, "Calling videoRepository - findByTitle method");
		Video existing = videoRepository.findByTitle(title);
		if(existing == null){
			LOGGER.log(Level.SEVERE, "Throws Exception - Video not found");
			throw new EntityNotFoundException("Video not found");
		}
		LOGGER.log(Level.INFO, "Calling videoRepository - update method");
		return videoRepository.update(video);
	}

	/**
	 * This method checks if the video exists and
	 * calls the VideoRepository method to delete the specific video
	 * @param title title of a video
	 */
	@Override
	@Transactional
	public void delete(String title) {
		LOGGER.log(Level.INFO, "Calling videoRepository - findByTitle method");
		Video existing = videoRepository.findByTitle(title);
		if(existing == null){
			LOGGER.log(Level.SEVERE, "Throws Exception - Video not found");
			throw new EntityNotFoundException("Video not found");
		}
		LOGGER.log(Level.INFO, "Calling videoRepository - delete method");
		videoRepository.delete(existing);
	}

	/**
	 * This method calls the VideoRepository method to retrieve all the videos
	 * @return List of videos
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Video> findAll() {
		LOGGER.log(Level.INFO, "Calling videoRepository - findAll method");
		return videoRepository.findAll();
	}

	/**
	 * This method calls the VideoRepository method to find the video with a specific title
	 * @param title title of a video
	 * @return Object of video
	 */
	@Override
	@Transactional(readOnly = true)
	public Video findByTitle(String title) {
		LOGGER.log(Level.INFO, "Calling videoRepository - findByTitle method");
		Video existing = videoRepository.findByTitle(title);
		if(existing == null){
			LOGGER.log(Level.SEVERE, "Throws Exception - Video not found");
			throw new EntityNotFoundException("Video not found");
		}
		LOGGER.log(Level.INFO, "RETURN {0}", existing.toString());
		return existing;
	}
	
	/**
	 * This method calls the VideoRepository method to find the video with matching substring of a title
	 * @param title Any substring of a title
	 * @return List of videos with a title match
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Video> findByTitleSubstring(String title) {
		LOGGER.log(Level.INFO, "Calling videoRepository - findByTitleSubstring method");
		return videoRepository.findByTitleSubstring(title);
	}
	
	/**
	 * This method calls the VideoRepository method to find the top rated videos
	 * @param type type of a video
	 * @return List of videos
	 */
	@Override
	public List<Video> findTopRatedVideos(String type) {
		LOGGER.log(Level.INFO, "Calling videoRepository - findTopRatedVideos method");
       	return videoRepository.findTopRatedVideos(type);
	}

	/**
	 * This method calls the VideoRepository method to filter the videos according to input
	 * @param type type of a video
	 * @param year released year of a video
	 * @param genre genre of a video
	 * @param sort sort by criteria
	 * @return List of videos
	 */
	@Override
	public List<Video> findByFilter(String type, int year, String genre, String sort) {
		LOGGER.log(Level.INFO, "Calling videoRepository - findByFilter method");
		return videoRepository.findByFilter(type, year, genre, sort);
	}

	/**
	 * This method checks if the video exist and
	 * calls the VideoRepository method to get the Imdb ID of a specific video title
	 * @param title title of a video
	 * @return ImdbID
	 */
	@Override
	public String getImdbID(String title) {
		LOGGER.log(Level.INFO, "Calling videoRepository - findByTitle method");
		Video existing = videoRepository.findByTitle(title);
		if(existing == null){
			LOGGER.log(Level.SEVERE, "Throws Exception - Video not found");
			throw new EntityNotFoundException("Video not found");
		}
		LOGGER.log(Level.INFO, "Calling videoRepository - getImdbID method");
		return videoRepository.getImdbID(title);
	}

	/**
	 * This method returns the video of a particular ID
	 * @param videoId ID of video
	 * @return video object
	 */
	@Override
	@Transactional(readOnly = true)
	public Video findByID(String videoId) {
		LOGGER.log(Level.INFO, "Calling videoRepository - findByID method");
		Video video = videoRepository.findByID(videoId);
		if (video == null) {
			LOGGER.log(Level.SEVERE, "Throws Exception - Video not found");
			throw new EntityNotFoundException("Video not found");
		}
		LOGGER.log(Level.INFO, "RETURN {0}", video.toString());
		return video;
	}
	
}
