package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

import dao.LanguageDAOImpl;

import org.apache.log4j.Logger;

@WebServlet("/checkOccurrences")
public class CheckOccurrencesServlet extends HttpServlet {

	private static final long serialVersionUID = 3245779398753064118L;

	final static Logger logger = Logger.getLogger(CheckOccurrencesServlet.class);
	
	private LanguageDAOImpl languageDAO = new LanguageDAOImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {

			String username = request.getParameter("language");
			String returnedValue = null;

			if(languageDAO.checkLanguage(username))
				returnedValue = "repetead";
			else
				returnedValue = "available";
			
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write(returnedValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
