package io.movieflix.api.repository;

import io.movieflix.api.entity.User;

/**
 * This is the UserRepository interface whose methods are implemented in UserRepositoryImpl class
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
public interface UserRepository {
   
	public User create(User user);
	
	public User findUser(String username, String password);

	public User findByUserName(String username);

	public User findByID(String userId);
}
