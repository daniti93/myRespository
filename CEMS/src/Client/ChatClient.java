// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package Client;

import java.io.IOException;

import common.ChatIF;
import gui.LoginController;
import logic.Person;
import logic.User;
import ocsf.client.AbstractClient;

/**
 * This class overrides some of the methods defined in the abstract superclass
 * in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class ChatClient extends AbstractClient {
	// Instance variables **********************************************

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	ChatIF clientUI;

	public static boolean awaitResponse = false;

	@SuppressWarnings("unused")
	private LoginController loginController;
	private static ChatClient client = null;
	public static User user;
	public static String Permission;
	public static Person person;
	// Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host     The server to connect to.
	 * @param port     The port number to connect on.
	 * @param clientUI The interface type variable.
	 */

	public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
		super(host, port); // Call the superclass constructor
		this.clientUI = clientUI;
		// openConnection();
	}

	// Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	@SuppressWarnings("unchecked")
	public void handleMessageFromServer(Object msg) {
		Object msgFromServer = msg;
		System.out.println("--> handleMessageFromServer");
		awaitResponse = false;
		if (msgFromServer instanceof User) {
			user = (User) msg;
			if (user != null) {
				Permission = user.getPermssion();
			}
		} else if (msgFromServer instanceof Person) {
			person = (Person) msg;
		}

		else if (msgFromServer instanceof String)
			if (msgFromServer.equals("not found"))
				Permission = "not found";
			else if (msgFromServer.equals("nothing"))
				System.out.println("nothing");
			else if (msgFromServer.equals("alreadylogged"))
				Permission="alreadylogged";
	}

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message The message from the UI.
	 */

	public void handleMessageFromClientUI(String message) {
		try {
			openConnection();// in order to send more than one message
			awaitResponse = true;
			sendToServer(message);
			// wait for response
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			clientUI.display("Could not send message to server: Terminating client." + e);
			quit();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

	public ChatClient(String host, int port) throws Exception {
		super(host, port); // Call the superclass constructor
		openConnection();
		if (isConnected() == false) {
			throw new IOException("Could not connect to server.");
		}
		System.out.println("Connected");
		loginController = null;
	}

	public static ChatClient GetClient() {
		if (client != null) {
			return client;
		}
		return null;
	}

	public static ChatClient GetClient(String host, int port) throws Exception {
		if (client == null) {
			client = new ChatClient(host, port);
		}
		return client;
	}
}
//End of ChatClient class
