package org.iesalixar.aleal.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

public class BookStudentClient {
	
	private void getFtoCResponse() {
		try {
 
			Client client = ClientBuilder.newClient( new ClientConfig().register(new LoggingFeature()) );
			
			WebTarget webTarget = client.target("http://localhost:9097/Rest_CtoF/ctof").path("ftocservice");
			
			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
			
			Response response = invocationBuilder.get();
			
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
 
			String conversion = response.readEntity(String.class);
			
			System.out.println("\n============getFtoCResponse============");
			System.out.println(conversion);
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
public String getBookByIsbn(int isbn) {
		
		String result = "";
		try {
 
			Client client = ClientBuilder.newClient( new ClientConfig().register(new LoggingFeature()) );
			
			WebTarget webTarget = client.target("http://localhost:9097/RestServerBookStudent/apirest/book-service")
					.path("book-by-isbn")
					.path(String.valueOf(isbn));
			
			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
			
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

}
