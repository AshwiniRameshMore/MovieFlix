package io.movieflix.api.exception;

/**
 * This is the user-defined Exception class for handling Entity Not found exception
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
public class EntityNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -7702647914541952283L;

	public EntityNotFoundException(String message){
		super(message);
	}
	
	public EntityNotFoundException(String message, Throwable cause){
		super(message, cause);
	}
}
