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

@WebServlet("/patchBook")
public class PatchBookServlet extends HttpServlet {

	/**
	 * SERIAL
	 */
	private static final long serialVersionUID = 7666453851384796162L;
	
	final static Logger logger = Logger.getLogger(PatchBookServlet.class);
	
	private BookStudentClient bookStudentClient = new BookStudentClient();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Book book = new Book();
		Student student = new Student();
		
		try {			
			student.setId(Integer.parseInt(request.getParameter("studentid")));
			
			if(request.getParameter("bookid") != "") {
				book.setId(Integer.parseInt(request.getParameter("bookid")));
			}
			
			if(request.getParameter("booktitle") != "") {
				book.setTitle(request.getParameter("booktitle"));
			}
			
			if(request.getParameter("bookauthor") != "") {
				book.setAuthor(request.getParameter("bookauthor"));
			}
			
			if(request.getParameter("bookisbn") != "") {
				book.setIsbn(request.getParameter("bookisbn"));
			}
			
			if(request.getParameter("bookyear") != "") {
				book.setYear(Integer.parseInt(request.getParameter("bookyear")));
			}
			
			book.setStudent(student);
			
			String returnedValue = null;

			String result = bookStudentClient.patchBookStudent(book);
			
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
