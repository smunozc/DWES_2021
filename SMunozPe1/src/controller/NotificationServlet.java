package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.NotificationDAOI;
import dao.NotificationDAOImpl;
import dao.UserDAOI;
import dao.UserDAOImpl;
import misc.RegexValidator;
import model.Notification;
import model.User;

@WebServlet("/notification")
public class NotificationServlet extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 3245779398753064118L;

	final static Logger logger = Logger.getLogger(NotificationServlet.class);

	private NotificationDAOI notificationDAO = new NotificationDAOImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("Client has invoked GET operation [NotificationServlet.class]");

		try {

			request.getRequestDispatcher("/createNotification.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("Client has invoked POST operation [RegisterServlet.class]");

		// Username, Password and user data
		String province = request.getParameter("province");
		String municipality = request.getParameter("municipality");
		String notifier = request.getParameter("notifier");
		String body = request.getParameter("body");
		String link = request.getParameter("link");
		String title = request.getParameter("title");

		Notification notification = new Notification(province, municipality, notifier, body, link, title);

		// URLs
		String urlLanding = "/login.jsp";
		String urlRegister = "/register.jsp";

		try {
			notificationDAO.createNotification(notification);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(urlRegister);
		}
	}

}
