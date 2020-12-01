package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.userType;
//import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FilterAuth
 */
@WebFilter("/welcome")
public class FilterAuth implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		try {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;

			// URLs
			String urlLogin = "/login";
			String urlWelcomeRegular = "/welcome.jsp";
			String urlNotifierArea = "/notifierArea.jsp";

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");

			if (user != null) {
				// Check if user is admin
				if (user.getRole() != null) {

					if (user.getRole().equals(userType.ADMIN.getValue())) {

						chain.doFilter(request, response);

					} else if(user.getRole().equals(userType.NOTIFIER.getValue())) {
						
						request.getRequestDispatcher(urlNotifierArea).forward(request, response);

					} else if(user.getRole().equals(userType.TRACKER.getValue())) {
						//TODO tracker view
						
						request.getRequestDispatcher(urlWelcomeRegular).forward(request, response);
						
					} else {
						
						request.getRequestDispatcher(urlWelcomeRegular).forward(request, response);
						
					}

				} else {

					request.getRequestDispatcher(urlWelcomeRegular).forward(request, response);

				}

			} else {
				
				request.getRequestDispatcher(urlLogin).forward(request, response);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
