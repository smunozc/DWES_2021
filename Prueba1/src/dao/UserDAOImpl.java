package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.mindrot.jbcrypt.BCrypt;

import model.User;

public class UserDAOImpl implements UserDAOI {
	
	final static Logger logger = Logger.getLogger(UserDAOImpl.class);

	@Override
	public User doLogin(String username, String password) {
		logger.info("doLogin method has been invoked [UserDAOImpl.class]");
		
		Connection con = null;
		User returnedUser = null;
		
		try {
			
			con = ((BasicDataSource)((new InitialContext()).lookup("java:/comp/env/jdbc/mariaDBConnection"))).getConnection();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
		
		if(con != null) {
			
			String sql = "SELECT * FROM users WHERE user LIKE ?";
			PreparedStatement ps;
			
			try {
				
				ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setString(1, username);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					String hashedPassword = rs.getNString("password");
					
					if(BCrypt.checkpw(password, hashedPassword)) {
						
						returnedUser = new User(username, password, rs.getNString("type"));
						
						
					} else {
						
						logger.info("Client tried to login but the password is incorrect");
						
					}
					
				} else {
					
					logger.info("Client tried to login but the user was not found");
					
				}
				
			} catch (SQLException e) {
				logger.error("Client tried to login but connection to database failed");
				e.printStackTrace();
			}
		}
		
		return returnedUser;
	}

	@Override
	public boolean doRegister(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addDetailedData(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyDetailedData(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyUserPassword(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUserAccount(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
