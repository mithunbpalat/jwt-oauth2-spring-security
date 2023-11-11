package com.web.spring;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UsersPojo {
	
	private String username;
	private String password;
	private Collection<GrantedAuthority> listofgrantedauthorities = new ArrayList<>();
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
	public Collection<GrantedAuthority> getListofgrantedauthorities() {
		return listofgrantedauthorities;
	}
	public void setListofgrantedauthorities(Collection<GrantedAuthority> listofgrantedauthorities) {
		this.listofgrantedauthorities = listofgrantedauthorities;
	}
	
	
}
