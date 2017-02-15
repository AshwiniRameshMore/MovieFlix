package io.movieflix.api.repository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.movieflix.api.entity.User;

/**
 * This is the Repository class of User Entity
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
@Repository
public class UserRepositoryImpl implements UserRepository{

	private static final Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class.getName() );

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * This method creates new user
	 * @param user Object of User
	 * @return Newly created User object
	 */
	@Override
	public User create(User user) {
		LOGGER.log(Level.INFO, "User with Id: {0} saved",user.getUserId());
    	em.persist(user);
		return user;
	}

	/**
	 * This method finds the user by username and password
	 * @param username username of the user
	 * @param password password of the user
	 * @return User object
	 */
	@Override
	public User findUser(String username, String password) {
		TypedQuery<User> query = em.createNamedQuery("User.findByUsernamePassword", User.class);
		query.setParameter("uname", username);
		query.setParameter("upassword", password);
		
		List<User> users = query.getResultList();
		if (users != null && users.size() == 1) {
			LOGGER.log(Level.INFO, "User exist: {0}", users.get(0).toString());
			return users.get(0);
		}else{
			LOGGER.log(Level.INFO, "User does not exist");
			return null;
		}
	}

	/**
	 * This method finds the user by username
	 * @param username username of user
	 * @return User object 
	 */
	@Override
	public User findByUserName(String username) {
	TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);
		query.setParameter("uname", username);

		List<User> users = query.getResultList();
		if (users != null && users.size() == 1) {
			LOGGER.log(Level.INFO, "User exist: {0}", users.get(0).toString());
			return users.get(0);
		}else {
			LOGGER.log(Level.INFO, "Creating User");
			return null;
		}		
	}

	/**
	 * This method returns the user of a particular Id
	 * @param userId ID of user
	 * @return user object
	 */
	@Override
	public User findByID(String userId) {
		LOGGER.log(Level.INFO, "User with ID: {0} found", userId);
		return em.find(User.class, userId);
	}
	
}
