package restjersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import model.User;

@Path("/login")
public class CheckUser {
	
	@GET
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("name") String name) {
		boolean exist;
		if(name.equals("user1")) {
			exist = true;
		} else {
			exist = false;
		}
		
		JSONObject userResponse = new JSONObject();
		userResponse.put("username", name);
		userResponse.put("exists", exist);
		
		return Response.ok(userResponse).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String checkUser(User user) {
		//TODO Investigar como hacer post en servicios REST de jersey
		if(user.getUsername() == "user1") {			
			return "El usuario ya existe";
		} else {
			return "El usuario está disponible";
		}
	}
}
