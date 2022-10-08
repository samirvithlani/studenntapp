package com.util;

public class JwtResponse {

	private String jwttoken;

	public String getJwttoken() {
		return jwttoken;
	}

	public void setJwttoken(String jwttoken) {
		this.jwttoken = jwttoken;

	}

	public JwtResponse(String jwttoken) {
		super();
		this.jwttoken = jwttoken;
	}
	
	
	
}
