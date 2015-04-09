package com.company.ui.page.login;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Bind the request and validate data from the login form
 */
public class LoginBinder {
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;

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
}
