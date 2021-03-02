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
import org.json.JSONArray;
import org.json.JSONObject;

public class RestClient1 {
	
	// Get HospitalByNameBySurgeon
	
	public static void getHospitalByNameBySurgeon(String hospitalName, String surgeonLicense) {

		String result = "";
		try {

			Client client = ClientBuilder.newClient(new ClientConfig().register(new LoggingFeature()));

			WebTarget webTarget = client
					.target("http://localhost:8080/SMunozPT2RestServer/apirest/hospital/get-hospital-by-name-by-surgeon")
					.path(surgeonLicense)
					.path(hospitalName);

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
