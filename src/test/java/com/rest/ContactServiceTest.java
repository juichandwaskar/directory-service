package com.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rest.Contacts;
import com.rest.Main;

import static org.junit.Assert.assertEquals;

public class ContactServiceTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
       
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "admin");
        
        final Client client = ClientBuilder.newClient();
        client.register(feature);
        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = client.target(Main.BASE_URI+Main.API_VERSION);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    @Test
    public void testContactsConstructor() {
        Contacts contact = new Contacts("bob", "789635482", "bob@example.com");
        assertEquals("Contacts [name=bob, phoneNumber=789635482, email=bob@example.com]",contact.toString());
    }
   
    @Test
    public void testPostRequest() {
        String jsonBody = "{\"name\":\"joe\",\"phoneNumber\":\"12342\",\"email\":\"joe@example.com\"}";
        Entity<String> contactPostEntity =Entity.entity(jsonBody, MediaType.APPLICATION_JSON_TYPE);
        Response response = target.path("/contacts").request().post(contactPostEntity); 
        assertEquals(Status.CREATED.getStatusCode(),response.getStatus());
    }
    
    @Test
    public void testPostRequestWithNoName() {
        String jsonInvalidBody = "{\"phoneNumber\":\"928471\",\"email\":\"joe.sample.com\"}";
        Entity<String> contactEntity =Entity.entity(jsonInvalidBody, MediaType.APPLICATION_JSON_TYPE);
        Response response = target.path("/contacts").request().post(contactEntity); 
        assertEquals(Status.BAD_REQUEST.getStatusCode(),response.getStatus());
    }
    
    @Test
    public void testPostRequestWithExistingName() {
        String jsonBody = "{\"name\":\"joe\",\"phoneNumber\":\"12342\",\"email\":\"joe@example.com\"}";
        Entity<String> contactEntity =Entity.entity(jsonBody, MediaType.APPLICATION_JSON_TYPE);
        Response response = target.path("/contacts").request().post(contactEntity);
        assertEquals(Status.CONFLICT.getStatusCode(),response.getStatus());
    }
    
    @Test
    public void testPostRequestWithInvalidBody() {
    	String jsonBody = "";
    	Entity<String> contactEntity =Entity.entity(jsonBody, MediaType.APPLICATION_JSON_TYPE);
        Response response = target.path("/contacts").request().post(contactEntity);
        assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(),response.getStatus());
    }
    
    @Test
    public void testPostRequestWithInvalidMediaType() {
        String jsonBody = "{\"name\":\"joe\",\"phoneNumber\":\"12342\",\"email\":\"joe@example.com\"}";
    	Entity<String> contactEntity =Entity.entity(jsonBody, MediaType.TEXT_PLAIN);
        Response response = target.path("/contacts").request().post(contactEntity);
        assertEquals(Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode(),response.getStatus());
    }
    
    @Test
    public void testGetAllContacts() {
        Response response= target.path("/contacts").request().get();
        assertEquals(Status.OK.getStatusCode(),response.getStatus());
    }
    
    @Test
    public void testGetContactDetails() {
    	Response response= target.path("/contacts/joe").request().get();
        assertEquals(Status.OK.getStatusCode(),response.getStatus());
    }
    
    @Test
    public void testGetContactDetailsWithInvalidContactName() {
    	Response response= target.path("/contacts/abc").request().get();
        assertEquals(Status.NOT_FOUND.getStatusCode(),response.getStatus());
    }
    
    @Test
    public void testUpdateContactDetails() {
    	String jsonBody = "{\"phoneNumber\":\"92847142671\",\"email\":\"joe.example.com\"}";

        Entity<String> contactEntity =Entity.entity(jsonBody, MediaType.APPLICATION_JSON_TYPE);
        Response response = target.path("/contacts/joe").request().put(contactEntity); 
        assertEquals(Status.OK.getStatusCode(),response.getStatus());
    }
    
    @Test
    public void testUpdateOnlyPhoneNumberOfContact() {
    	String jsonBody = "{\"phoneNumber\":\"12345\"}";
    	Entity<String> contactEntity =Entity.entity(jsonBody, MediaType.APPLICATION_JSON_TYPE);
        Response response = target.path("/contacts/joe").request().put(contactEntity); 
        assertEquals(Status.OK.getStatusCode(),response.getStatus());
    }
    
    @Test
    public void testUpdateOnlyEmailOfContact() {
    	String jsonBody= "{\"email\":\"testjoe.example.com\"}";
    	Entity<String> contactEntity =Entity.entity(jsonBody, MediaType.APPLICATION_JSON_TYPE);
        Response response = target.path("/contacts/joe").request().put(contactEntity); 
        assertEquals(Status.OK.getStatusCode(),response.getStatus());
    }
    
    @Test
    public void testUpdateContactDetailsWithInvalidContactName() {
    	String jsonBody = "{\"phoneNumber\":\"92847142671\",\"email\":\"joe.example.com\"}";
        Entity<String> contactEntity =Entity.entity(jsonBody, MediaType.APPLICATION_JSON_TYPE);
        Response response = target.path("/contacts/abc").request().put(contactEntity); 
        assertEquals(Status.NOT_FOUND.getStatusCode(),response.getStatus());
    }
    
    @Test
    public void testDeleteContact() {
    	Response response= target.path("/contacts/joe").request().delete();
        assertEquals(Status.OK.getStatusCode(),response.getStatus());
    }
    
    @Test
    public void testDeleteContactWithInvalidName() {
    	Response response= target.path("/contacts/abc").request().delete();
        assertEquals(Status.NOT_FOUND.getStatusCode(),response.getStatus());
    }
  
    
}
