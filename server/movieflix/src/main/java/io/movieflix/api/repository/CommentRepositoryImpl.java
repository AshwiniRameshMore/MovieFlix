package io.movieflix.api.repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.movieflix.api.entity.Comment;

/**
 * This is the Repository Class of Comment Entity
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
@Repository
public class CommentRepositoryImpl implements CommentRepository{

	private static final Logger LOGGER = Logger.getLogger(CommentRepositoryImpl.class.getName());

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * This method create the comment object
	 * @return comment object
	 */
	@Override
	public Comment create(Comment comment) {
		LOGGER.log(Level.INFO, "Comment with Id: {0} saved", comment.getCommentId());
     	em.persist(comment);
		return comment;
	}

	/**
	 * This method updates the comment object
	 * @param comment object 
	 * @return Modified comment object
	 */
	@Override
	public Comment update(Comment comment) {
		LOGGER.log(Level.INFO, "Comment with Id: {0} updated", comment.getCommentId());
		return em.merge(comment);
	}

	/**
	 * This method deletes the comment object
	 * @param comment comment object
	 */
	@Override
	public void delete(Comment comment) {
		LOGGER.log(Level.INFO, "Comment with Id: {0} removed", comment.getCommentId());
		em.remove(comment);
	}

	/**
	 * This method retrieves all comments specific to video ID
	 * @param videoId ID of video
	 * @return List of comments
	 */
	@Override
	public List<Comment> findAllComments(String videoId) {
		TypedQuery<Comment> query = em.createNamedQuery("Comment.findAllComments", Comment.class);
		query.setParameter("cid", videoId);
		List<Comment> list = query.getResultList();
		LOGGER.log(Level.INFO, "{0} Comments returned", list.size());
		return list ;
	}

	/**
	 * This method finds the comment by ID
	 * @param commentId ID of comment
	 * @return comment object
	 */
	@Override
	public Comment findByID(String commentId) {
		LOGGER.log(Level.INFO, "Comment with Id: {0} found",commentId);
    	return em.find(Comment.class, commentId);
	}
}
