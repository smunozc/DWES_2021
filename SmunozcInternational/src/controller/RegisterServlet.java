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

			getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);

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
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		// Date format must be YYYY-MM-DD
		String birthday = request.getParameter("birthday");

		User user = new User(username, password, name, surname, email, birthday);

		// URLs
		String urlLanding = "/WEB-INF/login.jsp";
		String urlRegister = "/WEB-INF/register.jsp";
		String urlInvalidRegister = "/WEB-INF/invalidRegister.jsp";

		try {
			/**
			 * If user and password are correct, the server will redirect to the landing
			 * page after authentication, if not, it will redirect to login again.
			 */
			if (validator.checkUsername(username) && validator.checkName(name)) {
				if (userDAO.doRegister(user)) {
					getServletContext().getRequestDispatcher(urlLanding).forward(request, response);
				} else {
					getServletContext().getRequestDispatcher(urlRegister).forward(request, response);
				}
			} else {
				logger.error("Form of data is invalid");
				getServletContext().getRequestDispatcher(urlInvalidRegister).include(request, response);
				getServletContext().getRequestDispatcher(urlRegister).forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(urlRegister);
		}
	}

}
