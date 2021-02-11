package org.iesalixar.aleal.rest;

import javax.ws.rs.core.Response;

import org.iesalixar.aleal.model.Book;

public interface BookService {
	
	public Response getBookByIsbn(int bookIsbn);
	
	public Response getBookByIsbnAndYear(int bookIsbn,int year);
	
	public Response getAllBooks();
	
	public Response addBook(Book book);
	
	public Response fullModifyBook(Book book);
	
	public Response partialModifyBook(Book book);
	
	public Response removeBook(Book book);

}
