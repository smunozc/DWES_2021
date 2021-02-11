package org.iesalixar.aleal.rest;

import javax.ws.rs.core.Response;

import org.iesalixar.aleal.model.Book;

public interface StudentService {
	
	public Response getStudentById(int studentId);

}
