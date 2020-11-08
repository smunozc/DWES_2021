package com.smunozc.SmunozcLogin.model;

public class User {

	// ATRIBUTES
	private String username;
	private String password;
	private String name;
	private String surname;
	private String email;
	private String birthday;
	// DetailedData
	private String nif;
	private String weight;
	private String height;
	private String academicFormation;
	private String hobbies;

	// METHODS
	public User(String username, String password, String name, String surname, String email, String birthday) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.birthday = birthday;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * Detailed Data NIF Getter
	 * 
	 * @return String
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * Detailed Data NIF Setter
	 * 
	 * @param nif
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}

	/**
	 * Detailed Data Weight Getter
	 * 
	 * @return String
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * Detailed Data Weight Setter
	 * 
	 * @param weight
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * Detailed Data Height Getter
	 * 
	 * @return String
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * Detailed Data Height Setter
	 * 
	 * @param height
	 */
	public void setHeight(String height) {
		this.height = height;
	}

	/**
	 * Detailed Data Academic Formation Getter
	 * 
	 * @return String
	 */
	public String getAcademicFormation() {
		return academicFormation;
	}

	/**
	 * Detailed Data Academic Formation Setter
	 * 
	 * @param academicFormation
	 */
	public void setAcademicFormation(String academicFormation) {
		this.academicFormation = academicFormation;
	}

	/**
	 * Detailed Data Hobbies Getter
	 * 
	 * @return String
	 */
	public String getHobbies() {
		return hobbies;
	}

	/**
	 * Detailed Data Hobbies Setter
	 * 
	 * @param hobbies
	 */
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

}
