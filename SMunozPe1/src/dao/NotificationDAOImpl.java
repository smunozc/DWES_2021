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

import model.Notification;
import model.User;

public class NotificationDAOImpl implements NotificationDAOI {

	final static Logger logger = Logger.getLogger(NotificationDAOImpl.class);

	@Override
	public boolean createNotification(Notification notification) {
		logger.info("Client invoked method to register [UserDAOImpl.class]");

		Connection con = null;
		boolean created = false;

		try {

			con = ((BasicDataSource) ((new InitialContext()).lookup("java:/comp/env/jdbc/DBConnectionMariaDB")))
					.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

		if (con != null) {

			// if user is not in the database yet, insert it
			String sql;
			PreparedStatement ps;

			try {
				// Uploading notification to database

				// Notification
				sql = "insert into notification (province,municipality,notifier,tracker,title,body,link,notificationstate) values (?,?,?,?,?,?,?,?)";
				ps = (PreparedStatement) con.prepareStatement(sql);

				ps.setString(1, notification.getProvince());
				ps.setString(2, notification.getMunicipality());
				ps.setString(3, notification.getNotifier());
				ps.setString(4, notification.getTracker());
				ps.setString(5, notification.getTitle());
				ps.setString(6, notification.getBody());
				ps.setString(7, notification.getLink());
				ps.setInt(8, notification.getNotificationState());
				ps.executeQuery();
				
				created = true;

				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			logger.error("Client failed to register, one or many fields are empty [UserDAOImpl.class]");
		}
		return created;
	}

	@Override
	public List<Notification> pendingOfAssignmentNotifications() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notification> getNotifierNotifications(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
