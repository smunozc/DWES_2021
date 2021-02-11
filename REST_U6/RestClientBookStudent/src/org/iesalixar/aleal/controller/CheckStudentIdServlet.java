package org.iesalixar.aleal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.iesalixar.aleal.rest.StudentClient;

@WebServlet("/checkStudentId")
public class CheckStudentIdServlet extends HttpServlet {

	private static final long serialVersionUID = 3245779398753064118L;

	final static Logger logger = Logger.getLogger(CheckStudentIdServlet.class);
	
	private StudentClient studentClient = new StudentClient();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {

			String studentId = request.getParameter("studentid");
			String returnedValue = null;

			String student = studentClient.getStudentById(Integer.valueOf(studentId));
			
			if(student.contains("id"))
				returnedValue = student;
			else
				returnedValue = "ERROR";
			
			System.out.println("la consulta ha devuelto: "+returnedValue);
			
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().write(returnedValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
