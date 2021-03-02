package org.iesalixar.daw2.dao;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iesalixar.daw2.helper.MemoryData;
import org.iesalixar.daw2.model.Hospital;
import org.iesalixar.daw2.model.OperatingRoom;
import org.iesalixar.daw2.model.Surgeon;
import org.json.JSONObject;

@Path("/surgeon")
public class SurgeonDAOImpl{
	
	public static Set<Surgeon> getSurgeonsWithEqualOrGreaterNumberOfHospitals(String number) {
		if(!MemoryData.isLoaded)
			MemoryData.load();
		
		Set<Surgeon> result = new HashSet<Surgeon>();
		for(Surgeon surgeon:MemoryData.list_surgeons) {
			if(surgeon.getHospitals().size()>(Integer.parseInt(number)-1))
				result.add(surgeon);
		}
		return result;
	}
	
	public static Set<Surgeon> getSurgeonsByOperatingRoomFloorByHospitalName(String operatingRoomFloor,String hospitalName){
		if(!MemoryData.isLoaded)
			MemoryData.load();
		
		Set<Surgeon> result = new HashSet<Surgeon>();
		
		for(Surgeon surgeon:MemoryData.list_surgeons) {
			for(Hospital hospital:surgeon.getHospitals()) {
				if(hospital.getName().equals(hospitalName)) {
					for(OperatingRoom room : hospital.getOperatingRooms()) {
						if(room.getFloor().equals(operatingRoomFloor)) {
							result.add(surgeon);
							break;
						}
							
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * RestServer2: crear objetos de la clase Surgeon
	 * @param surgeon
	 */
	@POST
	@Path("/add-surgeon")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response createSurgeon(Surgeon surgeon) {
		
		System.out.println(surgeon);
		
		if(!MemoryData.isLoaded)
			MemoryData.load();
		Set<Hospital> hospitals = MemoryData.list_hospitals;
		Set<Hospital> hospitals2 = surgeon.getHospitals();
		Set<Hospital> hospitals3 = new HashSet<Hospital>();
		for (Hospital hospital : hospitals2) {
			for (Hospital hospital2 : hospitals) {
				if(hospital.getHospital_id()==hospital2.getHospital_id()) {
					hospitals3.add(hospital2);
					break;
				}				
			}
		}
		surgeon.setHospitals(hospitals3);	
		Set<Surgeon> tmp = null;
		Set<Surgeon> surgeons = MemoryData.list_surgeons;
		surgeon.setSurgeon_id(surgeons.size()+1);
		surgeons.add(surgeon);
		for (Hospital h : surgeon.getHospitals()) {
			for (Hospital hospital2 : hospitals) {
				if(h.getName().equals(hospital2.getName())) {
					if(hospital2.getSurgeons()!=null)
						tmp = hospital2.getSurgeons();
					else
						tmp = new HashSet<Surgeon>();
					tmp.add(surgeon);
					break;
				}
				
			}
		}
		JSONObject json = new JSONObject();
		json.put("status", surgeon.getName() + " " + surgeon.getSurname());
		
		return Response.ok(json.toString()).build();
	}
	
	/**
	 * RestServer3: modificar objetos de la clase Surgeon
	 * @param surgeon
	 */
	@PATCH
	@Path("/patch-surgeon")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Response updateSurgeonDetails(Surgeon surgeon) {
		JSONObject result = new JSONObject();
		
		if(!MemoryData.isLoaded)
			MemoryData.load();
		
		Surgeon systemSurgeon = null;
		
		for (Surgeon s : MemoryData.list_surgeons) {
			if(s.getName().equals(surgeon.getName()))
				systemSurgeon = s;
		}
		
		if(systemSurgeon!=null) {
			if(!systemSurgeon.getSurname().equals(surgeon.getSurname()))
				systemSurgeon.setSurname(surgeon.getSurname());
			if(!systemSurgeon.getLicense().equals(surgeon.getLicense()))
				systemSurgeon.setLicense(surgeon.getLicense());
			result.put("status", "done!");
			System.out.println(result);
		}else
			result.put("status", "nothing changed");
			System.out.println(result);
		
		return Response.ok(result.toString()).build();
	}
	
}
