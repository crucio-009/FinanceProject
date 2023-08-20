package com.java.financeprojectapp.entities;

public class UserCredentials {
	
	private String username;
	private String password;
	private int role;
	
	public UserCredentials() {
		super();
	}

	public UserCredentials(String username, String password, int role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null)
			return false;

		if (this == arg0)
			return true;

		if (!(arg0 instanceof UserCredentials))
			return false;

		UserCredentials other = (UserCredentials) arg0;
		if (this.username != other.username)
			return false;

		return true;
	}
	
}