package com.smunozc.SmunozcLogin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.smunozc.SmunozcLogin.model.User;

public class UserDAOImpl implements UserDAOI {

	final static Logger logger = Logger.getLogger(UserDAOImpl.class);

	@Override
	public boolean doLogin(User user) {

		logger.info("Client invoked method to login [UserDAOImpl.class]");

		Connection con = DBConnection.getConnection();
		boolean validation = false;

		if (con != null) {
			String sql = "select * from users where user = ?;";
			PreparedStatement ps;

			try {
				ps = (PreparedStatement) con.prepareStatement(sql);

				ps.setString(1, user.getUsername());

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					String hashedPassword = rs.getNString("password");
					if (BCrypt.checkpw(user.getPassword(), hashedPassword)) {
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

	@Override
	public boolean doRegister(User user) {
		logger.info("Client invoked method to register [UserDAOImpl.class]");

		Connection con = DBConnection.getConnection();
		boolean register = false;

		if ((user.getUsername() != null && !user.getUsername().isEmpty() && user.getPassword() != null
				&& !user.getPassword().isEmpty())
				&& (user.getUsername() != null && !user.getUsername().isEmpty()
						|| user.getPassword() != null && !user.getPassword().isEmpty())) {

			if (con != null) {

				// if user is not in the database yet, insert it
				String sql;
				PreparedStatement ps;

				try {
					// Check if the user already exists
					sql = "select * from users where user = ?;";
					ps = (PreparedStatement) con.prepareStatement(sql);
					ps.setString(1, user.getUsername());

					ResultSet rs = ps.executeQuery();
					rs = ps.executeQuery();

					if (!rs.next()) {

						// Uploading user, user data and hashed password to database
						// User and hashed password
						sql = "insert into users values(?,?);";
						String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
						ps = (PreparedStatement) con.prepareStatement(sql);

						ps.setString(1, user.getUsername());
						ps.setString(2, hashedPassword);
						ps.executeQuery();

						// User data
						sql = "insert into userData (user,name,surname,email,birthday) values (?,?,?,?,?);";
						ps = (PreparedStatement) con.prepareStatement(sql);

						ps.setString(1, user.getUsername());
						ps.setString(2, user.getName());
						ps.setString(3, user.getSurname());
						ps.setString(4, user.getEmail());
						ps.setString(5, user.getBirthday());
						ps.executeQuery();

						// Check if the process was correct
						sql = "select * from users where user = ?;";
						ps = (PreparedStatement) con.prepareStatement(sql);
						ps.setString(1, user.getUsername());
						rs = ps.executeQuery();

						if (rs.next()) {
							register = true;
							logger.info("Client registered correctly [UserDAOImpl.class]");
						} else {
							logger.info("Client did not register correctly [UserDAOImpl.class]");
						}

					} else {
						logger.error("Client attempted to register, but username already exists [UserDAOImpl.class]");
					}

					con.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		} else {
			logger.error("Client failed to register, one or many fields are empty [UserDAOImpl.class]");
		}
		return register;

	}

	@Override
	public boolean checkUsername(User user) {

		logger.info("Client invoked method to check the availability of the username [UserDAOImpl.class]");

		Connection con = DBConnection.getConnection();
		boolean validation = false;

		if (con != null) {
			String sql = "select * from users where user = ?;";
			PreparedStatement ps;

			try {
				ps = (PreparedStatement) con.prepareStatement(sql);

				ps.setString(1, user.getUsername());

				ResultSet rs = ps.executeQuery();

				if (!rs.next()) {
					validation = true;
					logger.info("The username is available [UserDAOImpl.class]");
				} else {
					logger.info("The username is not available [UserDAOImpl.class]");
				}
				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return validation;

	}

	@Override
	public boolean uploadDetailedData(User user) {
		logger.info("Client invoked method to register [UserDAOImpl.class]");

		Connection con = DBConnection.getConnection();
		boolean upload = false;

		if (user.getUsername() != null && !user.getUsername().isEmpty()) {

			if (con != null) {

				String sql;
				PreparedStatement ps;
				ResultSet rs;

				try {

					// if detailedData is not in the database yet, insert it
					sql = "select * from userDetailedData where user = ?";
					ps = (PreparedStatement) con.prepareStatement(sql);

					ps.setString(1, user.getUsername());
					rs = ps.executeQuery();

					if (!rs.next()) {
						// User data
						sql = "insert into userDetailedData (user,nif,weight,height,academicFormation,hobbies) values (?,?,?,?,?,?);";
						ps = (PreparedStatement) con.prepareStatement(sql);

						ps.setString(1, user.getUsername());
						ps.setString(2, user.getNif());
						ps.setString(3, user.getWeight());
						ps.setString(4, user.getHeight());
						ps.setString(5, user.getAcademicFormation());
						ps.setString(6, user.getHobbies());
						ps.executeQuery();

						// Check if the process was correct
						sql = "select * from userDetailedData where user = ?;";
						ps = (PreparedStatement) con.prepareStatement(sql);
						ps.setString(1, user.getUsername());
						rs = ps.executeQuery();

						if (rs.next()) {
							upload = true;
							logger.info("Client uploaded information correctly [UserDAOImpl.class]");
						} else {
							logger.info("Client did not upload information correctly [UserDAOImpl.class]");
						}
					} else {
						logger.info(
								"Client tried to upload information from a user that has already linked information before [RegisterDAOImpl.class]");
					}

					con.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		} else {
			logger.error("Client failed to register, one or many fields are empty [UserDAOImpl.class]");
		}
		return upload;
	}

	@Override
	public boolean hasDetailedData(User user) {

		logger.info("Client invoked method to check if the user has detailed data in the database [UserDAOImpl.class]");

		Connection con = DBConnection.getConnection();
		boolean validation = false;

		if (con != null) {
			String sql = "select * from userDetailedData where user = ?;";
			PreparedStatement ps;

			try {
				ps = (PreparedStatement) con.prepareStatement(sql);

				ps.setString(1, user.getUsername());

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					validation = true;
					logger.info("Client have detailed information in the database. [UserDAOImpl.class]");
				} else {
					logger.info("Client does not have detailed information in the database. [UserDAOImpl.class]");
				}
				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return validation;
	}

	@Override
	public boolean getUserByUsername(User user) {
		
		logger.info("Client invoked method to get a user from the database [UserDAOImpl.class]");

		Connection con = DBConnection.getConnection();
		
		boolean validation = false;

		if (con != null) {
			String sql = "select * from users where user = ?;";
			PreparedStatement ps;

			try {
				ps = (PreparedStatement) con.prepareStatement(sql);

				ps.setString(1, user.getUsername());

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					user.setPassword(rs.getString("password"));
				} else {
					logger.info("The username does not exist in the database [UserDAOImpl.class]");
				}
				
				sql = "select * from userData where user = ?;";
				ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setString(1, user.getUsername());
				
				rs = ps.executeQuery();
				if (rs.next()) {
					user.setName(rs.getString("name"));
					user.setSurname(rs.getString("surname"));
					user.setEmail(rs.getString("email"));
					user.setBirthday(rs.getString("birthday"));
				} else {
					logger.info("The username does not have data [UserDAOImpl.class]");
				}
				
				sql = "select * from userDetailedData where user = ?;";
				ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setString(1, user.getUsername());
				
				rs = ps.executeQuery();
				if (rs.next()) {
					user.setNif(rs.getString("nif"));
					user.setWeight(rs.getString("weight"));
					user.setHeight(rs.getString("height"));
					user.setAcademicFormation(rs.getString("academicFormation"));
					user.setHobbies(rs.getString("hobbies"));
				} else {
					logger.info("The username does not have detailed data [UserDAOImpl.class]");
				}
				
				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return validation;
		
	}

}
