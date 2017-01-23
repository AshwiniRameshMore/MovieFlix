package io.movieflix.api.repository;

import io.movieflix.api.entity.Rating;

/**
 * This is the RatingRepository interface whose methods are implemented in RatingRepositoryImpl class
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
public interface RatingRepository {

	Rating findByID(String ratingId);

	Rating create(Rating rating);

	Rating update(Rating rating);

	void delete(Rating rating);

	double findAvgRatings(String videoId);

}
