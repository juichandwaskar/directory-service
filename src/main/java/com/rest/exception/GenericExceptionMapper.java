package com.rest.exception;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
 
	public Response toResponse(Throwable ex) {
		
		ErrorMessage errorMessage = new ErrorMessage();		
		setHttpStatus(ex, errorMessage);
		errorMessage.setMessage(ex.getMessage());
		StringWriter errorStackTrace = new StringWriter();
		ex.printStackTrace(new PrintWriter(errorStackTrace));
	
				
		return Response.status(errorMessage.getStatus())
				.entity(errorMessage)
				.type(MediaType.APPLICATION_JSON)
				.build();	
	}

	private void setHttpStatus(Throwable ex, ErrorMessage errorMessage) {
		if(ex instanceof WebApplicationException ) {
			int code = ((WebApplicationException)ex).getResponse().getStatus();
			errorMessage.setCode(code);
			errorMessage.setStatus(Status.fromStatusCode(code));
		} else {
			errorMessage.setStatus(Response.Status.INTERNAL_SERVER_ERROR);
			errorMessage.setCode(Status.INTERNAL_SERVER_ERROR.getStatusCode());
		}
	}
}