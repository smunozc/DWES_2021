package org.iesalixar.daw2.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iesalixar.daw2.dao.DealerDAOImpl;
import org.iesalixar.daw2.model.Dealer;

@Path("/dealer")
public class DealerService {
	
	/**
	 * RestServer1: Dealer getDealerByNameByDriver(String dealerName,String driverName)
	 * @param driverLicense
	 * @param dealerName
	 * @return
	 */
	@GET
	@Path("/get-dealer-by-name-by-driver/{driverLicense}/{dealerName}")
	@Produces(MediaType.APPLICATION_JSON)
	public static Response getDealerByNameByDriverLicense(@PathParam("dealerName") String dealerName, @PathParam("driverLicense") String driverLicense) {
		
		Dealer dealer = DealerDAOImpl.getDealerByNameByDriverLicense(dealerName, driverLicense);
		if(dealer != null) {
			return Response.ok(dealer).build();
		} else {
			return Response.ok("Could not find dealer with name: " + dealerName + " and license " + driverLicense).build();
		}
	}

}
