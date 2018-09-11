package com.rest.exception;

import java.util.logging.Logger;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Class to send response for application related exceptions
 */
@Provider
public class AppExceptionMapper implements ExceptionMapper<AppException> {

	private static final Logger LOGGER = Logger.getLogger(AppExceptionMapper.class.getName());

	/**
	 * @param exception
	 * 
	 */
	public Response toResponse(AppException ex) {
		LOGGER.info("In App exception mapper" + ex.toString());
		return Response.status(ex.getStatus())
				.entity(new ErrorMessage(ex))
				.type(MediaType.APPLICATION_JSON).
				build();
	}

}