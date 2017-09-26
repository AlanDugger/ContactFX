package com.alandugger;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Controller {
	
	@FXML
	private BorderPane mainBorderPane;
	@FXML
	private TableView<Contact> tableView = new TableView<Contact>();
	@FXML
	private VBox tableVbox;
	
	
	@FXML
	public void initialize() 
	{
				
		tableView.setEditable(true);
		tableView.setItems(ContactData.getInstance().getContacts());
		tableView.setPlaceholder(new Label("No contacts to display."));
		//Establish first column of data -> First Names
		TableColumn<Contact, String> firstNameCol = new TableColumn<Contact, String>("First Name");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstName"));
		
		//Establish second column of data -> Last Names
		TableColumn<Contact, String> lastNameCol = new TableColumn<Contact, String>("Last Name");
		lastNameCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("lastName"));
		
		//Establish third column of data -> Phone Numbers
		TableColumn<Contact, String> phoneNumberCol = new TableColumn<Contact, String>("Phone Number");
		phoneNumberCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("phoneNumber"));
		
		//Establish last column of data -> Notes
		TableColumn<Contact, String> notesCol = new TableColumn<Contact, String>("Notes");
		notesCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("notes"));
		
		//Set the column widths
		firstNameCol.setMinWidth(200);
		lastNameCol.setMinWidth(200);
		phoneNumberCol.setMinWidth(150);
		notesCol.setMinWidth(350);
				
		tableView.getColumns().add(firstNameCol);
		tableView.getColumns().add(lastNameCol);
		tableView.getColumns().add(phoneNumberCol);
		tableView.getColumns().add(notesCol);
	
	}
	
	@FXML
	public void showNewItemDialog() 
	{
		
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(mainBorderPane.getScene().getWindow());
		dialog.setTitle("Add New Contact Entry");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("ContactDialog.fxml"));
		
		
		try {			
			dialog.getDialogPane().setContent(fxmlLoader.load());
		}
		catch (IOException e) {
			System.out.println("Couldn't load the dialog");
			e.printStackTrace();
			return;
		}	
		
		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);			
		
		Optional<ButtonType> result = dialog.showAndWait();
		
		if (result.isPresent() && result.get() == ButtonType.OK) 
		{
			ContactDialogController controller = fxmlLoader.getController();
			controller.processResults();				
		}
		else
		{
			
		}
	}
	
	
	public void deleteContact(Contact contact, boolean reqConfirmation) 
	{
		
		if (reqConfirmation == true) 
		{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setHeaderText("Delete Contact for " + contact.getFirstName() + " " + contact.getLastName());
			alert.setTitle("Delete Contact");
			alert.setContentText("Are you sure?  Press OK to confirm.");		
			Optional<ButtonType> result = alert.showAndWait();
		
			if (result.isPresent() && result.get() == ButtonType.OK)
			{
				ContactData.getInstance().removeContact(contact);
			}
		}
		else if (reqConfirmation == false && contact != null)
			ContactData.getInstance().removeContact(contact);
			
	}
	
	public void editContact(Contact contact) 
	{		
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.initOwner(mainBorderPane.getScene().getWindow());
		dialog.setTitle("Edit Contact Entry");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("ContactDialog.fxml"));
		
		
		
		try {			
			dialog.getDialogPane().setContent(fxmlLoader.load());
		}
		catch (IOException e) {
			System.out.println("Couldn't load the dialog");
			e.printStackTrace();
			return;
		}
		
		ContactDialogController controller = fxmlLoader.getController();
		
		controller.setAsCurrentContact(contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(), contact.getNotes());
		dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
		dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);			
		
		
		Optional<ButtonType> result = dialog.showAndWait();
		
		if (result.isPresent() && result.get() == ButtonType.OK) 
		{
			//Create new entry
			controller.processResults();
			//Delete old entry
			deleteContact(contact, false);
		}
	
	}
	
	@FXML
	public void handleKeyPressed(KeyEvent keyEvent) {
		Contact contact = tableView.getSelectionModel().getSelectedItem();
		
		if (contact != null) {
			if (keyEvent.getCode().equals(KeyCode.DELETE)) {
				deleteContact(contact, true);
			}
		}
	}
	
	@FXML
	public void handleDeleteContact() {
		Contact contact = tableView.getSelectionModel().getSelectedItem();
		
		if (contact != null) {
			deleteContact(contact, true);
		}
	}
	
	@FXML
	public void handleEditContact() {
		Contact contact = tableView.getSelectionModel().getSelectedItem();
		
		if (contact != null) {
			editContact(contact);
		}
	}
	
}
