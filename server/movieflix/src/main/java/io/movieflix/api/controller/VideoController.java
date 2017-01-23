package io.movieflix.api.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.movieflix.api.entity.Video;
import io.movieflix.api.service.VideoService;

/**
 * This is the Controller class of Video Entity
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
@RestController
@RequestMapping(value = "videos")
public class VideoController {

	private static final Logger LOGGER = Logger.getLogger( VideoController.class.getName() );
	
	@Autowired
	private VideoService videoService;
	
	/**
	 * This method calls the VideoService method to create new Video object
	 * @param video Object of Video
	 * @return Newly created object of Video
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Video create(@RequestBody Video video){
		LOGGER.log(Level.INFO, "Calling videoService - Create method");
		return videoService.create(video);
	}
	
	/**
	 * This method calls the VideoService method to update the video object 
	 * of the specific video title
	 * @param title Title of the video
	 * @param video Modified video object
	 * @return Updated Video object
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "{title}")
	public Video update(@PathVariable("title") String title, @RequestBody Video video){
		LOGGER.log(Level.INFO, "Calling videoService - Update method");
		return videoService.update(title, video);
	}
	
	/**
	 * This method calls the VideoService method to delete the specific video
	 * @param title title of a video
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "{title}")
    public void delete(@PathVariable("title") String title){
		LOGGER.log(Level.INFO, "Calling videoService - Delete method");
		videoService.delete(title);
	}
	
	/**
	 * This method calls the VideoService method to retrieve all the videos
	 * @return List of videos
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Video> findAll(){
		LOGGER.log(Level.INFO, "Calling videoService - findAll method");
		return videoService.findAll();
	}
		
	/**
	 * This method calls the VideoService method to find the video with a specific title
	 * @param title title of a video
	 * @return Object of video
	 */
	@RequestMapping(method = RequestMethod.GET, value = "{title}")
	public Video findByTitle(@PathVariable("title") String title) {
		LOGGER.log(Level.INFO, "Calling videoService - findByTitle method");
		return videoService.findByTitle(title);
	}
	
	/**
	 * This method calls the VideoService method to find the video with matching substring of a title
	 * @param title Any substring of a title
	 * @return List of videos with a title match
	 */
	@RequestMapping(method = RequestMethod.GET, value = "search={title}")
	public List<Video> findByTitleSubstring(@PathVariable("title") String title) {
		LOGGER.log(Level.INFO, "Calling videoService - findByTitleSubstring method");
		return videoService.findByTitleSubstring(title);
	}
		
	/**
	 * This method calls the VideoService method to find the top rated videos
	 * @param type type of a video
	 * @return List of videos
	 */
	@RequestMapping(method = RequestMethod.GET, value = "top={type}")
	public List<Video> findTopRatedVideos(@PathVariable("type") String type){
		LOGGER.log(Level.INFO, "Calling videoService - findTopRatedVideos method");
		return videoService.findTopRatedVideos(type);
	}
	
	/**
	 * This method calls the VideoService method to filter the videos according to input
	 * @param type type of a video
	 * @param year released year of a video
	 * @param genre genre of a video
	 * @param sort sort by criteria
	 * @return List of videos
	 */
	@RequestMapping(method = RequestMethod.GET, value = "type={type}/year={year}/genre={genre}/sort={sort}")
	public List<Video> findByFilter(@PathVariable("type")String type, @PathVariable("year")int year, @PathVariable("genre")String genre, @PathVariable("sort") String sort){
		LOGGER.log(Level.INFO, "Calling videoService - findByFilter method");
		return videoService.findByFilter(type, year, genre, sort);
	}

	/**
	 * This method calls the VideoService method to get the Imdb ID of a specific video title
	 * @param title title of a video
	 * @return ImdbID
	 */
	@RequestMapping(method = RequestMethod.GET, value = "getimdb/{title}")
	public String getImdbID(@PathVariable("title") String title){
		LOGGER.log(Level.INFO, "Calling videoService - getImdbID method");
		return videoService.getImdbID(title);
	}

}
