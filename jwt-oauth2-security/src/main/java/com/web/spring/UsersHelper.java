package com.web.spring;

import org.springframework.security.core.userdetails.User;

public class UsersHelper extends User{
	
	private static final Long serialversionUID = 1L;
	
	public UsersHelper(UsersPojo user) {
		super(
				user.getUsername(),
				user.getPassword(),
				user.getListofgrantedauthorities()
				);
	}
	
}
