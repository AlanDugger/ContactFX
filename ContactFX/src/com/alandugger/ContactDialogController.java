package com.alandugger;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ContactDialogController {
	
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtPhoneNumber;
	@FXML
	private TextArea txtNotesArea;	

	public void setAsCurrentContact(String fName, String lName, String pNumber, String notes) {
		txtFirstName.setText(fName);
		txtLastName.setText(lName);
		txtPhoneNumber.setText(pNumber);
		txtNotesArea.setText(notes);
		return;
	}
	
	public Contact processResults() 
	{
		String firstName = txtFirstName.getText();
		String lastName = txtLastName.getText();
		String phoneNumber = txtPhoneNumber.getText();
		String notes = txtNotesArea.getText();				
		
		Contact newContact = new Contact(firstName, lastName, phoneNumber, notes);
		
		ContactData.getInstance().addContact(newContact);
		
		return newContact;
	}

}
