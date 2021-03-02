package org.iesalixar.aleal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.iesalixar.aleal.model.Book;
import org.iesalixar.aleal.model.Student;
import org.iesalixar.aleal.rest.BookStudentClient;

@WebServlet("/deleteBookParam")
public class DeleteBookServlet extends HttpServlet {

	/**
	 * SERIAL
	 */
	private static final long serialVersionUID = 7666453851384796162L;
	
	final static Logger logger = Logger.getLogger(DeleteBookServlet.class);
	
	private BookStudentClient bookStudentClient = new BookStudentClient();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int studentId;
		int bookId;
		
		try {			
			studentId = Integer.parseInt(request.getParameter("studentid"));
			bookId = Integer.parseInt(request.getParameter("bookid"));
			
			String returnedValue = null;

			String result = bookStudentClient.deleteBookStudentParams(bookId, studentId);
			
			if(result.contains("id"))
				returnedValue = result;
			else
				returnedValue = "ERROR";
			
			System.out.println("la consulta ha devuelto: "+returnedValue);
			
//			response.setContentType("text/html; charset=UTF-8");
//			response.getWriter().write(returnedValue);
			
			response.sendRedirect("index.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
