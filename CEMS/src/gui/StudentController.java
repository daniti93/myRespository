package gui;

import java.io.IOException;

import Client.ClientUI;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.Person;

public class StudentController {

	@FXML
	private Label lblWelcome;

	@FXML
	private Button btnTakeExam;

	@FXML
	private TextField txtLastName;

	@FXML
	private TextField txtFirstName;

	@FXML
	private TextField txtPhone;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtAddress;

	@FXML
	private Button btnExit;

	@FXML
	void ExitBtn(ActionEvent event) {
		ClientUI.chat.accept("Logout "+ per.getID());
		System.out.println("Bye Bye!!");
		System.exit(0);
	}

	@FXML
	private Tab myProfileTab;
	
	private Person per;
	@FXML
	void SelectMyProfile(Event event) {
		if (myProfileTab.isSelected())
			loadDetails(per);
	}

	@FXML
	void initialize() {
		txtFirstName.setDisable(true);
		txtLastName.setDisable(true);
	}
	

	@FXML
	void LogOutClick(MouseEvent event) throws IOException {
		ClientUI.chat.accept("Logout "+ per.getID());
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Parent root = FXMLLoader.load(getClass().getResource("/gui/LoginScreen.fxml"));
		Scene scene = new Scene(root);
		Stage primaryStage = new Stage();
		primaryStage.setTitle("CEMS - Login");
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public void loadDetails(Person person) {
		per = person;
		txtAddress.setText(person.getAddress());
		txtEmail.setText(person.getEmail());
		txtPhone.setText(person.getPhone());
		txtFirstName.setText(person.getFirstName());
		txtLastName.setText(person.getLastName());
		lblWelcome.setText("Welcome " + person.getFirstName()+" "+ person.getLastName());

	}

}
