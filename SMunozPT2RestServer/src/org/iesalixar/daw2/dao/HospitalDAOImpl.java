package org.iesalixar.daw2.dao;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.iesalixar.daw2.helper.MemoryData;
import org.iesalixar.daw2.model.Hospital;
import org.iesalixar.daw2.model.Surgeon;
import org.json.JSONObject;

@Path("/hospital")
public class HospitalDAOImpl {

	public static Set<Hospital> getHospitalsWithEqualNumberOfSurgeonsAndOperatingRooms() {
		if (!MemoryData.isLoaded)
			MemoryData.load();

		Set<Hospital> result = new HashSet<Hospital>();

		for (Hospital hospital : MemoryData.list_hospitals) {
			if (hospital.getSurgeons().size() == hospital.getOperatingRooms().size())
				result.add(hospital);
		}
		return result;
	}

	public static void createHospital(Hospital hospital) {

	}

	public static void addSurgeon(Surgeon surgeon, Hospital hospital) {
	}

	/**
	 * RestServer1: Hospital getHospitalByNameBySurgeon(String hospitalName,String
	 * surgeonName)
	 * 
	 * @param surgeonLicense
	 * @param hospitalName
	 * @return Response
	 */
	@GET
	@Path("/get-hospital-by-name-by-surgeon/{surgeonLicense}/{hospitalName}")
	@Produces(MediaType.APPLICATION_JSON)
	public static Response getHospitalByNameBySurgeonLicense(@PathParam("hospitalName") String hospitalName,
			@PathParam("surgeonLicense") String surgeonLicense) {
		System.out.println(hospitalName + " | " + surgeonLicense);
		JSONObject json;
		
		if (!MemoryData.isLoaded)
			MemoryData.load();
		for (Hospital hospital : MemoryData.list_hospitals) {
			if (hospital.getName().equals(hospitalName)) {
				for (Surgeon surgeon : hospital.getSurgeons()) {
					if (surgeon.getLicense().equals(surgeonLicense)) {

						return Response.ok(hospital).build();
					}
				}
			}
		}
		json = new JSONObject();
		json.put("error", "not found");
		
		return Response.ok(json.toString()).build();
	}

}
