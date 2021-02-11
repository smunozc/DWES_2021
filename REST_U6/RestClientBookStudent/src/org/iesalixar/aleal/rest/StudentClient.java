package org.iesalixar.aleal.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

public class StudentClient {
	
	public String getStudentById(int id) {
		
		String result = "";
		try {
 
			Client client = ClientBuilder.newClient( new ClientConfig().register(new LoggingFeature()) );
			
			WebTarget webTarget = client.target("http://localhost:9097/RestServerBookStudent/apirest/student-service")
					.path("student-by-id")
					.path(String.valueOf(id));
			
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
