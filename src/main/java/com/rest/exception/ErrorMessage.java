package com.rest.exception;

import java.lang.reflect.InvocationTargetException;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.beanutils.BeanUtils;




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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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