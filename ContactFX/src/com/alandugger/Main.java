package com.alandugger;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {		
		Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		primaryStage.setTitle("My Contacts");
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.setScene(new Scene (root, 900, 400));		
		primaryStage.show();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void stop() throws Exception {
		try 
		{
			ContactData.getInstance().saveContacts();
		}	
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}	
	
	public void init() throws Exception 
	{
		try 
		{						
			ContactData.getInstance().loadContacts();			
		}	
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
