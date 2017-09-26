package com.alandugger;

import javafx.beans.property.SimpleStringProperty;

public class Contact {
	
	private SimpleStringProperty firstName = new SimpleStringProperty("");
	private SimpleStringProperty lastName = new SimpleStringProperty("");
	private SimpleStringProperty phoneNumber = new SimpleStringProperty("");
	private SimpleStringProperty notes = new SimpleStringProperty("");
	
	public Contact() {
		new Contact("", "", "", "");
	}
	
	public Contact(String firstName, String lastName, String phoneNumber, String notes) {
		setFirstName(firstName);
		setLastName(lastName);
		setPhoneNumber(phoneNumber);
		setNotes(notes);
	}
		
	public void setFirstName(String name) {
		firstName.set(name);
	}
	
	public void setLastName(String name) {
		lastName.set(name);
	}
	
	public void setPhoneNumber(String number) {
		phoneNumber.set(number);
	}
	
	public void setNotes(String newNotes) {
		notes.set(newNotes);
	}

	public SimpleStringProperty getFirstNameSSP() {
		return firstName;
	}

	public SimpleStringProperty getLastNameSSP() {
		return lastName;
	}

	public SimpleStringProperty getPhoneNumberSSP() {
		return phoneNumber;
	}

	public SimpleStringProperty getNotesSSP() {
		return notes;
	}

	public String  getFirstName() {
		return firstName.getValue();
	}

	public String  getLastName() {
		return lastName.getValue();
	}

	public String  getPhoneNumber() {
		return phoneNumber.getValue();
	}

	public String  getNotes() {
		return notes.getValue();
	}
	

}
