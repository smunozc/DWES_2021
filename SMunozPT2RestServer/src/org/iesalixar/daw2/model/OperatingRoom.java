package org.iesalixar.daw2.model;

public class OperatingRoom {
	
	private int operatingroom_id;
	private String name;
	private String floor;
	private Hospital hospital;
	
	public OperatingRoom() {
		super();
	}

	public OperatingRoom(String name, String floor, Hospital hospital) {
		super();
		this.name = name;
		this.floor = floor;
		this.hospital = hospital;
	}

	public int getOperatingroom_id() {
		return operatingroom_id;
	}

	public void setOperatingroom_id(int operatingroom_id) {
		this.operatingroom_id = operatingroom_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
}
