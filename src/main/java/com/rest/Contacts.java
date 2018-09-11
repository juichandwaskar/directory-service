/**
 * 
 */
package com.rest;

/**
 * @author jui
 *
 */
public class Contacts {
	
	private String name, phoneNumber, email;
	
	public Contacts() {
		super();
	}

	public Contacts(String name, String phoneNumber, String email) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contacts [name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}

}
