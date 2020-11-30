package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

		logger.info("Client invoked method to login [UserDAOImpl.class]");

		Connection con = null;
		User returnedUser = null;

		try {

			con = ((BasicDataSource) ((new InitialContext()).lookup("java:/comp/env/jdbc/mariaDBConnection")))
					.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

		if (con != null) {

			String sql = "SELECT * FROM users WHERE user LIKE ?";
			PreparedStatement ps;

			try {

				ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setString(1, username);

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					String hashedPassword = rs.getNString("password");

					if (BCrypt.checkpw(password, hashedPassword)) {

						returnedUser = new User(username, password, rs.getNString("userType"));
						
						//Get userData
						sql = "select * from userData where user = ?;";
						ps = (PreparedStatement) con.prepareStatement(sql);
						ps.setString(1, returnedUser.getUsername());

						rs = ps.executeQuery();
						if (rs.next()) {
							returnedUser.setName(rs.getString("name"));
							returnedUser.setSurname(rs.getString("surname"));
							returnedUser.setEmail(rs.getString("email"));
							returnedUser.setBirthday(rs.getString("birthday"));
						} else {
							logger.info("The username does not have data [UserDAOImpl.class]");
						}
						
						//Get userDetailedData
						sql = "select * from userDetailedData where user = ?;";
						ps = (PreparedStatement) con.prepareStatement(sql);
						ps.setString(1, returnedUser.getUsername());

						rs = ps.executeQuery();
						if (rs.next()) {
							returnedUser.setNif(rs.getString("nif"));
							returnedUser.setWeight(rs.getString("weight"));
							returnedUser.setHeight(rs.getString("height"));
							returnedUser.setAcademicFormation(rs.getString("academicFormation"));
							returnedUser.setHobbies(rs.getString("hobbies"));
						} else {
							logger.info("The username does not have detailed data [UserDAOImpl.class]");
						}
						

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
		logger.info("Client invoked method to register [UserDAOImpl.class]");

		Connection con = null;
		boolean register = false;

		try {

			con = ((BasicDataSource) ((new InitialContext()).lookup("java:/comp/env/jdbc/mariaDBConnection")))
					.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

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
					sql = "select * from users where user = ?";
					ps = (PreparedStatement) con.prepareStatement(sql);
					ps.setString(1, user.getUsername());

					ResultSet rs = ps.executeQuery();
					rs = ps.executeQuery();

					if (!rs.next()) {

						// Uploading user, user data and hashed password to database
						// User and hashed password
						sql = "insert into users values(?,?,?)";
						String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
						ps = (PreparedStatement) con.prepareStatement(sql);

						ps.setString(1, user.getUsername());
						ps.setString(2, hashedPassword);
						ps.setString(3, user.getType());
						ps.executeQuery();

						// User data
						sql = "insert into userData (user,name,surname,email,birthday) values (?,?,?,?,?)";
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

		Connection con = null;
		boolean validation = false;

		try {

			con = ((BasicDataSource) ((new InitialContext()).lookup("java:/comp/env/jdbc/mariaDBConnection")))
					.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

		if (con != null) {
			String sql = "select user from users where user = ?;";
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

		Connection con = null;
		boolean upload = false;

		try {

			con = ((BasicDataSource) ((new InitialContext()).lookup("java:/comp/env/jdbc/mariaDBConnection")))
					.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

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

		Connection con = null;
		boolean validation = false;

		try {

			con = ((BasicDataSource) ((new InitialContext()).lookup("java:/comp/env/jdbc/mariaDBConnection")))
					.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

		if (con != null) {
			String sql = "select user from userDetailedData where user = ?;";
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
	public List<User> getAllUsers() {
		
		logger.info("Client invoked method to get all users [UserDAOImpl.class]");

		Connection con = null;
		List<User> users = null;

		try {

			con = ((BasicDataSource) ((new InitialContext()).lookup("java:/comp/env/jdbc/mariaDBConnection")))
					.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

		if (con != null) {
			String sql = "select * from users";
			PreparedStatement ps;

			try {
				ps = (PreparedStatement) con.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				User user;
				users = new ArrayList<User>();
					
				while (rs.next()) {
					
					user = new User(rs.getNString("user"), rs.getNString("password"),rs.getNString("userType"));
					
					users.add(user);
					
				} 
				
				
				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return users;
	}

	@Override
	public boolean updateUserData(User user) {
		logger.info("Client invoked method to login [UserDAOImpl.class]");

		Connection con = null;
		boolean result = false;

		try {

			con = ((BasicDataSource) ((new InitialContext()).lookup("java:/comp/env/jdbc/mariaDBConnection")))
					.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

		if (con != null) {

			String sql = "SELECT * FROM users WHERE user LIKE ?";
			PreparedStatement ps;

			try {

				ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setString(1, user.getUsername());

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					String hashedPassword = rs.getNString("password");

					if (BCrypt.checkpw(user.getPassword(), hashedPassword)) {
						
						//Update user
						sql = "UPDATE users SET userType = ? WHERE user = ?";
						ps = (PreparedStatement) con.prepareStatement(sql);
						ps.setString(1, user.getType());
						ps.setString(2, user.getUsername());
						
						result = true;
						
						//Update userData
						sql = "UPDATE userData SET name=?, surname=?, email=?, birthday=? where user = ?;";
						ps = (PreparedStatement) con.prepareStatement(sql);
						ps.setString(1, user.getName());
						ps.setString(2, user.getSurname());
						ps.setString(3, user.getEmail());
						ps.setString(4, user.getBirthday());
						ps.setString(5, user.getUsername());

						rs = ps.executeQuery();
						if (rs.next()) {
							logger.info("The user data has been updated succesfully [UserDAOImpl.class]");
						} else {
							logger.info("The username does not have data [UserDAOImpl.class]");
						}
						
						//Get userDetailedData
						sql = "UPDATE userData SET nif=?, weight=?, height=?, academicFormation=?, hobbies=? where user = ?;";
						ps = (PreparedStatement) con.prepareStatement(sql);
						
						ps.setString(1, user.getNif());
						ps.setString(2, user.getWeight());
						ps.setString(3, user.getHeight());
						ps.setString(4, user.getAcademicFormation());
						ps.setString(5, user.getHobbies());
						ps.setString(6, user.getUsername());

						rs = ps.executeQuery();
						if (rs.next()) {
							logger.info("The user detailed data has been updated succesfully [UserDAOImpl.class]");
						} else {
							logger.info("The username does not have detailed data [UserDAOImpl.class]");
						}
						

					} else {

						logger.info("Client tried to update data but the password is incorrect");

					}

				} else {

					logger.info("Client tried to update data but the user was not found");

				}

			} catch (SQLException e) {
				logger.error("Client tried to update data but connection to database failed");
				e.printStackTrace();
			}
		}

		return result;
	}

}
