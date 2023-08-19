package com.java.financeprojectapp.entities;

import java.util.Set;

import com.java.financeprojectapp.servicelayer.entities.Role;

public class User {

	private String username;
	private Set<Role> roles;
	
	public User(String username, Set<Role> roles) {
		super();
		this.setUsername(username);
		this.setRoles(roles);
	}

	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
