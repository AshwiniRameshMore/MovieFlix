package io.movieflix.api.repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.movieflix.api.entity.Rating;

/**
 * This is the Repository class of Rating Entity
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
@Repository
public class RatingRepositoryImpl implements RatingRepository{

	private static final Logger LOGGER = Logger.getLogger( RatingRepositoryImpl.class.getName() );

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * This method creates a new Rating object
	 * @param rating Object of Rating
	 * @return Rating object
	 */
	@Override
	public Rating create(Rating rating) {
		LOGGER.log(Level.INFO, "Rating with Id: {0} saved", rating.getRatingId());
		em.persist(rating);
		return rating;
	}

	/**
	 * This method updates the existing rating
	 * @param rating Rating object
	 * @return Modified Rating object
	 */
	@Override
	public Rating update(Rating rating) {
		LOGGER.log(Level.INFO, "Rating with Id: {0} updated", rating.getRatingId());
    	return em.merge(rating);
	}

	/**
	 * This method deletes the rating object
	 * @param rating object
	 */
	@Override
	public void delete(Rating rating) {
		LOGGER.log(Level.INFO, "Rating with Id: {0} removed", rating.getRatingId());
		em.remove(rating);
	}

	/**
     * This method retrieves the ratings of a specific video and computes the average of those ratings
     * @param videoId ID of Video
     * @return Average of Ratings
     */
	@Override
	public double findAvgRatings(String videoId) {
		TypedQuery<Rating> query = em.createNamedQuery("Rating.findAvgRatings", Rating.class);
		query.setParameter("cid", videoId);
		List<Rating> list = query.getResultList();
		if(list.size() > 0){
			double sum = 0.0;
			for(int i=0;i<list.size();i++)
				sum += list.get(i).getRating();
			double average = sum/list.size();
			LOGGER.log(Level.INFO, "Average Rating - {0}", average);
			return average;
		}
		else
			return 0;
	}
	
	/**
	 * This method finds the rating object by ID
	 * @param ratingId ID of rating
	 * @return Rating object
	 */
	@Override
	public Rating findByID(String ratingId) {
		LOGGER.log(Level.INFO, "Rating with Id: {0} found",ratingId);
		return em.find(Rating.class, ratingId);
	}

}

