package dao;

import java.util.List;

import model.User;

public interface UserDAOI {

	/**
	 * User login.
	 * 
	 * @param user
	 * @return boolean
	 */
	public User doLogin(String username, String password);

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
	 * Gets all users from database.
	 * @return List<User>
	 */
	public List<User> getAllUsers();
	
	/**
	 * Updates the user information in the database.
	 * @return boolean
	 */
	public boolean updateUserData(User user);

}
