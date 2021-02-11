package org.iesalixar.aleal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 3245779398753064118L;
	
	final static Logger logger = Logger.getLogger(LoginServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		logger.info("Client has invoked GET operation [LoginServlet.class]");
		try {
			response.sendRedirect("index.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("index.jsp");
		}			
	}  	  	    
	

}
