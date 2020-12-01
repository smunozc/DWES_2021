package dao;

import java.util.List;

import model.Notification;
import model.User;

public interface NotificationDAOI {

	public boolean createNotification(Notification notification);

	public List<Notification> pendingOfAssignmentNotifications();

	public List<Notification> getNotifierNotifications(User user);

}
