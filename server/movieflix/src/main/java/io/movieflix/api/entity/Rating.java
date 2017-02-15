package io.movieflix.api.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * This is the Rating class defining it's attributes and getter/setters
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Rating.findAvgRatings",query = "SELECT r from Rating r INNER JOIN r.video v WHERE v.videoId = :cid")
})
public class Rating {

	@Id
	private String ratingId;
	private double rating;
	@ManyToOne
	private Video video;
	
	public String getRatingId() {
		return ratingId;
	}
	
	public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
	}
	
	public double getRating() {
		return rating;
	}
	
	public void setRating(double rating) {
		this.rating = rating;
	}
		
	public Video getVideo() {
		return video;
	}
	
	public void setVideo(Video video) {
		this.video = video;
	}
	
	public Rating() {
		this.ratingId = UUID.randomUUID().toString();
	}	
}
