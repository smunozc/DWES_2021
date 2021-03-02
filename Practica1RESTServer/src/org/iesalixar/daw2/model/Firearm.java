package org.iesalixar.daw2.model;

public class Firearm {
	
	private String name;
	private int serialNum;
	private String cal;
	
	public Firearm(String name, int serialNum, String cal) {
		super();
		this.name = name;
		this.serialNum = serialNum;
		this.cal = cal;
	}
	
	public Firearm() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}

	public String getCal() {
		return cal;
	}

	public void setCal(String cal) {
		this.cal = cal;
	}

}
