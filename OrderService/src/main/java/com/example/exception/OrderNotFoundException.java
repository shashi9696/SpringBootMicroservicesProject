package com.example.exception;

public class OrderNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -292943446832569440L;

	public OrderNotFoundException() {
		super("resource not found on server !!");
	}
	
	public OrderNotFoundException(String message) {
		super(message);
	}

}
