package org.iesalixar.aleal.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
//import org.codehaus.jettison.json.JSONArray;
//import org.codehaus.jettison.json.JSONObject;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import org.hibernate.Session;
import org.iesalixar.aleal.dao.BookDAO;
import org.iesalixar.aleal.helper.HibernateUtil;
import org.iesalixar.aleal.model.Book;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Path("/book-service")
public class BookServiceImpl implements BookService {

	final static Logger logger = Logger.getLogger(BookServiceImpl.class);

	@GET
	@Path("/book-by-isbn/{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getBookByIsbn(@PathParam("isbn") int bookIsbn) {

		String book = null;
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			book = bookByIsbn(bookIsbn);
			logger.info("REST web service 'BookServiceImpl.getBookByIsbn' method has been invoked.");
		} catch (Exception e) {
			logger.error("'BookServiceImpl.getBookByIsbn' method has raised an exception: " + e.getMessage());
		}
		return Response.ok(book).build();

	}

	@GET
	@Path("/book-by-isbn-and-year/{isbn}/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getBookByIsbnAndYear(@PathParam("isbn") int bookIsbn, @PathParam("year") int year) {

		String book = null;
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			book = bookByIsbnAndYear(bookIsbn, year);
			logger.info("REST web service 'BookServiceImpl.getBookByIsbnAndYear' method has been invoked.");
		} catch (Exception e) {
			logger.error("'BookServiceImpl.getBookByIsbnAndYear' method has raised an exception: " + e.getMessage());
		}
		return Response.ok(book).build();

	}

	@GET
	@Path("/get-all-books")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getAllBooks() {

		String books = null;
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			books = allBooks();
			logger.info("REST web service 'BookServiceImpl.getAllBooks - operation GET' method has been invoked.");
		} catch (Exception e) {
			logger.error(
					"'BookServiceImpl.getAllBooks - operation GET' method has raised an exception: " + e.getMessage());
		}
		return Response.ok(books).build();

	}

	@POST
	@Path("/add-book")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response addBook(Book book) {
		Session session = null;
		JSONObject jsonObject = new JSONObject();
		int newBookId;
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();

			System.out.println(book.toString());
			session.beginTransaction();
			newBookId = (Integer) session.save(book);
			session.getTransaction().commit();
			session.close();

			jsonObject.put("created-book-id", String.valueOf(newBookId));
			logger.info("REST web service 'BookServiceImpl.addBook - operation POST' method has been invoked.");
		} catch (Exception e) {
			logger.error(
					"'BookServiceImpl.addBook - operation POST' method has raised an exception: " + e.getMessage());
			Response.serverError().build();
			jsonObject.put("status", "error");
		}

		return Response.ok(jsonObject.toString()).build();
	}

	@PUT
	@Path("/put-book")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response fullModifyBook(Book book) {
		Session session = null;
		JSONObject jsonObject = new JSONObject();
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();

			System.out.println(book.toString());
			session.beginTransaction();
			session.update(book);
			session.getTransaction().commit();
			session.close();

			jsonObject.put("put-book-id", String.valueOf(book.getId()));
			logger.info("REST web service 'BookServiceImpl.fullModifyBook - operation PUT' method has been invoked.");
		} catch (Exception e) {
			logger.error("'BookServiceImpl.fullModifyBook - operation PUT' method has raised an exception: "
					+ e.getMessage());
			Response.serverError().build();
		}

		return Response.ok(jsonObject.toString()).build();
	}

	@PATCH
	@Path("/patch-book")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response partialModifyBook(Book book) {
		Session session = null;
		Book bookToPatch = null;
		Book finaleBook = null;
		JSONObject jsonObject = new JSONObject();
		try {

			bookToPatch = BookDAO.getBookById(book.getId());
			finaleBook = updateBookProperties(bookToPatch, book);

			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();

			System.out.println(finaleBook.toString());
			session.beginTransaction();
			session.update(finaleBook);
			session.getTransaction().commit();
			session.close();

			jsonObject.put("patch-book-id", String.valueOf(finaleBook.getId()));
			logger.info(
					"REST web service 'BookServiceImpl.partialModifyBook - operation PATCH' method has been invoked.");
		} catch (Exception e) {
			logger.error("'BookServiceImpl.partialModifyBook - operation PATCH' method has raised an exception: "
					+ e.getMessage());
			Response.serverError().build();
		}

		return Response.ok(jsonObject.toString()).build();
	}

	@DELETE
	@Path("/remove-book")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response removeBook(Book book) {
		Session session = null;
		JSONObject jsonObject = new JSONObject();
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();

			System.out.println(book.toString());
			session.beginTransaction();
			session.remove(book);
			session.getTransaction().commit();
			session.close();

			jsonObject.put("status", "200");
			jsonObject.put("book-id-removed", String.valueOf(book.getId()));
			logger.info("REST web service 'BookServiceImpl.removeBook - operation DELETE' method has been invoked.");
		} catch (Exception e) {
			logger.error("'BookServiceImpl.removeBook - operation DELETE - book' method has raised an exception: "
					+ e.getMessage());
			Response.serverError().build();
			jsonObject.put("status", "500");
		}
		return Response.ok(jsonObject.toString()).build();
	}
	
	@DELETE
	@Path("/remove-book-id/{bookId}/{studentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response removeBookId(@PathParam("bookId") int bookId, @PathParam("studentId") int studentId) {
		Session session = null;
		JSONObject jsonObject = new JSONObject();
		
		Book book = new Book();
		book.setId(bookId);
		book.getStudent().setId(studentId);
		
		try {
			HibernateUtil.buildSessionFactory();
			HibernateUtil.openSessionAndBindToThread();
			session = HibernateUtil.getSessionFactory().getCurrentSession();

			session.beginTransaction();
			session.remove(book);
			session.getTransaction().commit();
			session.close();

			jsonObject.put("status", "200");
			jsonObject.put("book-id-removed", String.valueOf(book.getId()));
			logger.info("REST web service 'BookServiceImpl.removeBook - operation DELETE' method has been invoked.");
		} catch (Exception e) {
			logger.error("'BookServiceImpl.removeBook - operation DELETE - book' method has raised an exception: "
					+ e.getMessage());
			Response.serverError().build();
			jsonObject.put("status", "500");
		}
		return Response.ok(jsonObject.toString()).build();
	}

	private String bookByIsbn(int bookIsbn) {
		JSONObject response = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

		JSONObject jsonBook = null;
		JSONObject jsonStudent = null;
		Book book = null;
		try {
			book = BookDAO.getBookByIsbn(bookIsbn);

			jsonBook = new JSONObject();
			jsonBook.put("Id", book.getId());
			jsonBook.put("Author", book.getAuthor());
			jsonBook.put("Title", book.getTitle());
			jsonBook.put("Year", book.getYear());
			jsonBook.put("Isbn", book.getIsbn());

			jsonStudent = new JSONObject();
			jsonStudent.put("Nid", book.getStudent().getNid());
			jsonStudent.put("Birthyear", book.getStudent().getBirthyear());
			jsonStudent.put("Course", book.getStudent().getCourse());
			jsonStudent.put("Name", book.getStudent().getName());
			jsonStudent.put("Surnames", book.getStudent().getSurnames());

			jsonBook.put("Student", jsonStudent);
			response.put("book-by-isbn", jsonBook);
		} catch (Exception e) {
			logger.error("'BookServiceImpl.bookByIsbn' private method has raised an exception: " + e.getMessage());
		}
		return jsonBook.toString();
	}

	private String bookByIsbnAndYear(int bookIsbn, int year) {
		JSONObject response = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

		JSONObject jsonBook = null;
		JSONObject jsonStudent = null;
		Book book = null;
		try {
			book = BookDAO.getBookByIsbn(bookIsbn);
			jsonBook = new JSONObject();

			if (book.getYear() == year) {
				jsonBook.put("Id", book.getId());
				jsonBook.put("Author", book.getAuthor());
				jsonBook.put("Title", book.getTitle());
				jsonBook.put("Year", book.getYear());
				jsonBook.put("Isbn", book.getIsbn());

				jsonStudent = new JSONObject();
				jsonStudent.put("Nid", book.getStudent().getNid());
				jsonStudent.put("Birthyear", book.getStudent().getBirthyear());
				jsonStudent.put("Course", book.getStudent().getCourse());
				jsonStudent.put("Name", book.getStudent().getName());
				jsonStudent.put("Surnames", book.getStudent().getSurnames());

				jsonBook.put("Student", jsonStudent);
			} else
				jsonBook.put("status", 404);
			response.put("book-by-isbn-and-year", jsonBook);
		} catch (Exception e) {
			logger.error(
					"'BookServiceImpl.bookByIsbnAndYear' private method has raised an exception: " + e.getMessage());
		}
		return jsonBook.toString();
	}

	private String allBooks() {
		JSONObject response = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		Collection<JSONObject> items = new ArrayList<JSONObject>();
		JSONObject jsonBook = null;
		JSONObject jsonStudent = null;
		List<Book> books = null;
		try {
			books = BookDAO.getAllBooks();

			for (Book book : books) {
				jsonBook = new JSONObject();
				jsonBook.put("id", book.getId());
				jsonBook.put("author", book.getAuthor());
				jsonBook.put("title", book.getTitle());
				jsonBook.put("year", book.getYear());
				jsonBook.put("isbn", book.getIsbn());

				jsonStudent = new JSONObject();
				jsonStudent.put("nid", book.getStudent().getNid());
				jsonStudent.put("birthyear", book.getStudent().getBirthyear());
				jsonStudent.put("course", book.getStudent().getCourse());
				jsonStudent.put("name", book.getStudent().getName());
				jsonStudent.put("surnames", book.getStudent().getSurnames());

				jsonBook.put("student", jsonStudent);
				items.add(jsonBook);
			}

			response.put("get-all-books", new JSONArray(items));
		} catch (Exception e) {
			logger.error("'BookServiceImpl.allBooks' private method has raised an exception: " + e.getMessage());
		}
		return response.toString();
	}

	private static Book updateBookProperties(Book bookToPatch, Book book) {
		Book result = new Book();
		result.setId(bookToPatch.getId());

		if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
			result.setAuthor(bookToPatch.getAuthor());

		} else {
			result.setAuthor(book.getAuthor());
		}

		if (book.getTitle() == null || book.getTitle().isEmpty()) {
			result.setTitle(bookToPatch.getTitle());

		} else {
			result.setTitle(book.getTitle());
		}

		if (book.getIsbn() == null || book.getIsbn().isEmpty()) {
			result.setIsbn(bookToPatch.getIsbn());

		} else {
			result.setIsbn(book.getIsbn());
		}

		if (book.getYear() == null || book.getYear() == 0) {
			result.setYear(bookToPatch.getYear());

		} else {
			result.setYear(book.getYear());
		}

		if (book.getStudent() == null) {
			result.setStudent(bookToPatch.getStudent());

		} else {
			result.setStudent(book.getStudent());
		}

		return result;
	}

}