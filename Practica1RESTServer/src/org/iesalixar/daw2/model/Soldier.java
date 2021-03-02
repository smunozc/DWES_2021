package org.iesalixar.daw2.model;

public class Soldier {

	private String name;
	private String surname;
	private Firearm firearm;

	public Soldier(String name, String surname, Firearm firearm) {
		super();
		this.name = name;
		this.surname = surname;
		this.firearm = firearm;
	}
	
	public Soldier() {
		super();
		this.firearm = new Firearm();
	}

	public boolean isComplete() {
		boolean isComplete = true;

		if (this.name.isEmpty() || this.surname.isEmpty() || this.firearm == null) {
			isComplete = false;
		}

		return isComplete;
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

	public Firearm getFirearm() {
		return firearm;
	}

	public void setFirearm(Firearm firearm) {
		this.firearm = firearm;
	}

}
