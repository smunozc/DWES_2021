package org.iesalixar.daw2.model;

import java.io.Serializable;

public class Soldier implements Serializable {

	private static final long serialVersionUID = 6403660037970472700L;
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

	// El siguiente método comentado provoca que se cree un atributo extra en el
	// json: 'complete' que esta a true si devuelve true. No me interesa para el
	// cliente porque al mandarlo al servidor da un error de http 400 (Bad Request)
	// ya que no coinciden los atributos de la clase Soldier con los del json.

//	public boolean isComplete() {
//		boolean isComplete = true;
//
//		if (this.name.isEmpty() || this.surname.isEmpty() || this.firearm == null) {
//			isComplete = false;
//		}
//
//		return isComplete;
//	}

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
