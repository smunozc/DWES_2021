package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

//import model.User;

public class LoginDAOImpl implements LoginDAO {
	
	final static Logger logger = Logger.getLogger(LoginDAOImpl.class);

	@Override
	public boolean doLogin(String user, String password) {
		logger.info("Client invoked method to login [LoginDAOImpl.class]");
		
		Connection con = DBConnection.getConnecttion();
		boolean validation = false;
		
		if(con != null) {
			String sql = "select * from users where user = ?;";
			PreparedStatement ps;
			
			try {
				ps = (PreparedStatement) con.prepareStatement(sql);
				
				ps.setString(1, user);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					String hashedPassword = rs.getNString("password");
					if(BCrypt.checkpw(password, hashedPassword)) {
						validation = true;
					}
				}
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return validation;
	}
	
}
