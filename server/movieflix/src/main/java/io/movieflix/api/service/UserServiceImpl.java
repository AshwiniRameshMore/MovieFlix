package io.movieflix.api.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.movieflix.api.entity.User;
import io.movieflix.api.exception.BadRequestException;
import io.movieflix.api.repository.UserRepository;

/**
 * This is the Service class of User Entity
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
@Service
public class UserServiceImpl implements UserService {
  
	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName() );

	@Autowired
	private UserRepository userRepository;

	/**
	 * This method calls the UserRepository method to create new user
	 * @param user Object of User
	 * @return Newly created User object
	 */
	@Override
	@Transactional
	public User create(User user) {
		LOGGER.log(Level.INFO, "Calling userRepository - findByUserName method");
		User existing = userRepository.findByUserName(user.getUsername());
		if (existing != null) {
			LOGGER.log(Level.SEVERE, "Throws Exception - User with {0} username already exists", user.getUsername());
			throw new BadRequestException("User with this username already exists");
		}
		LOGGER.log(Level.INFO, "Calling userRepository - Create method");
		return userRepository.create(user);
	}

	/**
	 * This method calls the UserRepository method to find the user by username and password
	 * @param username username of the user
	 * @param password password of the user
	 * @return true/false
	 */
	@Override
	@Transactional(readOnly = true)
	public Boolean findUser(String username, String password) {
		LOGGER.log(Level.INFO, "Calling userRepository - findUser method");
		User user = userRepository.findUser(username, password);
		boolean exists = true;
		if (user == null) {
			exists = false;
		}
		return exists;
	}

	/**
	 * This method returns the user object if it exists
	 * @param userId ID of user
	 * @return user object
	 */
	@Override
	@Transactional(readOnly = true)
	public User findByID(String userId) {
		User user = userRepository.findByID(userId);
		if (user == null) {
			LOGGER.log(Level.SEVERE, "Throws Exception - User not found");
			throw new EntityNotFoundException("User not found");
		}
		LOGGER.log(Level.INFO, "RETURN {0}", user.toString());
		return user;
	}
  
}
