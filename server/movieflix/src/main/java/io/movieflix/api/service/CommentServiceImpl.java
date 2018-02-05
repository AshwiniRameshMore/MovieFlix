package io.movieflix.api.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.movieflix.api.entity.Comment;
import io.movieflix.api.entity.User;
import io.movieflix.api.entity.Video;
import io.movieflix.api.repository.CommentRepository;

/**
 * This is the Service Class of Comment Entity
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
@Service
public class CommentServiceImpl implements CommentService{

	private static final Logger LOGGER = Logger.getLogger( CommentServiceImpl.class.getName() );

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private VideoService videoService;
		
	@Autowired
	private UserService userService;
	
	/**
	 * This method retrieves the video and user objects and
	 * calls the CommentRepository method to create the new Comment object
	 * @param videoId ID of Video 
	 * @param userId ID of User 
	 * @param comment_str User comment
	 * @return Comment object
	 */
	@Override
	@Transactional
	public Comment create(String videoId, String userId, String comment_str) {
		Video video = videoService.findByID(videoId);
		User user = userService.findByID(userId);
		Comment comment = new Comment();
		comment.setVideo(video);
		comment.setUser(user);
		comment.setComment(comment_str);
		LOGGER.log(Level.INFO, "Calling commentRepository - create method");
		return commentRepository.create(comment);
	}

	/**
	 * This method checks if the comment exist and
	 * calls the CommentRepository method to update the existing Comment object
	 * @param commentId ID of Comment
	 * @param comment_str User comment
	 * @return Comment object
	 */
	@Override
	@Transactional
	public Comment update(String commentId, String comment_str) {
		Comment comment = commentRepository.findByID(commentId);
		if (comment == null) {
			LOGGER.log(Level.SEVERE, "Exception thrown - Comment not found");
			throw new EntityNotFoundException("Comment not found");
		}
	
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		comment.setDate(df.format(dateobj));
		comment.setComment(comment_str);
		LOGGER.log(Level.INFO, "Calling commentRepository - update method");
		return commentRepository.update(comment);
	}

	/**
	 * This method checks if the comment exist 
	 * and calls the CommentRepository method to delete the existing Comment object
	 * @param commentId ID of Comment
	 */
	@Override
	@Transactional
	public void delete(String commentId) {
		Comment comment = commentRepository.findByID(commentId);
		if (comment == null) {
			LOGGER.log(Level.SEVERE, "Exception thrown - Comment not found");
			throw new EntityNotFoundException("Comment not found");
		}
		LOGGER.log(Level.INFO, "Calling commentRepository - delete method");
    	commentRepository.delete(comment);
	}

	/**
	 * This method calls the CommentRepositoy method to retrieve all the comments of a specific Video
	 * @param videoId ID of Video
	 * @return List of comments
	 */
	@Override
	public List<Comment> findAllComments(String videoId) {
		LOGGER.log(Level.INFO, "Calling commentRepository - findAllComments method");
		return commentRepository.findAllComments(videoId);
	}

}
