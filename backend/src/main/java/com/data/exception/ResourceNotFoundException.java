package com.data.exception;

import org.springframework.stereotype.Component;

import com.data.model.User;

@Component
public class ResourceNotFoundException extends RuntimeException {

	String message;

	public ResourceNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
	public ResourceNotFoundException() {
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
		return "ResourceNotFoundException [message=" + message + "]";
	}
	
	
	
}
