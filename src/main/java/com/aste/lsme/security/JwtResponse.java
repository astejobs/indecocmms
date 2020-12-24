package com.aste.lsme.security;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String token;
	private final String role;
	private final String username;
	
	public JwtResponse(String jwttoken,String role, String username) {
		this.token = jwttoken;
		this.role=role;
		this.username=username;
	}

	public String getToken() {
		return this.token;
	}

	public String getRole() {
		return role;
	}

	public String getUsername() {
		return username;
	}
	
	
	
}