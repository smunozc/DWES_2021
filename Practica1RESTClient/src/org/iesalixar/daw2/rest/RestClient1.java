package org.iesalixar.daw2.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

public class RestClient1 {
	
	// Get All
	
	public static void getAllSoldier() {

		String result = "";
		try {

			Client client = ClientBuilder.newClient(new ClientConfig().register(new LoggingFeature()));

			WebTarget webTarget = client
					.target("http://localhost:8080/Practica1RESTServer/apirest/soldier/get-all-soldier");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

			Response response = invocationBuilder.get();

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			result = response.readEntity(String.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(result);
	}

}
