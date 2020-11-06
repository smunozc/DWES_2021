package dao;

public interface RegisterDAO {
	
	public boolean doRegister(String user, String password, String name, String surname, String email, String birthday);
	public boolean checkUser(String username);
	
}
