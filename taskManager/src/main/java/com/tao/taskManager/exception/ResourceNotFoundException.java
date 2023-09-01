package com.tao.taskManager.exception;

import lombok.Data;

public class ResourceNotFoundException extends RuntimeException{
	
	private String message;
	private String status;
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException(String message, String status) {
		super(message);
		this.status = status;
	}

}
