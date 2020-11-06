package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDAOImpl;

import org.apache.log4j.Logger;

@WebServlet("/login")
public class LoginServlet extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 3245779398753064118L;

	final static Logger logger = Logger.getLogger(LoginServlet.class);

	private LoginDAOImpl loginDAO = new LoginDAOImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("Client has invoked GET operation [LoginServlet.class]");

		try {
			// URLs
			String urlLogin = "/WEB-INF/login.jsp";
			String urlLanding = request.getServletContext().getContextPath() + "/welcome.jsp";
			
			// Check if there is a cookie with the name 'username' so login is not necessary.
			Cookie[] cookies = request.getCookies();

			boolean found = false;
			int i = 0;

			while (!found && i < cookies.length) {
				if (cookies[i].getName().equals("username")) {
					found = true;
					response.sendRedirect(urlLanding);
				} else {
					i++;
				}
			}
			if (!found) {
				getServletContext().getRequestDispatcher(urlLogin).forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("Client has invoked POST operation [LoginServlet.class]");

		// Username and Password
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// URLs
		String urlLanding = request.getContextPath() + "/welcome.jsp";
		String urlLogin = "/WEB-INF/login.jsp";

		try {
			/**
			 * If user and password are correct, the server will redirect to the landing
			 * page after authentication, if not, it will redirect to login again.
			 */
			if (loginDAO.doLogin(username, password)) {

				logger.info("Login correct! [LoginServlet.class]");

				HttpSession session = request.getSession();
				session.setAttribute("username", username);

				Cookie loginCookie = new Cookie("username", username);
				loginCookie.setMaxAge(30 * 60);// setting cookie to expire in 30 minutes.

				response.addCookie(loginCookie);
				response.sendRedirect(urlLanding);
				// getServletContext().getRequestDispatcher(urlLanding).forward(request,
				// response);

			} else {
				getServletContext().getRequestDispatcher(urlLogin).forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			getServletContext().getRequestDispatcher(urlLogin).forward(request, response);
		}
	}

}
