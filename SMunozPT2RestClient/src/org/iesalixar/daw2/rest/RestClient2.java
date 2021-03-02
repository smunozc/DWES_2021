package org.iesalixar.daw2.rest;

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
import org.iesalixar.daw2.model.Surgeon;
import org.json.JSONArray;
import org.json.JSONObject;

public class RestClient2 {
	
	// addSurgeon
	
	public static void addSurgeon(Surgeon surgeon) {

		String result = "";
		try {

			Client client = ClientBuilder.newClient(new ClientConfig().register(new LoggingFeature()));

			WebTarget webTarget = client.target("http://localhost:8080/SMunozPT2RestServer/apirest/surgeon/add-surgeon");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

			Response response = invocationBuilder.build("POST", Entity.entity(surgeon, MediaType.APPLICATION_JSON))
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
