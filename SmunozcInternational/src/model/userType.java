package model;

public enum userType {

	REGULAR("regular"),
    ADMIN("admin");

    private final String value;

    userType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
