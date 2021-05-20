package gui;

import java.io.IOException;
import java.net.URL;

import Client.ChatClient;
import Client.ClientUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private TextField UserNameField;

	@FXML
	private Button LoginBtn;

	@FXML
	private Button btnExit;

	@FXML
	private PasswordField PasswordField;

	@FXML
	void ClickLogin(ActionEvent event) throws IOException {
		ClientUI.chat.accept("Login " + UserNameField.getText() + " " + PasswordField.getText());
		FXMLLoader loader = new FXMLLoader();
		String Permission = ChatClient.Permission;
		if (Permission.equals("Principal")) {
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			URL location = getClass().getResource("/gui/PrincipalScreen.fxml");
			loader.setLocation(location);
			Stage primaryStage = new Stage();
			Pane root = loader.load(location.openStream());
			PrincipalController principalController = loader.getController();
			principalController.loadDetails(ChatClient.person);
			Scene scene = new Scene(root);
			primaryStage.setTitle("Principal");
			primaryStage.setScene(scene);
			primaryStage.show();
		} else if (Permission.equals("Teacher")) {
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			URL location = getClass().getResource("/gui/TeacherScreen.fxml");
			loader.setLocation(location);
			Stage primaryStage = new Stage();
			Pane root = loader.load(location.openStream());
			TeacherController teacherController = loader.getController();
			teacherController.loadDetails(ChatClient.person);
			Scene scene = new Scene(root);
			primaryStage.setTitle("Teacher");
			primaryStage.setScene(scene);
			primaryStage.show();

		} else if (Permission.equals("Student")) {
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			URL location = getClass().getResource("/gui/StudentScreen.fxml");
			loader.setLocation(location);
			Stage primaryStage = new Stage();
			Pane root = loader.load(location.openStream());
			StudentController studentController = loader.getController();
			studentController.loadDetails(ChatClient.person);
			Scene scene = new Scene(root);
			primaryStage.setTitle("Student");
			primaryStage.setScene(scene);
			primaryStage.show();

		} else if (Permission.equals("not found")) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Validation Error");
					alert.setHeaderText(null);
					alert.setContentText("Wrong Username of Password.");
					alert.showAndWait();
				}
			});
		}
		else if(Permission.equals("alreadylogged"))
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Connection Error");
					alert.setHeaderText(null);
					alert.setContentText("User Allready Connected.");
					alert.showAndWait();
				}
			});
			
	}

	@FXML
	void ExitBtn(ActionEvent event) {
		System.out.println("Bye Bye!!");
		System.exit(0);
	}

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/LoginScreen.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("CEMS - Login");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
