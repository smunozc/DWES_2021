package com.smunozc.SmunozcLogin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.smunozc.SmunozcLogin.dao.UserDAOI;
import com.smunozc.SmunozcLogin.dao.UserDAOImpl;
import com.smunozc.SmunozcLogin.model.User;

@WebServlet("/checkOccurrences")
public class CheckOccurrencesServlet extends HttpServlet {

	private static final long serialVersionUID = 3245779398753064118L;

	final static Logger logger = Logger.getLogger(CheckOccurrencesServlet.class);

	private UserDAOI userDAO = new UserDAOImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String returnedValue = null;

			User user = new User(username, password);

			if (userDAO.checkUsername(user))
				returnedValue = "Available";
			else
				returnedValue = "Not available";

			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write(returnedValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
