package org.iesalixar.daw2.model;

import java.util.HashSet;
import java.util.Set;

public class Dealer {
	
	private int dealer_id;
	private String name;
	private String address;
	private Set<Driver> drivers;
	private Set<Garage> garages;
	
	public Dealer() {
		super();
		this.drivers = new HashSet<Driver>();
		this.garages = new HashSet<Garage>();
	}

	public Dealer(String name, String address, Set<Driver> drivers, Set<Garage> operatingrooms) {
		super();
		this.name = name;
		this.address = address;
		this.drivers = drivers;
		this.garages = operatingrooms;
	}

	public int getDealer_id() {
		return dealer_id;
	}

	public void setDealer_id(int dealer_id) {
		this.dealer_id = dealer_id;
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

	public Set<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(Set<Driver> drivers) {
		this.drivers = drivers;
	}

	public Set<Garage> getGarages() {
		return garages;
	}

	public void setGarages(Set<Garage> garages) {
		this.garages = garages;
	}
	
}
