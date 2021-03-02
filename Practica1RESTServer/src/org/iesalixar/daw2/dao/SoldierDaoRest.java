package org.iesalixar.daw2.dao;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iesalixar.daw2.model.Firearm;
import org.iesalixar.daw2.model.Soldier;
import org.json.JSONObject;

@Path("/soldier")
public class SoldierDaoRest {

	public static Map<String, Soldier> soldierList = new HashMap<String, Soldier>();

	@GET
	@Path("/get-main-character/{option}")
	@Produces(MediaType.APPLICATION_JSON)
	public static Response getTheMainCharacter(@PathParam("option") String option) {

		Firearm firearm1 = new Firearm("AR-15", 11, "5,56mm");
		Soldier soldier1 = new Soldier("Connor", "Woods", firearm1);

		if (option.equalsIgnoreCase("firearm")) {
			return Response.ok(soldier1.getFirearm()).build();
		} else if (option.equalsIgnoreCase("soldier")) {
			return Response.ok(soldier1).build();
		} else {
			JSONObject json = new JSONObject();
			json.put("response", "Error, param should be either 'soldier' or 'firearm'");
			return Response.ok(json.toString()).build();
		}

	}

	@GET
	@Path("/get-all-soldier")
	@Produces(MediaType.APPLICATION_JSON)
	public static Response getAllSoldiers() {

		JSONObject json = new JSONObject();

		if (!SoldierDaoRest.soldierList.isEmpty()) {
			return Response.ok(soldierList).build();
		} else {
			json.put("response", "Database is empty");
		}

		return Response.ok(json.toString()).build();

	}

	@POST
	@Path("/post-soldier")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response postSoldier(Soldier soldier) {
		
		System.out.println("Peticion POST recibida correctamente, para el objeto: " + soldier);

		if (soldier.isComplete()) {
			SoldierDaoRest.soldierList.put(soldier.getName(), soldier);
			return Response.ok(soldier).build();
		} else {
			JSONObject json = new JSONObject();
			json.put("response", "Error, soldier is incomplete or invalid");

			return Response.ok(json.toString()).build();
		}

	}

	@PATCH
	@Path("/patch-soldier")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response patchSoldier(Soldier soldier) {
		
		JSONObject json = new JSONObject();

		for (Map.Entry<String, Soldier> entry : SoldierDaoRest.soldierList.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(soldier.getName())) {

				if (!soldier.getSurname().isEmpty()) {
					entry.getValue().setSurname(soldier.getSurname());
				}

				if (soldier.getFirearm().getSerialNum() != 0 && !soldier.getFirearm().getName().isEmpty()
						&& !soldier.getFirearm().getCal().isEmpty()) {
					entry.getValue().setFirearm(soldier.getFirearm());
				}
				
				return Response.ok(entry.getValue()).build();
			}
		}
		
		if(json.length() == 0) {
			json.put("response", "Could not find the specified soldier");
		}
		
		return Response.ok(json.toString()).build();

	}

	@PUT
	@Path("/put-soldier")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response putSoldier(Soldier soldier) {

		JSONObject json = new JSONObject();
		boolean found = false;
		Soldier resultSoldier = new Soldier();

		for (Map.Entry<String, Soldier> entry : SoldierDaoRest.soldierList.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(soldier.getName())) {
				found = true;

				entry.setValue(soldier);
				
				resultSoldier = entry.getValue();
			}
		}
		
		if(found) {
			return Response.ok(resultSoldier).build();
		} else {
			json.put("response", "Could not find the specified soldier");
			return Response.ok(json.toString()).build();
		}

	}
	
	@DELETE
	@Path("/delete-soldier")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response deleteSoldier(Soldier soldier) {

		JSONObject json = new JSONObject();
		
		if(SoldierDaoRest.soldierList.containsKey(soldier.getName())) {
			Soldier responseSoldier = SoldierDaoRest.soldierList.get(soldier.getName());
			
			SoldierDaoRest.soldierList.remove(soldier.getName());
			
			return Response.ok(responseSoldier).build();
		} else {
			json.put("response", "Could not find the specified soldier");
			return Response.ok(json.toString()).build();
		}

	}

}
