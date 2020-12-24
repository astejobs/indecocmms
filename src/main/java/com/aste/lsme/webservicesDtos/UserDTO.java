package com.aste.lsme.webservicesDtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTO {

	long id;
	
	String username;
	@JsonIgnore
	String firstName;
	@JsonIgnore
	String lastName;
	
	String deviceToken;
	
	
	
	

	public UserDTO() {
		super();
	}
	public UserDTO(long id, String username, String firstName, String lastName) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public UserDTO(long id, String username, String firstName, String lastName,String deviceToken) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.deviceToken=deviceToken;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getName(){
		return firstName+" "+lastName;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public UserDTO(long id, String deviceToken) {
		super();
		this.id = id;
		this.deviceToken = deviceToken;
	}
	
	
	
}
