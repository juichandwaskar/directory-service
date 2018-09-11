package com.rest.authentication;


import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

/**
 * This filter verify the access permissions for a user
 * based on username and password provided in request
 * */
@Provider
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter
{

	// Authentication schema: Basic Auth
	private static final String AUTHENTICATION_SCHEME = "Basic";

	/**
	 * @param requestContext
	 * 
	 */  
	@Override
	public void filter(ContainerRequestContext requestContext)
	{
		Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
				.entity("You cannot access this resource").build();


		//Fetch authorization header
		final String authorization = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		//If no authorization information present; block access
		if(authorization == null || authorization.isEmpty())
		{
			requestContext.abortWith(ACCESS_DENIED);
			return;
		}     
		//Get encoded user name and password
		final String encodedUserPassword = authorization.replaceFirst(AUTHENTICATION_SCHEME + " ", "");

		//Decode user name and password
		String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));;

		//Split user name and password tokens
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		//Verifying User name and password           
		if( ! isUserAllowed(username, password))
		{

			requestContext.abortWith(ACCESS_DENIED);
			return;
		}


	} 
	/**
	 * Validate the username and password
	 * @param username
	 * @param password
	 * @return Boolean
	 */
	private boolean isUserAllowed(final String username, final String password)
	{
		boolean isAllowed = false;

		// User name and Password can be fetched from DB
		if(username.equals("admin") && password.equals("admin"))
		{
			isAllowed = true;

		}
		return isAllowed;
	}
}