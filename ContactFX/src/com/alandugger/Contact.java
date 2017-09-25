package com.alandugger;

import javafx.beans.property.SimpleStringProperty;

public class Contact {
	
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty phoneNumber;
	private SimpleStringProperty notes;
	
	public Contact() {
		this.firstName = new SimpleStringProperty();
		this.lastName = new SimpleStringProperty();
		this.phoneNumber = new SimpleStringProperty();
		this.notes = new SimpleStringProperty();
	}
		
	public void setFirstName(String name) {
		this.firstName.set(name);
	}
	
	public void setLastName(String name) {
		this.firstName.set(name);
	}
	
	public void setPhoneNumber(String number) {
		this.firstName.set(number);
	}
	
	public void setNotes(String notes) {
		this.firstName.set(notes);
	}

	public String getFirstName() {
		return firstName.getValue();
	}

	public String getLastName() {
		return lastName.getValue();
	}

	public String getPhoneNumber() {
		return phoneNumber.getValue();
	}

	public String getNotes() {
		return notes.getValue();
	}
	
	

}
