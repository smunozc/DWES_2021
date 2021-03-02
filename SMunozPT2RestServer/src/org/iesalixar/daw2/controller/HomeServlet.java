package org.iesalixar.daw2.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.iesalixar.daw2.dao.SurgeonDAOImpl;
import org.iesalixar.daw2.helper.MemoryData;
import org.iesalixar.daw2.dao.HospitalDAOImpl;
import org.iesalixar.daw2.dao.OperatingRoomDAOImpl;
import org.iesalixar.daw2.model.Surgeon;
import org.iesalixar.daw2.model.OperatingRoom;
import org.iesalixar.daw2.model.Hospital;;


public class HomeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 7698387569340826896L;
	
	public static Surgeon 			surgeon1,surgeon2,surgeon3;
	public static Hospital 			hospital1,hospital2,hospital3;
	public static OperatingRoom 	operatingroom1,operatingroom2,operatingroom3,
									operatingroom4,operatingroom5,operatingroom6;
	
	public static Set<Hospital> 	list_hospitals;
	public static Set<Surgeon>  	list_surgeons;
	public static Set<OperatingRoom>list_operatingrooms;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		if(!MemoryData.isLoaded)
			MemoryData.load();
		
		//add a new surgeon
		/*
		Surgeon surgeonTmp = new Surgeon();
		surgeonTmp.setSurgeon_id(MemoryData.list_surgeons.size()+1);
		surgeonTmp.setName("Surgeon name");
		surgeonTmp.setSurname("Surgeon surname");
		surgeonTmp.setLicense("Surgeon license");
		Set<Hospital> hospitalsTmp = new HashSet<Hospital>();
		hospitalsTmp.add(MemoryData.list_hospitals.stream().findFirst().get());
		surgeonTmp.setHospitals(hospitalsTmp);
		SurgeonDAOImpl.createSurgeon(surgeonTmp);*/
		
//		request.setAttribute("surgeonsWithEqualOrGreaterNumberOfHospitals",
//				SurgeonDAOImpl.getSurgeonsWithEqualOrGreaterNumberOfHospitals("2"));
		
//		request.setAttribute("hospitalsWithEqualNumberOfSurgeonsAndOperatingRooms", 
//				HospitalDAOImpl.getHospitalsWithEqualNumberOfSurgeonsAndOperatingRooms());
		
		request.setAttribute("allOperatingRooms", OperatingRoomDAOImpl.getAllOperatingRooms());
		
//		request.setAttribute("surgeonsByOperatingRoomFloorByHospitalName",
//				SurgeonDAOImpl.getSurgeonsByOperatingRoomFloorByHospitalName(
//						"3",
//						"Hospital Universitario Vírgen Macarena"));
		
//		request.setAttribute("hospitalByNameBySurgeonLicense",
//				HospitalDAOImpl.getHospitalByNameBySurgeonLicense("Hospital de Valme","AR-03976"));
		
		request.setAttribute("getAllOperatingRoomsByHospitalName",
				OperatingRoomDAOImpl.getAllOperatingRoomsByHospitalName("Hospital Universitario Virgen del Rocío"));
		
		
		RequestDispatcher rd = getServletContext()
				.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void objectsInMemory() {
		
		surgeon1 		= new Surgeon();
		surgeon1.setName("Elena");
		surgeon1.setSurname("Barraquer");
		surgeon1.setLicense("ES-23443");
		surgeon2 		= new Surgeon();
		surgeon2.setName("Cecilia");
		surgeon2.setSurname("Grierson");
		surgeon2.setLicense("AR-03976");
		surgeon3 		= new Surgeon();
		surgeon3.setName("Florence");
		surgeon3.setSurname("Nightingale");
		surgeon3.setLicense("UK-30206");
	
		hospital1 		= new Hospital();
		hospital1.setName("Hospital Universitario Virgen del Rocío");
		hospital1.setAddress("Av. Manuel Siurot, S/n, 41013 Sevilla");
		hospital2 		= new Hospital();
		hospital2.setName("Hospital de Valme");
		hospital2.setAddress("41014 Sevilla");
		hospital3 		= new Hospital();
		hospital3.setName("Hospital Universitario Vírgen Macarena");
		hospital3.setAddress("Calle Dr. Fedriani, 3, 41009 Sevilla");
		
	
		operatingroom1 	= new OperatingRoom();
		operatingroom1.setName("Severo Ochoa");
		operatingroom1.setFloor("2");
		operatingroom1.setHospital(hospital1);
		
		operatingroom2 	= new OperatingRoom();
		operatingroom2.setName("Margarita Salas");
		operatingroom2.setFloor("3");
		operatingroom2.setHospital(hospital1);
		
		operatingroom3 	= new OperatingRoom();
		operatingroom3.setName("Ignacia Salvans i Casas");
		operatingroom3.setFloor("4");
		operatingroom3.setHospital(hospital1);
		
		operatingroom4 	= new OperatingRoom();
		operatingroom4.setName("Pablo Clavel");
		operatingroom4.setFloor("5");
		operatingroom4.setHospital(hospital2);
		
		operatingroom5 	= new OperatingRoom();
		operatingroom5.setName("Luis Ley");
		operatingroom5.setFloor("3");
		operatingroom5.setHospital(hospital3);
		
		operatingroom6 	= new OperatingRoom();
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
		list1_hospital.add(hospital1);
		list1_hospital.add(hospital3);
	
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
	}
}