package dao;

import model.User;

public interface UserDAOI {
	
	public User doLogin(String username, String password);
	
	public boolean doRegister(User user);
	
	public boolean addDetailedData(User user);
	
	public boolean modifyDetailedData(User user);
	
	public boolean modifyUserPassword(User user);
	
	public boolean deleteUserAccount(User user);
	
	public User getUserByUsername(String username);
}
