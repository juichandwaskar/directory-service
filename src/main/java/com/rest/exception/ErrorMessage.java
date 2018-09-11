package com.rest.exception;

import java.lang.reflect.InvocationTargetException;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanUtils;


/**
 * Class to construct Error message for exceptions
 */
@XmlRootElement
public class ErrorMessage {


	/** contains the same HTTP Status code returned by the server */
	@XmlElement(name = "status")
	Status status;

	/** application specific error code */
	@XmlElement(name = "code")
	int code;

	/** message describing the error*/
	@XmlElement(name = "message")
	String message;
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
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message
	 * the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @param exception
	 * 
	 */
	public ErrorMessage(AppException ex){
		super();
		try {
			BeanUtils.copyProperties(this, ex);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public ErrorMessage() {
		super();
	}
}