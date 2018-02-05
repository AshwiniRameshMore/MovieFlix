package io.movieflix.api.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.movieflix.api.entity.Rating;
import io.movieflix.api.entity.User;
import io.movieflix.api.entity.Video;
import io.movieflix.api.repository.RatingRepository;

/**
 * This is the Service class of Rating Entity
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
@Service
public class RatingServiceImpl implements RatingService{
	
	private static final Logger LOGGER = Logger.getLogger( RatingServiceImpl.class.getName() );

	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private VideoService videoService;
		
	@Autowired
	private UserService userService;
	
	/**
	 * This method retrieves the user and video objects and
	 * calls the RatingRepository method to create a new Rating object
	 * @param videoId ID of Video
	 * @param userId ID of User
	 * @param rate Rating provided by user
	 * @return Rating object
	 */
	@Override
	@Transactional
	public Rating create(String videoId, String userId, double rate) {
		Video video = videoService.findByID(videoId);
		User user = userService.findByID(userId);
		Rating rating = new Rating();
		rating.setVideo(video);
		rating.setUser(user);
		rating.setRating(rate);
		LOGGER.log(Level.INFO, "Calling ratingRepository - create method");
    	return ratingRepository.create(rating);
	}

	/**
	 * This method calls the RatingRepository method to update the existing rating
	 * @param ratingId ID of Rating
	 * @param rate Rating provided by user
	 * @return Rating object
	 */
	@Override
	@Transactional
	public Rating update(String ratingId, double rate) {
		Rating rating = ratingRepository.findByID(ratingId);
		if (rating == null) {
			LOGGER.log(Level.SEVERE, "Exception thrown - Rating not found");
		   	throw new EntityNotFoundException("Rating not found");
		}
		rating.setRating(rate);
		LOGGER.log(Level.INFO, "Calling ratingRepository - update method");
		return ratingRepository.update(rating);
	}

	/**
	 * This method checks if the rating exist and
	 * calls the RatingRepository method to delete the existing rating
	 * @param ratingId ID of Rating
	 */
	@Override
	@Transactional
	public void delete(String ratingId) {
		LOGGER.log(Level.INFO, "Calling ratingRepository - findByID method");
		Rating rating = ratingRepository.findByID(ratingId);
		if (rating == null) {
			LOGGER.log(Level.SEVERE, "Exception thrown - Rating not found");
    		throw new EntityNotFoundException("Rating not found");
		}
		LOGGER.log(Level.INFO, "Calling ratingRepository - delete method");
		ratingRepository.delete(rating);
	}

	/**
     * This method calls the RatingRepository method to retrieve the average ratings of a specific video
     * @param videoId ID of Video
     * @return Average of Ratings
     */
	@Override
	public double findAvgRatings(String videoId) {
		LOGGER.log(Level.INFO, "Calling ratingRepository - findAvgRatings method");
		return ratingRepository.findAvgRatings(videoId);
	}

}
