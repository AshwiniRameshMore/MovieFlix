package io.movieflix.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is the user-defined Exception class for handling duplicate records
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 1235244149251577366L;

	public BadRequestException(String message){
		super(message);
	}
	
	public BadRequestException(String message, Throwable cause){
		super(message, cause);
	}
}
