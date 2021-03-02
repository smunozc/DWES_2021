package org.iesalixar.daw2.main;

import java.util.HashSet;
import java.util.Set;

import org.iesalixar.daw2.model.Hospital;
import org.iesalixar.daw2.model.OperatingRoom;
import org.iesalixar.daw2.model.Surgeon;
import org.iesalixar.daw2.rest.RestClient1;
import org.iesalixar.daw2.rest.RestClient2;
import org.iesalixar.daw2.rest.RestClient3;
import org.iesalixar.daw2.rest.RestClient4;

public class main {

	public static void main(String[] args) {
		
		// REST CLIENT 1
		
//		RestClient1.getHospitalByNameBySurgeon("Hospital Universitario Virgen del Rocío", "ES-23443");
		
		// REST CLIENT 2
		Set<Surgeon> surgeons = null;
		Set<OperatingRoom> opreatingRooms = null;
		
		Hospital hospital = new Hospital();
		hospital.setAddress("ebtbb");
		hospital.setName("erbeber");
		hospital.setHospital_id(123);
		hospital.setSurgeons(surgeons);
		hospital.setOperatingRooms(opreatingRooms);
		
		Set<Hospital> hospitals = new HashSet<Hospital>();
		hospitals.add(hospital);
		
		Surgeon surgeon = new Surgeon();
		surgeon.setName("Elena");
		surgeon.setSurname("apellido5");
		surgeon.setLicense("license123");
		surgeon.setHospitals(hospitals);
		
		RestClient2.addSurgeon(surgeon);		
		
		// REST CLIENT 3
		
//		Surgeon surgeon = new Surgeon();
//		surgeon.setName("Elena");
//		surgeon.setSurname("apellido5");
//		surgeon.setLicense("license123");
//		
//		RestClient3.patchSurgeon(surgeon);
//		RestClient1.getHospitalByNameBySurgeon("Hospital Universitario Virgen del Rocío", "wakiwaki");
		
		// REST CLIENT 4
		
		OperatingRoom oproom = new OperatingRoom();
		oproom.setName("Luis Ley");
		RestClient4.deleteOperatingroom(oproom);

	}

}
