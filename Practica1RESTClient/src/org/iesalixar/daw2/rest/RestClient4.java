package org.iesalixar.daw2.rest;

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
import org.iesalixar.daw2.model.Soldier;

public class RestClient4 {
	
	// Delete entity
	
	public static void deleteSoldier(Soldier soldier) {

		String result = "";
		try {

			ClientConfig clientConfig = new ClientConfig();
			clientConfig.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true);

			Client client = ClientBuilder.newClient(clientConfig.register(new LoggingFeature()));

			WebTarget webTarget = client.target("http://localhost:8080/Practica1RESTServer/apirest/soldier/delete-soldier");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

			Response response = invocationBuilder.build("DELETE", Entity.entity(soldier, MediaType.APPLICATION_JSON))
					.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true).invoke();

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed | HTTP error code : " + response.getStatus());
			}

			result = response.readEntity(String.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(result);
	}

}
