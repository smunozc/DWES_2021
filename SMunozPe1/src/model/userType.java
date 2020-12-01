package model;

public enum userType {

	NOTIFIER("notifier"),
	TRACKER("tracker"),
    ADMIN("admin");

    private final String value;

    userType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
