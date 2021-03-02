package org.iesalixar.aleal.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.logging.LoggingFeature;
import org.iesalixar.aleal.model.Book;
import org.json.JSONArray;
import org.json.JSONObject;

public class BookStudentClient {
	
	// Get all Books
	
	public static List<JSONObject> getAllBooks() {

		String result = "";
		try {

			Client client = ClientBuilder.newClient(new ClientConfig().register(new LoggingFeature()));

			WebTarget webTarget = client
					.target("http://localhost:8080/RestServerBookStudent/apirest/book-service/get-all-books");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

			Response response = invocationBuilder.get();

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			
			result = response.readEntity(String.class);
			
			
			JSONObject jsonObject = new JSONObject(result);
			
			List<JSONObject> listaLibrosJSON = new ArrayList<JSONObject>();
			
			JSONArray jsonArray = jsonObject.getJSONArray("get-all-books");
			for (int i = 0; i < jsonArray.length(); i++) {
			    JSONObject object = jsonArray.getJSONObject(i);
			    listaLibrosJSON.add(object);
			}

//			Gson gson = new Gson();
//	        List<Book> books = gson.fromJson(jsonObject.get("get-all-books").toString(), 
//	        		new TypeToken<List<Book>>(){}.getType());
	       
			return listaLibrosJSON;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// Get by ISBN

	public String getBookByIsbn(int isbn) {

		String result = "";
		try {

			Client client = ClientBuilder.newClient(new ClientConfig().register(new LoggingFeature()));

			WebTarget webTarget = client.target("http://localhost:8080/RestServerBookStudent/apirest/book-service")
					.path("get-by-isbn").path(String.valueOf(isbn));

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

			Response response = invocationBuilder.get();

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			result = response.readEntity(String.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// POST Book

	public String postBookStudent(Book book) {

		String result = "";
		try {

			Client client = ClientBuilder.newClient(new ClientConfig().register(new LoggingFeature()));

			WebTarget webTarget = client.target("http://localhost:8080/RestServerBookStudent/apirest/book-service")
					.path("add-book");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

			// Response response = invocationBuilder.get();

			Response response = invocationBuilder.build("POST", Entity.entity(book, MediaType.APPLICATION_JSON))
					.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true).invoke();

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			result = response.readEntity(String.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// PUT Book

	public String putBookStudent(Book book) {

		String result = "";
		try {

			Client client = ClientBuilder.newClient(new ClientConfig().register(new LoggingFeature()));

			WebTarget webTarget = client.target("http://localhost:8080/RestServerBookStudent/apirest/book-service")
					.path("put-book");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

			Response response = invocationBuilder.put(Entity.entity(book, MediaType.APPLICATION_JSON));

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			result = response.readEntity(String.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// PATCH Book

	public String patchBookStudent(Book book) {

		String result = "";
		try {

			Client client = ClientBuilder.newClient(new ClientConfig().register(new LoggingFeature()));

			WebTarget webTarget = client.target("http://localhost:8080/RestServerBookStudent/apirest/book-service")
					.path("patch-book");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

			Response response = invocationBuilder.build("PATCH", Entity.entity(book, MediaType.APPLICATION_JSON))
					.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true).invoke();

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			result = response.readEntity(String.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// DELETE Book

	public String deleteBookStudent(Book book) {

		String result = "";
		try {

			ClientConfig clientConfig = new ClientConfig();
			clientConfig.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true);

			Client client = ClientBuilder.newClient(clientConfig.register(new LoggingFeature()));

			WebTarget webTarget = client.target("http://localhost:8080/RestServerBookStudent/apirest/book-service")
					.path("remove-book");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

			Response response = invocationBuilder.build("DELETE", Entity.entity(book, MediaType.APPLICATION_JSON))
					.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true).invoke();

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			result = response.readEntity(String.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// DELETE Book with params

	public String deleteBookStudentParams(int bookId, int studentId) {

		String result = "";
		try {

			Client client = ClientBuilder.newClient(new ClientConfig().register(new LoggingFeature()));

			WebTarget webTarget = client.target("http://localhost:8080/RestServerBookStudent/apirest/book-service")
					.path("remove-book-id").path(Integer.toString(bookId)).path(Integer.toString(studentId));

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

			Response response = invocationBuilder.delete();

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			result = response.readEntity(String.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
