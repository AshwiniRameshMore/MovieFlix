package io.movieflix.api.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.movieflix.api.entity.Comment;
import io.movieflix.api.service.CommentService;

/**
 * This is the Controller class of Comment Entity
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
@RestController
@CrossOrigin(origins = "http://localhost:4000")
@RequestMapping(value = "comments")
public class CommentController {
	
	private static final Logger LOGGER = Logger.getLogger( CommentController.class.getName() );
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * This method calls the CommentService method to create the new Comment object
	 * @param videoId ID of Video 
	 * @param comment_str User comment
	 * @return Comment object
	 */
	@RequestMapping(method = RequestMethod.POST, value="{videoId}/comment={comment_str}")
	public Comment create(@PathVariable("videoId") String videoId, @PathVariable("comment_str") String comment_str){
		LOGGER.log(Level.INFO, "Calling commentService - Create method");
		return commentService.create(videoId, comment_str);
	}
	
	/**
	 * This method calls the CommentService method to update the existing Comment object
	 * @param commentId ID of Comment
	 * @param comment_str User comment
	 * @return Comment object
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "{commentId}/comment={comment_str}")
	public Comment update(@PathVariable("commentId") String commentId, @PathVariable("comment_str") String comment_str){
		LOGGER.log(Level.INFO, "Calling commentService - Update method");
		return commentService.update(commentId, comment_str);
	}
	
	/**
	 * This method calls the CommentService method to delete the existing Comment object
	 * @param commentId ID of Comment
	 */
	@RequestMapping(method = RequestMethod.DELETE, value="{commentId}")
	public void delete(@PathVariable("commentId") String commentId){
		LOGGER.log(Level.INFO, "Calling commentService - Delete method");
		commentService.delete(commentId);
	}
	
	/**
	 * This method calls the CommentService method to retrieve all the comments of a specific Video
	 * @param videoId ID of Video
	 * @return List of comments
	 */
	@RequestMapping(method = RequestMethod.GET, value="{videoId}")
	public List<Comment> findAllComments(@PathVariable("videoId") String videoId){
		LOGGER.log(Level.INFO, "Calling commentService - findAllComments method");
		return commentService.findAllComments(videoId);
		
	}
	
}
