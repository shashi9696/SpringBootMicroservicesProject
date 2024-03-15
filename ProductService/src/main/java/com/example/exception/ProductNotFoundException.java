package com.example.exception;

public class ProductNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -960245350737320976L;

	/*public ProductNotFoundException(String s) {
		super(s);
	}
	
	public ProductNotFoundException() {
		super("Resource not found");
	}*/
	
	public ProductNotFoundException() {
		super("resource not found on server !!");
	}
	
	public ProductNotFoundException(String message) {
		super(message);
	}

}
