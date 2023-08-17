package com.java.financeprojectapp.entities;

public class UserCredentials {

	private String userId;
	private String password;
	private int roleId;
	
	public UserCredentials() {
		super();
	}

	public UserCredentials(String userId, String password, int roleId) {
		super();
		this.userId = userId;
		this.password = password;
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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
		if (this.userId != other.userId)
			return false;

		return true;
	}

}
