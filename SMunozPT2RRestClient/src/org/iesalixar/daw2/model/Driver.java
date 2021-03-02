package org.iesalixar.daw2.model;

import java.util.HashSet;
import java.util.Set;

public class Driver {
	
	private int driver_id;
	private String name;
	private String surname;
	private String license;
	private Set<Dealer> dealers;
	
	public Driver() {
		super();
		this.dealers = new HashSet<Dealer>();
	}

	public Driver(String name, String surname, String license, Set<Dealer> dealers) {
		super();
		this.name = name;
		this.surname = surname;
		this.license = license;
		this.dealers = dealers;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
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

	public Set<Dealer> getDealers() {
		return dealers;
	}

	public void setDealers(Set<Dealer> dealers) {
		this.dealers = dealers;
	}

}
