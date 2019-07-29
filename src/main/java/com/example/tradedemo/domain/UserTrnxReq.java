package com.example.tradedemo.domain;

public class UserTrnxReq {
	
	private String user;
	private String role;
	
	
	
	
	public UserTrnxReq() {
		super();
	}
	
	
	
	public UserTrnxReq(String user, String role) {
		super();
		this.user = user;
		this.role = role;
	}



	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
