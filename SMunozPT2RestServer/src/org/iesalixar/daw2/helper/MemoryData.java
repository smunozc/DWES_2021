package org.iesalixar.daw2.helper;

import java.util.HashSet;
import java.util.Set;

import org.iesalixar.daw2.model.Surgeon;
import org.iesalixar.daw2.model.Hospital;
import org.iesalixar.daw2.model.OperatingRoom;

public class MemoryData {
	
	public static Surgeon 			surgeon1,surgeon2,surgeon3;
	public static Hospital 			hospital1,hospital2,hospital3;
	public static OperatingRoom 	operatingroom1,operatingroom2,operatingroom3,operatingroom4,operatingroom5,operatingroom6;
	public static Set<Hospital> 	list_hospitals;
	public static Set<Surgeon>  	list_surgeons;
	public static Set<OperatingRoom>list_operatingrooms;
	public static boolean 			isLoaded = false;
	
	public static void load() {
		
		surgeon1 		= new Surgeon();
		surgeon1.setSurgeon_id(1);
		surgeon1.setName("Elena");
		surgeon1.setSurname("Barraquer");
		surgeon1.setLicense("ES-23443");
		surgeon2 		= new Surgeon();
		surgeon2.setSurgeon_id(2);
		surgeon2.setName("Cecilia");
		surgeon2.setSurname("Grierson");
		surgeon2.setLicense("AR-03976");
		surgeon3 		= new Surgeon();
		surgeon3.setSurgeon_id(3);
		surgeon3.setName("Florence");
		surgeon3.setSurname("Nightingale");
		surgeon3.setLicense("UK-30206");
	
		hospital1 		= new Hospital();
		hospital1.setHospital_id(1);
		hospital1.setName("Hospital Universitario Virgen del Rocío");
		hospital1.setAddress("Av. Manuel Siurot, S/n, 41013 Sevilla");
		hospital2 		= new Hospital();
		hospital2.setHospital_id(2);
		hospital2.setName("Hospital de Valme");
		hospital2.setAddress("41014 Sevilla");
		hospital3 		= new Hospital();
		hospital3.setHospital_id(3);
		hospital3.setName("Hospital Universitario Vírgen Macarena");
		hospital3.setAddress("Calle Dr. Fedriani, 3, 41009 Sevilla");
		
	
		operatingroom1 	= new OperatingRoom();
		operatingroom1.setOperatingroom_id(1);
		operatingroom1.setName("Severo Ochoa");
		operatingroom1.setFloor("2");
		operatingroom1.setHospital(hospital1);
		
		operatingroom2 	= new OperatingRoom();
		operatingroom2.setOperatingroom_id(2);
		operatingroom2.setName("Margarita Salas");
		operatingroom2.setFloor("3");
		operatingroom2.setHospital(hospital1);
		
		operatingroom3 	= new OperatingRoom();
		operatingroom3.setOperatingroom_id(3);
		operatingroom3.setName("Ignacia Salvans i Casas");
		operatingroom3.setFloor("4");
		operatingroom3.setHospital(hospital1);
		
		operatingroom4 	= new OperatingRoom();
		operatingroom4.setOperatingroom_id(4);
		operatingroom4.setName("Pablo Clavel");
		operatingroom4.setFloor("5");
		operatingroom4.setHospital(hospital2);
		
		operatingroom5 	= new OperatingRoom();
		operatingroom5.setOperatingroom_id(5);
		operatingroom5.setName("Luis Ley");
		operatingroom5.setFloor("3");
		operatingroom5.setHospital(hospital3);
		
		operatingroom6 	= new OperatingRoom();
		operatingroom6.setOperatingroom_id(6);
		operatingroom6.setName("Francisco Trujillo");
		operatingroom6.setFloor("4");
		operatingroom6.setHospital(hospital3);
		
		Set<Hospital> list1_hospital = new HashSet<Hospital>();		
		list1_hospital.add(hospital1);
		list1_hospital.add(hospital2);
		list1_hospital.add(hospital3);
		
		Set<Hospital> list2_hospital = new HashSet<Hospital>();
		list2_hospital.add(hospital2);
		
		Set<Hospital> list3_hospital = new HashSet<Hospital>();		
		list3_hospital.add(hospital1);
		list3_hospital.add(hospital3);
	
		surgeon1.setHospitals(list1_hospital);
		surgeon2.setHospitals(list2_hospital);
		surgeon3.setHospitals(list3_hospital);

		Set<Surgeon> list1_surgeon = new HashSet<Surgeon>();		
		list1_surgeon.add(surgeon1);
		list1_surgeon.add(surgeon3);
		
		Set<Surgeon> list2_surgeon = new HashSet<Surgeon>();		
		list2_surgeon.add(surgeon1);
		list2_surgeon.add(surgeon2);
		
		Set<Surgeon> list3_surgeon = new HashSet<Surgeon>();		
		list3_surgeon.add(surgeon1);
		list3_surgeon.add(surgeon3);
				
		Set<OperatingRoom> list1_operatingrooms = new HashSet<OperatingRoom>();
		list1_operatingrooms.add(operatingroom1);
		list1_operatingrooms.add(operatingroom2);
		list1_operatingrooms.add(operatingroom3);
		
		Set<OperatingRoom> list2_operatingrooms = new HashSet<OperatingRoom>();
		list2_operatingrooms.add(operatingroom4);
		
		Set<OperatingRoom> list3_operatingrooms = new HashSet<OperatingRoom>();
		list3_operatingrooms.add(operatingroom5);
		list3_operatingrooms.add(operatingroom6);
		
		hospital1.setSurgeons(list1_surgeon);
		hospital1.setOperatingRooms(list1_operatingrooms);
		
		hospital2.setSurgeons(list2_surgeon);
		hospital2.setOperatingRooms(list2_operatingrooms);
		
		hospital3.setSurgeons(list3_surgeon);
		hospital3.setOperatingRooms(list3_operatingrooms);
		
		list_hospitals = new HashSet<Hospital>();		
		list_hospitals.add(hospital1);
		list_hospitals.add(hospital2);
		list_hospitals.add(hospital3);
		
		list_surgeons = new HashSet<Surgeon>();		
		list_surgeons.add(surgeon1);
		list_surgeons.add(surgeon2);
		list_surgeons.add(surgeon3);
		
		list_operatingrooms = new HashSet<OperatingRoom>();
		list_operatingrooms.add(operatingroom1);
		list_operatingrooms.add(operatingroom2);
		list_operatingrooms.add(operatingroom3);
		list_operatingrooms.add(operatingroom4);
		list_operatingrooms.add(operatingroom5);
		list_operatingrooms.add(operatingroom6);
		
		isLoaded = true;
	}

}
