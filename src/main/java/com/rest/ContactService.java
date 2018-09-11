package com.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rest.exception.AppException;



/**
 * Root resource (exposed at "contacts" path)
 */
@Path("contacts")
public class ContactService {
	
	
	ContactsDoa contactsObj = new ContactsDoa();
	

	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    
	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
    public Response addNewContact(Contacts contact) throws AppException {
    	contactsObj.addNewContact(contact);
        return Response.status(Response.Status.CREATED)//201
        		.entity("Contact name: " + contact.getName() + " added to records").build();
    }
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContactNames() {
    	return Response.status(Response.Status.OK)//200
    			.entity(contactsObj.getAllContactNames()).build();
    }
    
    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContactDetails(@PathParam("name") String name) throws AppException {
    	return Response.status(Response.Status.OK)//200
    			.entity(contactsObj.getContactDetails(name)).build();
    }
    @PUT
    @Path("{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public Response updateExistingContact(@PathParam("name") String name, Contacts contact) throws AppException {
    	contactsObj.updateContact(name, contact);
		return Response.status(Response.Status.OK)//200
				.entity("Contact details updated for name: "+name).build();
    }
  
    @DELETE
    @Path("{name}")
    @Produces(MediaType.TEXT_HTML)
    public Response deleteExistingContact(@PathParam("name") String name) throws AppException {
    	contactsObj.deleteContact(name);
    	return Response.status(Response.Status.OK)//200
    			.entity("Contact details deleted for name: "+name).build();
    }
}
