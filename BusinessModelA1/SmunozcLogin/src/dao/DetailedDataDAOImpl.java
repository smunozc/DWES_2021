package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DetailedDataDAOImpl implements DetailedDataDAO {

	final static Logger logger = Logger.getLogger(DetailedDataDAOImpl.class);
	
	@Override
	public boolean uploadData(String user, String nif, String weight, String height, String academicFormation, String hobbies) {
		logger.info("Client invoked method to register [RegisterDAOImpl.class]");
		
		Connection con = DBConnection.getConnecttion();
		boolean upload = false;
		
		if(!user.isBlank()) {
			
			if(con != null) {
				
				String sql;
				PreparedStatement ps;
				ResultSet rs;
				
				try {
					
					//if detailedData is not in the database yet, insert it
					sql = "select * from userDetailedData where user = ?";
					ps = (PreparedStatement) con.prepareStatement(sql);
					
					ps.setString(1, user);
					rs = ps.executeQuery();
					
					if(!rs.next()) {
						//User data
						sql = "insert into userDetailedData (user,nif,weight,height,academicFormation,hobbies) values (?,?,?,?,?,?);";
						ps = (PreparedStatement) con.prepareStatement(sql);
						
						ps.setString(1, user);
						ps.setString(2, nif);
						ps.setString(3, weight);
						ps.setString(4, height);
						ps.setString(5, academicFormation);
						ps.setString(6, hobbies);
						ps.executeQuery();
						
						//Check if the process was correct
						sql = "select * from userDetailedData where user = ?;";
						ps = (PreparedStatement) con.prepareStatement(sql);
						ps.setString(1, user);
						rs = ps.executeQuery();
						
						if(rs.next()) {
							upload = true;
							logger.info("Client uploaded information correctly [RegisterDAOImpl.class]");
						} else {
							logger.info("Client did not upload information correctly [RegisterDAOImpl.class]");
						}
					} else {
						logger.info("Client tried to upload information from a user that has already linked information before [RegisterDAOImpl.class]");
					}
					
					con.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		} else {
			logger.error("Client failed to register, one or many fields are empty [RegisterDAOImpl.class]");
		}
		return upload;
	}
}

