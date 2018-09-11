package com.rest.exception;

import javax.ws.rs.core.Response.Status;


/**
 * Class to map application related exceptions
 */
public class AppException extends Exception {


	private static final long serialVersionUID = 1L;
	Status status;
	int code; 

	/**
	 * @param status
	 * @param status code
	 * @param message
	 */
	public AppException(Status status, int code, String message) {
		super(message);
		this.status = status;
		this.code = code;
	}
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * @param status
	 * the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	/**
	 * @return the status code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param status code 
	 * the status code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}


}