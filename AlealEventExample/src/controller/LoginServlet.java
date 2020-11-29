package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;

import dao.LanguageDAOImpl;

import model.Language;

import org.apache.log4j.Logger;

public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 3245779398753064118L;
	
	final static Logger logger = Logger.getLogger(LoginServlet.class);
	
	private LanguageDAOImpl languageDAO = new LanguageDAOImpl();
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
//		String username = "alex";
//		String password = "1234";
		
		logger.info("Client has invoked GET operation [LoginServlet.class]");
		
		List<Language> languages 	= null;
		String 	urlLanguages 		= "results.jsp";//welcomeRegisteredUser.jsp
		String 	urlIndex 			= "index.jsp"; // login.jsp
		
		try {
			languages = languageDAO.getLanguages();
			
			//boolean success = credentials.doLogin(username,password);
			
			//if(success) {
				
				// stuffs involved with success login 			// redirect welcome.jsp
			//}else {	
				// stuffs involved with non-success login		// redirect login.jsp				
			//}
			
			HttpSession session = request.getSession();
			session.setAttribute("languages", languages);
			response.sendRedirect(urlLanguages);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(urlIndex);
		}			
	}  	  	    
	

}
