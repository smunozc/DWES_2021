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

			con = ((BasicDataSource) ((new InitialContext()).lookup("java:/comp/env/jdbc/DBConnectionMariaDB")))
					.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

		if (con != null) {

			String sql = "SELECT * FROM user WHERE username LIKE ?";
			PreparedStatement ps;

			try {

				ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setString(1, username);

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					String hashedPassword = rs.getNString("password");

					if (BCrypt.checkpw(password, hashedPassword)) {

						returnedUser = new User(username, password, rs.getNString("fullname"), rs.getNString("email"),
								rs.getNString("role"), rs.getInt("userstate"));

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

			con = ((BasicDataSource) ((new InitialContext()).lookup("java:/comp/env/jdbc/DBConnectionMariaDB")))
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
					sql = "select username from user where username = ?";
					ps = (PreparedStatement) con.prepareStatement(sql);
					ps.setString(1, user.getUsername());

					ResultSet rs = ps.executeQuery();
					rs = ps.executeQuery();

					if (!rs.next()) {

						// Uploading user to database
						// Hashed password
						String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));

						// User
						sql = "insert into user (username,password,fullname,email,role,userstate) values (?,?,?,?,?,?)";
						ps = (PreparedStatement) con.prepareStatement(sql);

						ps.setString(1, user.getUsername());
						ps.setString(2, hashedPassword);
						ps.setString(3, user.getFullname());
						ps.setString(4, user.getEmail());
						ps.setString(5, user.getRole());
						ps.setInt(6, user.getUserState());
						ps.executeQuery();

						// Check if the process was correct
						sql = "select * from user where username = ?;";
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
	public boolean checkUsername(String username) {

		logger.info("Client invoked method to check the availability of the username [UserDAOImpl.class]");

		Connection con = null;
		boolean validation = false;

		try {

			con = ((BasicDataSource) ((new InitialContext()).lookup("java:/comp/env/jdbc/DBConnectionMariaDB")))
					.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

		if (con != null) {
			String sql = "select username from user where username = ?;";
			PreparedStatement ps;

			try {
				ps = (PreparedStatement) con.prepareStatement(sql);

				ps.setString(1, username);

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
	public List<User> getAllUsers() {

		logger.info("Client invoked method to get all users [UserDAOImpl.class]");

		Connection con = null;
		List<User> users = null;

		try {

			con = ((BasicDataSource) ((new InitialContext()).lookup("java:/comp/env/jdbc/DBConnectionMariaDB")))
					.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

		if (con != null) {
			String sql = "select * from user";
			PreparedStatement ps;

			try {
				ps = (PreparedStatement) con.prepareStatement(sql);

				ResultSet rs = ps.executeQuery();
				User user;
				users = new ArrayList<User>();

				while (rs.next()) {

					user = new User(rs.getNString("username"), rs.getNString("password"), rs.getNString("fullname"),
							rs.getNString("email"), rs.getNString("role"), rs.getInt("userstate"));

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

			con = ((BasicDataSource) ((new InitialContext()).lookup("java:/comp/env/jdbc/DBConnectionMariaDB")))
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

						// Update user
						sql = "UPDATE user SET role = ? WHERE username = ?";
						ps = (PreparedStatement) con.prepareStatement(sql);
						ps.setString(1, user.getRole());
						ps.setString(2, user.getUsername());

						rs = ps.executeQuery();
						if (rs.next()) {
							result = true;
							logger.info("The user data has been updated succesfully [UserDAOImpl.class]");
						} else {
							logger.info("The username does not have data [UserDAOImpl.class]");
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

	@Override
	public boolean setState(User user) {
		logger.info("Client invoked method to login [UserDAOImpl.class]");

		Connection con = null;
		boolean result = false;

		try {

			con = ((BasicDataSource) ((new InitialContext()).lookup("java:/comp/env/jdbc/DBConnectionMariaDB")))
					.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

		if (con != null) {

			String sql = "UPDATE user SET userstate = ? WHERE username = ?";
			PreparedStatement ps;

			try {

				ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setString(1, user.getUsername());

				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					String hashedPassword = rs.getNString("password");

					if (BCrypt.checkpw(user.getPassword(), hashedPassword)) {

						sql = "SELECT userstate FROM user WHERE username = ?";
						ps = (PreparedStatement) con.prepareStatement(sql);
						ps.setString(1, user.getUsername());
						rs = ps.executeQuery();
						rs.next();
						int stateInDB = rs.getInt("userstate");
						
						if(stateInDB == user.getUserState()) {
							result = true;
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

		return result;
	}

}
