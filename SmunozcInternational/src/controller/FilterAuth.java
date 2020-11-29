package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FilterAuth
 */
@WebFilter("/login")
public class FilterAuth implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		try {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;
	        HttpSession session = ((HttpServletRequest) request).getSession();
			
			// URLs
			String urlLogin = "/WEB-INF/login.jsp";
			String urlLanding = request.getServletPath() + "/welcome.jsp";

			// Check if there is a cookie with the name 'username' so login is not
			// necessary.
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
				// TODO Fix error and do filter for users
				request.getRequestDispatcher(urlLogin).forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		chain.doFilter(req, res);
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
