package org.iesalixar.aleal.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import org.iesalixar.aleal.dao.BookDAO;
import org.iesalixar.aleal.dao.StudentDAO;
import org.iesalixar.aleal.helper.HibernateUtil;
import org.iesalixar.aleal.model.Book;
import org.iesalixar.aleal.model.Student;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Path("/student-service")
public class StudentServiceImpl implements StudentService {
	
	final static Logger logger = Logger.getLogger(StudentServiceImpl.class);

	@GET
	@Path("/student-by-id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getStudentById(@PathParam("id") int id) {
		
		String student = null;
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			student = studentById(id);
			logger.info("REST web service 'StudentServiceImpl.getStudentById' method has been invoked.");
		} catch (Exception e) {
			logger.error("'StudentServiceImpl.getStudentById' method has raised an exception: " + e.getMessage());
		}
		return Response.ok(student).build();
		
	}
	
	
	private String studentById(int id) {
		JSONObject response = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		JSONObject jsonStudent = null;
		Student student = null;
		try {
			student = StudentDAO.getStudentById(id);

			jsonStudent = new JSONObject();
			jsonStudent.put("Nid", student.getNid());
			jsonStudent.put("Birthyear", student.getBirthyear());
			jsonStudent.put("Course", student.getCourse());
			jsonStudent.put("Name", student.getName());
			jsonStudent.put("Surnames", student.getSurnames());

			response.put("student-by-id", jsonStudent);
		} catch (Exception e) {
			logger.error("'BookServiceImpl.bookByIsbn' private method has raised an exception: " + e.getMessage());
		}
		return jsonStudent.toString();
	}

}
