package model;

import java.io.Serializable;

public class User implements Serializable {

	// ATRIBUTES
	private static final long serialVersionUID = -5179837179138299120L;
	private String username;
	private String password;
	private String fullname;
	private String email;
	private String role;
	private int userState;

	// METHODS
	public User(String username, String password, String fullname, String email, String role, int userState) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.role = role;
		this.userState = userState;
	}
	
	public User(String username, String password, String fullname, String email) {
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.email = email;
		this.role = "notifier";
		this.userState = 1;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getUserState() {
		return userState;
	}

	public void setUserState(int userState) {
		this.userState = userState;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", type=" + role + ", name=" + fullname
				+ ", email=" + email + ", weight=" + "]";
	}

}
