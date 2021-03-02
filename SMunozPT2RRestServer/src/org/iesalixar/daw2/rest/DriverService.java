package org.iesalixar.daw2.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iesalixar.daw2.dao.DriverDAOImpl;
import org.iesalixar.daw2.model.Driver;

@Path("/driver")
public class DriverService {
	
	/**
	 * RestServer2: crear objetos de la clase Driver
	 * @param driver
	 */
	@POST
	@Path("/add-driver")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response createDriver(Driver driver) {
		
		DriverDAOImpl.createDriver(driver);
		
		return Response.ok("POST request recieved correctly").build();
	}
	
	/**
	 * RestServer3: modificar objetos de la clase Driver
	 * @param driver
	 */
	@PATCH
	@Path("/patch-driver")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response updateDriverDetails(Driver driver) {
		
		if(DriverDAOImpl.updateDriverDetails(driver)) {
			return Response.ok(driver).build();
		} else {
			return Response.ok("Driver could not update successfully").build();
		}
	}

}
