package io.movieflix.api.service;

import java.util.List;

import io.movieflix.api.entity.Comment;

/**
 * This is the CommentService interface whose methods are implemented in CommentServiceImpl class
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
public interface CommentService {

	Comment create(String videoId, String userId, String comment_str);

	Comment update(String commentId, String comment_str);

	void delete(String commentId);

	List<Comment> findAllComments(String videoId);

}
