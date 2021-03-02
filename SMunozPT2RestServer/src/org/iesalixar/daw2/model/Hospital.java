package org.iesalixar.daw2.model;

import java.util.HashSet;
import java.util.Set;

public class Hospital {
	
	private int hospital_id;
	private String name;
	private String address;
	private Set<Surgeon> surgeons;
	private Set<OperatingRoom> operatingrooms;
	
	public Hospital() {
		super();
		this.surgeons = new HashSet<Surgeon>();
		this.operatingrooms = new HashSet<OperatingRoom>();
	}

	public Hospital(String name, String address, Set<Surgeon> surgeons, Set<OperatingRoom> operatingrooms) {
		super();
		this.name = name;
		this.address = address;
		this.surgeons = surgeons;
		this.operatingrooms = operatingrooms;
	}

	public int getHospital_id() {
		return hospital_id;
	}

	public void setHospital_id(int hospital_id) {
		this.hospital_id = hospital_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Surgeon> getSurgeons() {
		return surgeons;
	}

	public void setSurgeons(Set<Surgeon> surgeons) {
		this.surgeons = surgeons;
	}

	public Set<OperatingRoom> getOperatingRooms() {
		return operatingrooms;
	}

	public void setOperatingRooms(Set<OperatingRoom> operatingrooms) {
		this.operatingrooms = operatingrooms;
	}
}
