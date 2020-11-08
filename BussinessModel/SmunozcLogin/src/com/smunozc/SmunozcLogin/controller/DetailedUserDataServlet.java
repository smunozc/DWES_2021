package com.smunozc.SmunozcLogin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.smunozc.SmunozcLogin.dao.UserDAOI;
import com.smunozc.SmunozcLogin.dao.UserDAOImpl;
import com.smunozc.SmunozcLogin.model.User;

@WebServlet("/detailedData")
public class DetailedUserDataServlet extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 3245779398753064118L;

	final static Logger logger = Logger.getLogger(DetailedUserDataServlet.class);

	private UserDAOI userDAO = new UserDAOImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("Client has invoked GET operation [RegisterServlet.class]");

		try {

			// URLs
			String urlLogin = "/WEB-INF/login.jsp";
			String urlLanding = "/WEB-INF/detailedUserData.jsp";

			// Check if there is a cookie with the name 'username' so it can forward to
			// detailed user form.
			Cookie[] cookies = request.getCookies();

			boolean found = false;
			int i = 0;

			while (!found && i < cookies.length) {
				if (cookies[i].getName().equals("username")) {
					found = true;
					getServletContext().getRequestDispatcher(urlLanding).forward(request, response);
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

		logger.info("Client has invoked POST operation [RegisterServlet.class]");

		String urlLogin = "/WEB-INF/login.jsp";

		// Get the value from the cookie username to asign it to user variable
		Cookie[] cookies = request.getCookies();

		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("username")) {

				HttpSession session = request.getSession();
				String username = (String) session.getAttribute("username");
				String password = (String) session.getAttribute("password");

				User user = new User(username, password);
				user.setNif(request.getParameter("nif"));
				user.setWeight(request.getParameter("weight"));
				user.setHeight(request.getParameter("height"));
				user.setAcademicFormation(request.getParameter("academicFormation"));
				user.setHobbies(request.getParameter("hobbies"));

				// URLs
				String urlLanding = request.getContextPath() + "/welcome.jsp";
				String urlData = "/WEB-INF/detailedUserData.jsp";

				try {
					/**
					 * If user data uploads correctly to the table, the server will redirect to the
					 * landing page, if not, it will redirect to detailedData again.
					 */
					if (userDAO.uploadDetailedData(user)) {
						response.sendRedirect(urlLanding);
					} else {
						getServletContext().getRequestDispatcher(urlData).forward(request, response);
					}

				} catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect(urlData);
				}
				break;
			} else {
				getServletContext().getRequestDispatcher(urlLogin).forward(request, response);
			}
		}
	}
}
