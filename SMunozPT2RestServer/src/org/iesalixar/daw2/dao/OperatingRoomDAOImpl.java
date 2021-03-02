package org.iesalixar.daw2.dao;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iesalixar.daw2.helper.MemoryData;
import org.iesalixar.daw2.model.Hospital;
import org.iesalixar.daw2.model.OperatingRoom;
import org.json.JSONObject;

@Path("/operatingroom")
public class OperatingRoomDAOImpl{
	
	public static Set<OperatingRoom> getAllOperatingRooms() {
		if(!MemoryData.isLoaded)
			MemoryData.load();
		return MemoryData.list_operatingrooms;
	}
	
	public static Set<OperatingRoom> getAllOperatingRoomsByHospitalName(String hospitalName){
		if(!MemoryData.isLoaded)
			MemoryData.load();
		Set<OperatingRoom> result = new HashSet<OperatingRoom>();
		for(OperatingRoom room:MemoryData.list_operatingrooms) {
			if(room.getHospital().getName().equals(hospitalName))
				result.add(room);
		}
		return result;
	}
	
	public static void createOperatingRoom(OperatingRoom room) {}
	public static void setHospital(OperatingRoom room,Hospital hospital) {}
	public static void remove(OperatingRoom room) {}
	
	
	/**
	 * RestServer4: eliminar objetos de la clase OperatingRoom
	 * @param operatingRoom
	 */
	@DELETE
	@Path("/delete-operatingroom")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response removeOperatingRoom(OperatingRoom operatingRoom) {
		JSONObject result = new JSONObject();
		
		if(!MemoryData.isLoaded)
			MemoryData.load();
		
		OperatingRoom systemOperatingRoom = null;
		
		for (OperatingRoom o : MemoryData.list_operatingrooms) {
			if(o.getName().equals(operatingRoom.getName())) {
				systemOperatingRoom = o;
				break;
			}
		}
		if(systemOperatingRoom!=null) {
			Hospital hosp = null;
			
			for (Hospital hospital : MemoryData.list_hospitals) {
				if(hospital.getName().equals(systemOperatingRoom.getHospital().getName())) {
					hosp = hospital;
					break;
				}
			}
			if(hosp!=null) {
				Set<OperatingRoom> ops = new HashSet<OperatingRoom>();
				for (OperatingRoom operating : hosp.getOperatingRooms()) {
					if(!operating.getName().equals(operatingRoom.getName()))
						ops.add(operating);
				}
				hosp.setOperatingRooms(ops);
			}
			MemoryData.list_operatingrooms.remove(systemOperatingRoom);
			result.put("status", "done!");
			result.put("operation result", hosp);
			System.out.println(result);
		}else 
			result.put("status", "nothing was done");
			System.out.println(result);
		
		return Response.ok(result.toString()).build();
	}
}
