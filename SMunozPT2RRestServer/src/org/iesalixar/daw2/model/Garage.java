package org.iesalixar.daw2.model;

public class Garage {
	
	private int garage_id;
	private String name;
	private String slot;
	private Dealer dealer;
	
	public Garage() {
		super();
	}

	public Garage(String name, String slot, Dealer dealer) {
		super();
		this.name = name;
		this.slot = slot;
		this.dealer = dealer;
	}

	public int getGarage_id() {
		return garage_id;
	}

	public void setGarage_id(int garage_id) {
		this.garage_id = garage_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

}
