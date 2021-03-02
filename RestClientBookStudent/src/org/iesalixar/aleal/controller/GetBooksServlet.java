package org.iesalixar.aleal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.iesalixar.aleal.model.Book;
import org.iesalixar.aleal.model.Student;
import org.iesalixar.aleal.rest.BookStudentClient;
import org.json.JSONObject;

@WebServlet("/showBooks")
public class GetBooksServlet extends HttpServlet {

	/**
	 * SERIAL
	 */
	private static final long serialVersionUID = 7666453851384796162L;
	
	final static Logger logger = Logger.getLogger(GetBooksServlet.class);
	
	private BookStudentClient bookStudentClient = new BookStudentClient();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession();
			
			List<JSONObject> listBooks = BookStudentClient.getAllBooks();
			
			System.out.println(listBooks);

			session.setAttribute("listBooks", listBooks);

			response.sendRedirect("showBook.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
