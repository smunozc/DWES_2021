package org.iesalixar.daw2.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.logging.LoggingFeature;
import org.iesalixar.daw2.model.Driver;

public class RestClient3 {
	
	// patch entity
	
	public static void patchDriver(Driver driver) {

		String result = "";
		try {

			Client client = ClientBuilder.newClient(new ClientConfig().register(new LoggingFeature()));

			WebTarget webTarget = client.target("http://localhost:9096/SMunozPT2RRestServer/apirest/driver/patch-driver");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

			Response response = invocationBuilder.build("PATCH", Entity.entity(driver, MediaType.APPLICATION_JSON))
					.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true).invoke();

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
