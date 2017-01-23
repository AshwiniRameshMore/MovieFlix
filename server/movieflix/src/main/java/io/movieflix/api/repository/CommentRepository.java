package io.movieflix.api.repository;

import java.util.List;

import io.movieflix.api.entity.Comment;

/**
 * This is the CommentRepository interface whose methods are implemented in CommentRepositoryImpl class
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
public interface CommentRepository {

	Comment create(Comment comment);

	Comment findByID(String commentId);

	Comment update(Comment comment);

	void delete(Comment comment);

	List<Comment> findAllComments(String videoId);

}
