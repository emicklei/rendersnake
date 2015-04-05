package com.company.service.impl;

import org.springframework.stereotype.Service;

import com.company.service.LoginService;

@Service
public class StdLoginService implements LoginService {

	/*
	 * username: admin
	 * password: admin
	 */
	@Override
	public boolean isAuthenticated(String username, String password) {
		if ("admin".equals(username) && "admin".equals(password)) return true;
		return false;
	}

}
