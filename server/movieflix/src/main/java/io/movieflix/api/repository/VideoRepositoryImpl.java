package io.movieflix.api.repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.movieflix.api.entity.Video;

/**
 * This is the Repository class of Video Entity
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
@Repository
public class VideoRepositoryImpl implements VideoRepository{

	private static final Logger LOGGER = Logger.getLogger(VideoRepositoryImpl.class.getName());

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * This method creates new Video object
	 * @param video Object of Video
	 * @return Newly created object of Video
	 */
	@Override
	public Video create(Video video) {
		LOGGER.log(Level.INFO, "Video with Id: {0} saved", video.getVideoId());
		em.persist(video);
		return video;
	}

	/**
	 * This method updates the video object 
	 * @param video Modified video object
	 * @return Updated Video object
	 */
	@Override
	public Video update(Video video) {
		LOGGER.log(Level.INFO, "Video with Id: {0} updated", video.getVideoId());
		return em.merge(video);
	}

	/**
	 * This method deletes the specific video
	 * @param video video object
	 */
	@Override
	public void delete(Video video) {
		LOGGER.log(Level.INFO, "Video with Id: {0} removed", video.getVideoId());
		em.remove(video);
	}

	/**
	 * This method retrieves all the videos
	 * @return List of videos
	 */
	@Override
	public List<Video> findAll() {
		TypedQuery<Video> query = em.createNamedQuery("Video.selectAllSortByYear",Video.class);
		List<Video> list = query.getResultList();
		LOGGER.log(Level.INFO, "{0} Videos returned", list.size());
		return list ;
	}

	/**
	 * This method finds the video with a specific title
	 * @param title title of a video
	 * @return Object of video
	 */
	@Override
	public Video findByTitle(String title) {
		TypedQuery<Video> query = em.createNamedQuery("Video.findByTitle",Video.class);
		query.setParameter("vtitle", title);
	   
		List<Video> video = query.getResultList();
		if(video.size() == 1) {
			LOGGER.log(Level.INFO, "Video returned: {0}",video.toString());
			return video.get(0);		  
		}
		else{
			LOGGER.log(Level.INFO, "Video with title {0} does not exist", title);
			return null;
		}
	}
	
	/**
	 * This method finds all videos with matching substring of a title
	 * @param title Any substring of a title
	 * @return List of videos with a title match
	 */
	@Override
	public List<Video> findByTitleSubstring(String title) {
		TypedQuery<Video> query = em.createNamedQuery("Video.findByTitle",Video.class);
		query.setParameter("vtitle", "%" + title + "%");
		List<Video> list = query.getResultList();
		LOGGER.log(Level.INFO, "{0} Videos returned", list.size());
		return list ;
	}

	/**
	 * This method finds the top rated videos
	 * @param type type of a video
	 * @return List of videos
	 */
	@Override
	public List<Video> findTopRatedVideos(String type) {
		TypedQuery<Video> query = em.createNamedQuery("Video.selectTopRated",Video.class);
		query.setParameter("vtype", type);
		query.setMaxResults(10);
		List<Video> list = query.getResultList();
		LOGGER.log(Level.INFO, "{0} Videos returned", list.size());
		return list ;
	}

	/**
	 * This method filters the videos according to input
	 * @param type type of a video
	 * @param year released year of a video
	 * @param genre genre of a video
	 * @param sort sort by criteria
	 * @return List of videos
	 */
	@Override
	public List<Video> findByFilter(String type, int year, String genre, String sort) {
		TypedQuery<Video> query =null;
	
		if(type.equals("null") && year == 0 && genre.equals("null"))
		{
			if(sort.equals("imdbRating"))
				 query = em.createNamedQuery("Video.selectAllSortByRating",Video.class);	
			else if(sort.equals("imdbVotes"))
				 query = em.createNamedQuery("Video.selectAllSortByVotes",Video.class);
			else
				 query = em.createNamedQuery("Video.selectAllSortByYear",Video.class);
		}
		else if(type.equals("null") && year == 0 && !genre.equals("null"))
		{
			if(sort.equals("imdbRating"))
				 query = em.createNamedQuery("Video.selectByGenreSortByRating",Video.class);	
			else if(sort.equals("imdbVotes"))
				 query = em.createNamedQuery("Video.selectByGenreSortByVotes",Video.class);
			else
				 query = em.createNamedQuery("Video.selectByGenreSortByYear",Video.class);
			
			query.setParameter("vgenre", "%" + genre + "%");
		}
		else if(type.equals("null") && year != 0 && genre.equals("null"))
		{
			if(sort.equals("imdbRating"))
				 query = em.createNamedQuery("Video.selectByYearSortByRating",Video.class);	
			else if(sort.equals("imdbVotes"))
				 query = em.createNamedQuery("Video.selectByYearSortByVotes",Video.class);
			else
				 query = em.createNamedQuery("Video.selectByYearSortByYear",Video.class);
			
			query.setParameter("vyear", year);
		}
		else if(type.equals("null") && year != 0 && !genre.equals("null"))
		{
			if(sort.equals("imdbRating"))
				 query = em.createNamedQuery("Video.selectByYearGenreSortByRating",Video.class);	
			else if(sort.equals("imdbVotes"))
				 query = em.createNamedQuery("Video.selectByYearGenreSortByVotes",Video.class);
			else
				 query = em.createNamedQuery("Video.selectByYearGenreSortByYear",Video.class);
		
			query.setParameter("vyear", year);
			query.setParameter("vgenre", "%" + genre + "%");
		}
		else if(!type.equals("null") && year == 0 && genre.equals("null"))
		{
			if(sort.equals("imdbRating"))
				 query = em.createNamedQuery("Video.selectByTypeSortByRating",Video.class);	
			else if(sort.equals("imdbVotes"))
				 query = em.createNamedQuery("Video.selectByTypeSortByVotes",Video.class);
			else
				 query = em.createNamedQuery("Video.selectByTypeSortByYear",Video.class);
		
			query.setParameter("vtype", type);
		}
		else if(!type.equals("null") && year == 0 && !genre.equals("null"))
		{
			if(sort.equals("imdbRating"))
				 query = em.createNamedQuery("Video.selectByTypeGenreSortByRating",Video.class);	
			else if(sort.equals("imdbVotes"))
				 query = em.createNamedQuery("Video.selectByTypeGenreSortByVotes",Video.class);
			else
				 query = em.createNamedQuery("Video.selectByTypeGenreSortByYear",Video.class);
			
			query.setParameter("vtype", type);
			query.setParameter("vgenre", "%" + genre + "%");
		}
		else if(!type.equals("null") && year != 0 && genre.equals("null"))
		{
			if(sort.equals("imdbRating"))
				 query = em.createNamedQuery("Video.selectByTypeYearSortByRating",Video.class);	
			else if(sort.equals("imdbVotes"))
				 query = em.createNamedQuery("Video.selectByTypeYearSortByVotes",Video.class);
			else
				 query = em.createNamedQuery("Video.selectByTypeYearSortByYear",Video.class);
		
			query.setParameter("vtype", type);
			query.setParameter("vyear", year);
		}
		else if(!type.equals("null") && year != 0 && !genre.equals("null"))
		{
			if(sort.equals("imdbRating"))
				 query = em.createNamedQuery("Video.selectByTypeYearGenreSortByRating",Video.class);	
			else if(sort.equals("imdbVotes"))
				 query = em.createNamedQuery("Video.selectByTypeYearGenreSortByVotes",Video.class);
			else
				 query = em.createNamedQuery("Video.selectByTypeYearGenreSortByYear",Video.class);
		
			query.setParameter("vtype", type);
			query.setParameter("vyear", year);
			query.setParameter("vgenre", "%" + genre + "%");
		}
		List<Video> list = query.getResultList();
		LOGGER.log(Level.INFO, "{0} Videos returned", list.size());
		return list ;
	}
	
	/**
	 * This method returns the Imdb ID of a specific video title
	 * @param title title of a video
	 * @return ImdbID
	 */
	@Override
	public String getImdbID(String title) {
		TypedQuery<Video> query = em.createNamedQuery("Video.findByTitle",Video.class);
		query.setParameter("vtitle", title);
		List<Video> video = query.getResultList();
		if(video.size() == 1) {
			LOGGER.log(Level.INFO, "Returned IMDB Id {0}",video.get(0).getImdbId());
			return video.get(0).getImdbId();		  
		}
		else{
			LOGGER.log(Level.INFO, "Returned Null");
			return null;
		}
	}

	/** 
	 * This method returns the video with a particular ID
	 * @param videoId ID of video
	 * @return video object
	 */
	@Override
	public Video findByID(String videoId) {
		LOGGER.log(Level.INFO, "Video with Id: {0} found",videoId);
		return em.find(Video.class, videoId);
	}
	
}
