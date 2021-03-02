package org.iesalixar.daw2.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iesalixar.daw2.dao.GarageDAOImpl;
import org.iesalixar.daw2.model.Garage;

@Path("/garage")
public class GarageService {
	
	/**
	 * RestServer4: eliminar objetos de la clase Garage
	 * @param garage
	 */
	@DELETE
	@Path("/delete-garage")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response removeGarage(Garage garage) {
		
		if(GarageDAOImpl.removeGarage(garage)) {
			return Response.ok("Garage has been deleted").build();
		} else {
			return Response.ok("Could not delete garage").build();
		}
		
	}

}
