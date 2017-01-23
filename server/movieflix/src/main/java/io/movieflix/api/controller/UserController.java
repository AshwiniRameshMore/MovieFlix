package io.movieflix.api.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.movieflix.api.entity.User;
import io.movieflix.api.service.UserService;

/**
 * This is the Controller class of User Entity
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
@RestController
@RequestMapping(value = "users")
public class UserController {
	
	private static final Logger LOGGER = Logger.getLogger( UserController.class.getName() );

	@Autowired
	private UserService userService;
	
	/**
	 * This method calls the UserService method to create new user
	 * @param user Object of User
	 * @return Newly created User object
	 */
	@RequestMapping(method = RequestMethod.POST)
	public User create(@RequestBody User user){
		LOGGER.log(Level.INFO, "Calling userService - create method");
    	return userService.create(user);
	}
	
	/**
	 * This method calls the UserService method to find the user by username and password
	 * @param username username of the user
	 * @param password password of the user
	 * @return User object
	 */
	@RequestMapping(method = RequestMethod.GET,value={"{username}/{password}"})
	public User findUser(@PathVariable("username") String username, @PathVariable("password") String password) {
		LOGGER.log(Level.INFO, "Calling userService - findUser method");
    	return userService.findUser(username, password);
	}

}
