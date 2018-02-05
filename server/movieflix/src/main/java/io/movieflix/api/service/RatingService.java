package io.movieflix.api.service;

import io.movieflix.api.entity.Rating;

/**
 * This is the RatingService interface whose methods are implemented in RatingServiceImpl class
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
public interface RatingService {

	Rating create(String videoId, double rate);

	Rating update(String ratingId, double rate);

	void delete(String ratingId);

	double findAvgRatings(String videoId);

}
