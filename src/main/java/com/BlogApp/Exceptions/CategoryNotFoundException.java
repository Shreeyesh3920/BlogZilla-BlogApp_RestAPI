package com.BlogApp.Exceptions;

public class CategoryNotFoundException extends RuntimeException{

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CategoryNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	
}
