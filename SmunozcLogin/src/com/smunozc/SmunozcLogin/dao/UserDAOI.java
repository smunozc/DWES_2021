package com.smunozc.SmunozcLogin.dao;

import com.smunozc.SmunozcLogin.model.User;

public interface UserDAOI {

	/**
	 * User login.
	 * 
	 * @param user
	 * @return boolean
	 */
	public boolean doLogin(User user);

	/**
	 * User register.
	 * 
	 * @param user
	 * @return boolean
	 */
	public boolean doRegister(User user);

	/**
	 * Check if username is available
	 * 
	 * @param user
	 * @return
	 */
	public boolean checkUsername(User user);

	/**
	 * Uploads detailed user data.
	 * 
	 * @param user
	 * @return boolean
	 */
	public boolean uploadDetailedData(User user);

	/**
	 * Checks if the user contains detailed data in the database.
	 * 
	 * @param user
	 * @return
	 */
	public boolean hasDetailedData(User user);

	/**
	 * Gets a user from the database with the User entered as a parameter, this User
	 * only needs to have the username.
	 * 
	 * @param user
	 * @return boolean
	 */
	public boolean getUserByUsername(User user);

}
