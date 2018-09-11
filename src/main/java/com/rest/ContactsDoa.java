package com.rest;

import java.util.*;

import javax.ws.rs.core.Response;

import com.rest.exception.AppException;

public class ContactsDoa {
	

	private static HashMap<String, Contacts> contactCatalog;
	
    public ContactsDoa() {
        initializeContactCatalog();
    }


    private void initializeContactCatalog() {
    	if (contactCatalog == null) {
    		contactCatalog = new HashMap<String, Contacts>();
    	}
	}
    
    public Set<String> getAllContactNames(){
    	return contactCatalog.keySet();
    }
    
    public Contacts getContactDetails(String name) throws AppException {
    	if (contactCatalog.containsKey(name) != true) {
    		throw new AppException(Response.Status.NOT_FOUND, Response.Status.NOT_FOUND.getStatusCode(), "Contact name:" + name + " not present in records.");
    	}
    	return contactCatalog.get(name);
    }
    
    public void addNewContact(Contacts contact) throws AppException {
    	if (contact.getName() == null) {
    		throw new AppException(Response.Status.BAD_REQUEST, Response.Status.BAD_REQUEST.getStatusCode(), "Contact name must be defined.");
    	}
        else if (contactCatalog.containsKey(contact.getName()) == true){
    		throw new AppException(Response.Status.CONFLICT, Response.Status.CONFLICT.getStatusCode(), "Contact name:" + contact.getName() + " already exists in records.");
    	}
    	
    	contactCatalog.put(contact.getName(), contact);
    }


	public void updateContact(String name, Contacts contactUpdate) throws AppException  {
		if (contactCatalog.containsKey(name) != true) {
    		throw new AppException(Response.Status.NOT_FOUND, Response.Status.NOT_FOUND.getStatusCode(), "Contact name:" + name + " not present in records.");
    	}
	    Contacts updatedContact = contactCatalog.get(name);
		if (contactUpdate.getPhoneNumber() != null) {
		    updatedContact.setPhoneNumber(contactUpdate.getPhoneNumber());
		}
		if (contactUpdate.getEmail() != null) {
		    updatedContact.setEmail(contactUpdate.getEmail());
		}
	}

	public void deleteContact(String name) throws AppException {
		if (contactCatalog.containsKey(name) != true) {
    		throw new AppException(Response.Status.NOT_FOUND, Response.Status.NOT_FOUND.getStatusCode(), "Contact name:" + name + " not present in records.");
    	}
		contactCatalog.remove(name);
	}

}
