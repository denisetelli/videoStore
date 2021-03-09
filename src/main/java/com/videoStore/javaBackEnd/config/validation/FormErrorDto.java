package com.videoStore.javaBackEnd.config.validation;

public class FormErrorDto {
	private String error;
	private String message;

	public FormErrorDto(String error, String message) {
		super();
		this.setError(error);
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
