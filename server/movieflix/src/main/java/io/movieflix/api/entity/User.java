package io.movieflix.api.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * This is the User class defining it's attributes and getter/setters
 * 
 * @author Ashwini More
 * @version 1.0
 * @since 01-20-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "User.findByUsernamePassword", query = "SELECT u FROM User u WHERE u.username=:uname AND u.password=:upassword"), 
	@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username=:uname") 
})
public class User {

	@Id
	private String userId;
	@Column(unique=true)
	private String username;
	private String password;
	private String role;
	private String firstname;
	private String lastname;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public User() {
		this.userId = UUID.randomUUID().toString();
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}
	
	
}
