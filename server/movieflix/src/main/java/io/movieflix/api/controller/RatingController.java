package io.movieflix.api.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.movieflix.api.entity.Rating;
import io.movieflix.api.service.RatingService;

/**
 * This is the Controller class of Rating Entity
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
@RestController
@CrossOrigin(origins = "http://localhost:4000")
@RequestMapping(value = "ratings")
public class RatingController {
	
	private static final Logger LOGGER = Logger.getLogger( RatingController.class.getName() );

	@Autowired
	private RatingService ratingService;
	
	/**
	 * This method calls the RatingService method to create a new Rating object
	 * @param videoId ID of Video
	 * @param rate Rating provided by user
	 * @return Rating object
	 */
	@RequestMapping(method = RequestMethod.POST, value="{videoId}/rating={rate}")
	public Rating create(@PathVariable("videoId") String videoId, @PathVariable("rate") double rate){
		LOGGER.log(Level.INFO, "Calling ratingService - Create method");
		return ratingService.create(videoId, rate);
	}
		
	/**
	 * This method calls the RatingService method to update the existing rating
	 * @param ratingId ID of Rating
	 * @param rate Rating provided by user
	 * @return Rating object
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "{ratingId}/rating={rate}")
	public Rating update(@PathVariable("ratingId") String ratingId, @PathVariable("rate") double rate){
		LOGGER.log(Level.INFO, "Calling ratingService - Update method");
    	return ratingService.update(ratingId, rate);
	}
		
	/**
	 * This method calls the RatingService method to delete the existing rating
	 * @param ratingId ID of Rating
	 */
	@RequestMapping(method = RequestMethod.DELETE, value="{ratingId}")
	public void delete(@PathVariable("ratingId") String ratingId){
		LOGGER.log(Level.INFO, "Calling ratingService - Delete method");
		ratingService.delete(ratingId);
	}

    /**
     * This method calls the RatingService method to retrieve the average ratings of a specific video
     * @param videoId ID of Video
     * @return Average of Ratings
     */
	@RequestMapping(method = RequestMethod.GET, value="{videoId}")
	public double findAvgRatings(@PathVariable("videoId") String videoId){
		LOGGER.log(Level.INFO, "Calling ratingService - findAvgRatings method");
		int scale = (int) Math.pow(10, 1);
	    return (double) Math.round(ratingService.findAvgRatings(videoId) * scale) / scale;
	    
	}

}
