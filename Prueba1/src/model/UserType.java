package model;

public enum UserType {
	
	REGULAR("regular"),
	ADMIN("admin");
	
	private final String value;
	
	UserType(String value){
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}

}
