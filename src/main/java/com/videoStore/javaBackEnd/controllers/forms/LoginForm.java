package com.videoStore.javaBackEnd.controllers.forms;

import javax.validation.Valid;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	private String email;
	private String password;

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UsernamePasswordAuthenticationToken convert(@Valid LoginForm form) {
		return new UsernamePasswordAuthenticationToken(email, password);
	}

}
