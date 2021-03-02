package org.iesalixar.daw2.model;

import java.util.HashSet;
import java.util.Set;

public class Surgeon {
	
	private int surgeon_id;
	private String name;
	private String surname;
	private String license;
	private Set<Hospital> hospitals;
	
	public Surgeon() {
		super();
		this.hospitals = new HashSet<Hospital>();
	}

	public Surgeon(String name, String surname, String license, Set<Hospital> hospitals) {
		super();
		this.name = name;
		this.surname = surname;
		this.license = license;
		this.hospitals = hospitals;
	}

	public int getSurgeon_id() {
		return surgeon_id;
	}
	
	public void setSurgeon_id(int surgeon_id) {
		this.surgeon_id = surgeon_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public Set<Hospital> getHospitals() {
		return hospitals;
	}

	public void setHospitals(Set<Hospital> hospitals) {
		this.hospitals = hospitals;
	}
}
