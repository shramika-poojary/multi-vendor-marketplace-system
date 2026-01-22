package com.data.exception;

import org.springframework.stereotype.Component;

@Component
public class InvalidCredentialsException extends RuntimeException {

	String message;

	public InvalidCredentialsException(String message) {
		super();
		this.message = message;
	}

	public InvalidCredentialsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "InvalidCredentialsException [message=" + message + "]";
	}

	
	
}
