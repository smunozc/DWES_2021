package com.smunozc.SmunozcLogin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet("/logout")
public class LogoutServlet extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 3245779398753064118L;

	final static Logger logger = Logger.getLogger(LogoutServlet.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("Client has invoked GET operation [LogoutServlet.class]");

		// URLs
		String urlWelcome = "/welcome.jsp";
		String urlLanding = request.getContextPath();

		try {

			// Delete username cookie
			Cookie[] cookies = request.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("username")) {
					cookies[i].setMaxAge(0);
					response.addCookie(cookies[i]);
					break;
				}
			}

			HttpSession session = request.getSession();
			// Remove username session attribute
			// session.removeAttribute("username");

			// Invalidate session
			session.invalidate();

			response.sendRedirect(urlLanding);

		} catch (Exception e) {
			e.printStackTrace();
			getServletContext().getRequestDispatcher(urlWelcome).forward(request, response);
		}
	}

}
