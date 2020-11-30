package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import dao.UserDAOI;
import dao.UserDAOImpl;
import model.User;

@WebServlet("/login")
public class LoginServlet extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 3245779398753064118L;

	final static Logger logger = Logger.getLogger(LoginServlet.class);

	private UserDAOI userDAO = new UserDAOImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("Client has invoked GET operation [LoginServlet.class]");
		HttpSession session = request.getSession();

		try {
			// URLs
			String urlLogin = "/WEB-INF/login.jsp";
			String urlLanding = "/welcome";

			// Check if there is a cookie with the name 'username' so login is not
			// necessary.
			Cookie[] cookies = request.getCookies();

			boolean found = false;
			int i = 0;

			while (!found && i < cookies.length) {
				if (cookies[i].getName().equals("username")) {
					found = true;
				} else {
					i++;
				}
			}
			if (found) {
				request.getRequestDispatcher(urlLanding).forward(request, response);
			} else {
				request.getRequestDispatcher(urlLogin).forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("Client has invoked POST operation [LoginServlet.class]");
		HttpSession session = request.getSession();

		// Username and Password
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// URLs
		String urlLanding = request.getContextPath() + "/welcome";
		String urlLogin = "/WEB-INF/login.jsp";
		
		//Boolean login correct on post
		boolean loginCorrect = true;

		try {
			/**
			 * If user and password are correct, the server will redirect to the landing
			 * page after authentication, if not, it will redirect to login again.
			 */
			User user = userDAO.doLogin(username, password);
			
			if (user != null) {

				logger.info("Login correct! [LoginServlet.class]");
				
				session.setAttribute("user", user);

				Cookie loginCookie = new Cookie("username", user.getUsername());
				loginCookie.setMaxAge(30 * 60);// setting cookie to expire in 30 minutes.

				response.addCookie(loginCookie);
				response.sendRedirect(urlLanding);
				//request.getRequestDispatcher(urlLanding).forward(request, response);

			} else {

				logger.info("Login incorrect. [LoginServlet.class]");
				loginCorrect = false;
				
				request.getRequestDispatcher(urlLogin).forward(request, response);

			}
			
			session.setAttribute("loginCorrect", loginCorrect);

		} catch (Exception e) {
			e.printStackTrace();
			getServletContext().getRequestDispatcher(urlLogin).forward(request, response);
		}
	}

}
