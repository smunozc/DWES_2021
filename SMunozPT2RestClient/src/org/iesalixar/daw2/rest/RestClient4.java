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
import org.iesalixar.daw2.model.OperatingRoom;
import org.iesalixar.daw2.model.Surgeon;
import org.json.JSONArray;
import org.json.JSONObject;

public class RestClient4 {
	
	// Get HospitalByNameBySurgeon
	
	public static void deleteOperatingroom(OperatingRoom operatingRoom) {

		String result = "";
		try {

			ClientConfig clientConfig = new ClientConfig();
			clientConfig.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true);

			Client client = ClientBuilder.newClient(clientConfig.register(new LoggingFeature()));

			WebTarget webTarget = client.target("http://localhost:8080/SMunozPT2RestServer/apirest/operatingroom/delete-operatingroom");

			Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

			Response response = invocationBuilder.build("DELETE", Entity.entity(operatingRoom, MediaType.APPLICATION_JSON))
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
