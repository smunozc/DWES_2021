package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.UserDAOI;
import dao.UserDAOImpl;
import misc.RegexValidator;
import model.User;

@WebServlet("/register")
public class RegisterServlet extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 3245779398753064118L;

	final static Logger logger = Logger.getLogger(RegisterServlet.class);

	private UserDAOI userDAO = new UserDAOImpl();
	private RegexValidator validator = new RegexValidator();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("Client has invoked GET operation [RegisterServlet.class]");

		try {

			request.getRequestDispatcher("/register.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		logger.info("Client has invoked POST operation [RegisterServlet.class]");

		// Username, Password and user data
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");

		User user = new User(username, password, fullname, email);

		// URLs
		String urlLanding = "/login.jsp";
		String urlRegister = "/register.jsp";

		try {
			/**
			 * If user and password are correct, the server will redirect to the landing
			 * page after authentication, if not, it will redirect to login again.
			 */
			if (validator.checkUsername(username) && validator.checkPassword(password)
					&& validator.checkName(fullname) && validator.checkEmail(email)) {
				if (userDAO.doRegister(user)) {
					request.getRequestDispatcher(urlLanding).forward(request, response);
				} else {
					request.getRequestDispatcher(urlRegister).forward(request, response);
				}
			} else {
				logger.error("Form of data is invalid");
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().write("Incorrect input");
				request.getRequestDispatcher(urlRegister).include(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(urlRegister);
		}
	}

}
