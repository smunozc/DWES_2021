package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import dao.UserDAOI;
import dao.UserDAOImpl;
import model.User;

@WebServlet("/welcome")
public class WelcomeAdminServlet extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 3245779398753064118L;

	final static Logger logger = Logger.getLogger(WelcomeAdminServlet.class);

	private UserDAOI userDAO = new UserDAOImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("Client has invoked GET operation [LoginServlet.class]");
		HttpSession session = request.getSession();

		try {
			// URLs
			String urlLanding = "/welcomeAdmin.jsp";
			
			List<User> users = new ArrayList<User>();
			users = userDAO.getAllUsers();
			
			session.setAttribute("users", users);
			request.getRequestDispatcher(urlLanding).forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
