package com.rest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.rest.authentication.AuthenticationFilter;
import com.rest.exception.AppExceptionMapper;
import com.rest.exception.GenericExceptionMapper;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {
	// Base URI the Grizzly HTTP server will listen on
	public static final String BASE_URI = "http://localhost:8080/";
	// Directory service API version
	public static final String API_VERSION = "v1";

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
	 * @return 
	 */
	public static HttpServer startServer() {
		// create a resource config that scans for JAX-RS resources and providers
		// in com.smartbear package
		final ResourceConfig rc = new ResourceConfig().packages("com.rest");
		rc.register(AppExceptionMapper.class);
		rc.register(GenericExceptionMapper.class);
		rc.register(AuthenticationFilter.class);
		// create and start a new instance of grizzly http server
		// exposing the Jersey application at BASE_URI
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI+API_VERSION), rc);
	}

	/**
	 * Main method.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		final HttpServer server = startServer();
		System.out.println(String.format("Contact directory service app started at "
				+ "%s\nHit enter to stop it...", BASE_URI+API_VERSION));
		System.in.read();
		server.shutdownNow();
	}
}

