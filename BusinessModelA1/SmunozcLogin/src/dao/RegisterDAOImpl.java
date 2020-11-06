package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class RegisterDAOImpl implements RegisterDAO {

	final static Logger logger = Logger.getLogger(RegisterDAOImpl.class);
	
	@Override
	public boolean doRegister(String user, String password, String name, String surname, String email, String birthday) {
		logger.info("Client invoked method to register [RegisterDAOImpl.class]");
		
		Connection con = DBConnection.getConnecttion();
		boolean register = false;
		
		if((user != null && !user.isEmpty() && password != null && !password.isEmpty()) && (user != null && !user.isEmpty() || password != null && !password.isEmpty())) {
			
			if(con != null) {
				
				//if user is not in the database yet, insert it
				String sql;
				PreparedStatement ps;
				
				try {
					//Check if the user already exists
					sql = "select * from users where user = ?;";
					ps = (PreparedStatement) con.prepareStatement(sql);
					ps.setString(1, user);
					
					ResultSet rs = ps.executeQuery();
					rs = ps.executeQuery();
					
					if(!rs.next()) {
						
						//Uploading user, user data and hashed password to database	
						//User and hashed password
						sql = "insert into users values(?,?);";
						String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
						ps = (PreparedStatement) con.prepareStatement(sql);
						
						ps.setString(1, user);
						ps.setString(2, hashedPassword);
						ps.executeQuery();
						
						//User data
						sql = "insert into userData (user,name,surname,email,birthday) values (?,?,?,?,?);";
						ps = (PreparedStatement) con.prepareStatement(sql);
						
						ps.setString(1, user);
						ps.setString(2, name);
						ps.setString(3, surname);
						ps.setString(4, email);
						ps.setString(5, birthday);
						ps.executeQuery();
						
						//Check if the process was correct
						sql = "select * from users where user = ?;";
						ps = (PreparedStatement) con.prepareStatement(sql);
						ps.setString(1, user);
						rs = ps.executeQuery();
						
						if(rs.next()) {
							register = true;
							logger.info("Client registered correctly [RegisterDAOImpl.class]");
						} else {
							logger.info("Client did not register correctly [RegisterDAOImpl.class]");
						}
						
					} else {
						logger.error("Client attempted to register, but username already exists [RegisterDAOImpl.class]");
					}
					
					con.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		} else {
			logger.error("Client failed to register, one or many fields are empty [RegisterDAOImpl.class]");
		}
		return register;
	}
	
	@Override
	public boolean checkUser(String username) {
		logger.info("Client invoked method to check the availability of the username [RegisterDAOImpl.class]");
		
		Connection con = DBConnection.getConnecttion();
		boolean validation = false;
		
		if(con != null) {
			String sql = "select * from users where user = ?;";
			PreparedStatement ps;
			
			try {
				ps = (PreparedStatement) con.prepareStatement(sql);
				
				ps.setString(1, username);
				
				ResultSet rs = ps.executeQuery();
				
				if(!rs.next()) {
					validation = true;
					logger.info("The username is available [RegisterDAOImpl.class]");
				} else {
					logger.info("The username is not available [RegisterDAOImpl.class]");
				}
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return validation;
	}

}
