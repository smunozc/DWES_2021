package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;

import dao.LanguageDAOImpl;

import model.Language;

import org.apache.log4j.Logger;

@WebServlet("/languages")
public class LanguageServlet extends HttpServlet{

	private static final long serialVersionUID = 3245779398753064118L;
	
	final static Logger logger = Logger.getLogger(LanguageServlet.class);
	
	private LanguageDAOImpl languageDAO = new LanguageDAOImpl();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("Client has invoked GET operation [LanguageServlet.class]");
		
		List<Language> languages 	= null;
		String 	urlLanguages 		= "results.jsp";//welcomeRegisteredUser.jsp
		String 	urlIndex 			= "index.jsp"; // login.jsp
		
		try {
			languages = languageDAO.getLanguages();
			
			HttpSession session = request.getSession();
			session.setAttribute("languages", languages);
			session.setAttribute("values","value 1 - value 2 - value 3");
			response.sendRedirect(urlLanguages);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(urlIndex);
		}			
	}  	  	    
	

}
