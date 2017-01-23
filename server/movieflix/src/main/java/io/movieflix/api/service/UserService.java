package io.movieflix.api.service;

import io.movieflix.api.entity.User;

/**
 * This is the UserService interface whose methods are implemented in UserServiceImpl class
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
public interface UserService {

	public User create(User user);
	
	public User findUser(String username, String password);

	public User findByID(String userId);

}

