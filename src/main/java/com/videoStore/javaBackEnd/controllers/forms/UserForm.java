package com.videoStore.javaBackEnd.controllers.forms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.videoStore.javaBackEnd.models.User;

import lombok.Data;

@Data
public class UserForm {
	@NotNull
	@NotEmpty
	private String name;
	@NotNull
	@NotEmpty
	private String email;
	@NotNull
	@NotEmpty
	private String password;

	public User convert() {
		return new User(name, email, password);
	}

}
