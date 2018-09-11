package com.rest.exception;

import javax.ws.rs.core.Response.Status;


/**
 * Class to map application related exceptions
 */

public class AppException extends Exception {
	
	
	private static final long serialVersionUID = 1L;
	Status status;
	int code; 
	
	
	public AppException(Status status, int code, String message) {
		super(message);
		this.status = status;
		this.code = code;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	
}