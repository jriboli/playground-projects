package com.promineotech.person.dao;

public class MemDbException extends RuntimeException {

	public MemDbException() {
		
	}
	
	public MemDbException(String message) {
		super(message);
	}
	
	public MemDbException(String message, Exception e) {
		super(message, e);
	}
	
	public MemDbException(Exception e) {
		super(e);
	}
}
