package gui;

import java.io.IOException;

import Server.EchoServer;
import Server.ServerLog;
import Server.ServerUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
;

public class ServerController {
	public static ServerController instance;
	public static int port;
	private EchoServer echoServer;

	@FXML
	private TextArea txtTextArea;

	@FXML
	private TextField txtPort;

	@FXML
	private Button btnStartServer;

	@FXML
	private Button btnExit;

	@FXML
	private TextField txtStatusColor;

	@FXML
	private Label txtStatus;

	@FXML
	void initialize() {
		ServerLog.setServerController(this);
		txtPort.setText("5555");
	}

	@FXML
	void ExitBtn(ActionEvent event) {
		try {
		    echoServer.close();
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    ServerLog.writeNewLine("Closing server failed.");
		}
		System.out.println("Bye Bye!!");
		System.exit(0);
	}
	public int getPort()
    {
    return Integer.parseInt(txtPort.getText());
    }
	@SuppressWarnings("unused")
	@FXML
	void StartServerBtn(ActionEvent event) throws IOException {
		if (true) { // add text check
			port = Integer.parseInt(txtPort.getText());
			ServerUI.runServer(port);
		} else {// if the port is not an int, don't runServer.
			try {

				ServerUI.runServer(port);
				
			} catch (NumberFormatException e) {
				 ServerLog.writeNewLine("error");
				
			}
	
		}

		txtStatus.setText("ON");
		btnStartServer.setDisable(true);
		txtPort.setDisable(true);
		txtStatus.setStyle("-fx-text-fill: white;");
		txtStatusColor.setStyle("-fx-background-color:LIMEGREEN;\n"+"-fx-border-color: BLACK;\n"+"-fx-border-width:2");
	}

	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ServerWindow.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("CEMS server prototype");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void AddMsgToLog(String msg) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				txtTextArea.appendText(msg);
			}
		});

	}

}
