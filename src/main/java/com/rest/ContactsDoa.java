package com.rest;

import java.util.*;

import javax.ws.rs.core.Response;

import com.rest.exception.AppException;

/**
 * Contacts data access object class
 */
public class ContactsDoa {

	// contactCatalog stores contacts information
	private static HashMap<String, Contacts> contactCatalog;

	public ContactsDoa() {
		initializeContactCatalog();
	}


	private void initializeContactCatalog() {
		if (contactCatalog == null) {
			contactCatalog = new HashMap<String, Contacts>();
		}
	}

	/**
	 * Get contact names method.
	 * @return Set of names
	 */
	public Set<String> getAllContactNames(){
		return contactCatalog.keySet();
	}

	/**
	 * Get contact details method.
	 * @return Contacts object
	 * @throws AppException
	 */
	public Contacts getContactDetails(String name) throws AppException {
		if (contactCatalog.containsKey(name) != true) {
			throw new AppException(Response.Status.NOT_FOUND, Response.Status.NOT_FOUND.getStatusCode(), "Contact name:" + name + " not present in records.");
		}
		return contactCatalog.get(name);
	}

	/**
	 * Method to add new contact details to contactCatalog
	 * @throws AppException
	 */
	public void addNewContact(Contacts contact) throws AppException {
		if (contact.getName() == null) {
			throw new AppException(Response.Status.BAD_REQUEST, Response.Status.BAD_REQUEST.getStatusCode(), "Contact name must be defined.");
		}
		else if (contactCatalog.containsKey(contact.getName()) == true){
			throw new AppException(Response.Status.CONFLICT, Response.Status.CONFLICT.getStatusCode(), "Contact name:" + contact.getName() + " already exists in records.");
		}

		contactCatalog.put(contact.getName(), contact);
	}

	/**
	 * Method to update contact details in contactCatalog
	 * @return
	 * @throws AppException
	 */
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

	/**
	 * Method to delete contact details in contactCatalog
	 * @return
	 * @throws AppException
	 */
	public void deleteContact(String name) throws AppException {
		if (contactCatalog.containsKey(name) != true) {
			throw new AppException(Response.Status.NOT_FOUND, Response.Status.NOT_FOUND.getStatusCode(), "Contact name:" + name + " not present in records.");
		}
		contactCatalog.remove(name);
	}

}
