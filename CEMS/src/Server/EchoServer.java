package Server;
// This file contains material supporting section 3.7 of the textbook:

import java.io.IOException;

// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import gui.ServerController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.Person;
import logic.User;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;res
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer {
	// Class variables *************************************************
	static Connection conn;
	public ServerController sc;
	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 */
	public EchoServer(int port) {
		super(port);
	}

	public void CreateDataBase() {
		String query;
		Statement st;
		try {
			query = ("CREATE TABLE CEMS.USERS(UserName VARCHAR(30), Password VARCHAR(20), Permssion VARCHAR(45), Status BOOLEAN, PRIMARY KEY(UserName));");
			st = conn.createStatement();
			st.executeUpdate(query);
			ServerLog.writeNewLine("Table created: USERS");
			try { // Add some users to DB
				st = conn.createStatement();
				query = ("INSERT INTO USERS (UserName, Password, Permssion, Status ) VALUES('Ronen.Melihov', 'Ronen123', 'Principal', FALSE);");
				st.executeUpdate(query);
				query = ("INSERT INTO USERS (UserName, Password, Permssion,Status ) VALUES('Danit.Doron', 'Danit123', 'Teacher', FALSE);");
				st.executeUpdate(query);
				query = ("INSERT INTO USERS (UserName, Password, Permssion,Status ) VALUES('Saar.Keshet', 'Saar123', 'Teacher', FALSE);");
				st.executeUpdate(query);
				query = ("INSERT INTO USERS (UserName, Password, Permssion, Status ) VALUES('Eden.Atias', 'eden123', 'Teacher',FALSE);");
				st.executeUpdate(query);
				query = ("INSERT INTO USERS (UserName, Password, Permssion, Status ) VALUES('Zohar.Barel', 'zohar123', 'Teacher', FALSE);");
				st.executeUpdate(query);
				query = ("INSERT INTO USERS (UserName, Password, Permssion, Status ) VALUES('Vladislav.Yefremov', 'mosh123', 'Student',  FALSE);");
				st.executeUpdate(query);
				query = ("INSERT INTO USERS (UserName, Password, Permssion, Status ) VALUES('Jeniuchka.Vex', 'JEN123', 'Student', FALSE);");
				st.executeUpdate(query);
				query = ("INSERT INTO USERS (UserName, Password, Permssion, Status ) VALUES('Yossi.Cohen', 'yoss123', 'Student', FALSE);");
				st.executeUpdate(query);
				query = ("INSERT INTO USERS (UserName, Password, Permssion, Status ) VALUES('Brano.Tagania', 'bra123', 'Student', FALSE);");
				st.executeUpdate(query);
				query = ("INSERT INTO USERS (UserName, Password, Permssion, Status ) VALUES('Dani.Rop', 'dani123', 'Student', FALSE);");
				st.executeUpdate(query);
			} catch (SQLException sqlException) {
				if (sqlException.getErrorCode() == 1050) { // Table already exists
					// error
					ServerLog.writeNewLine("Table already exist: USERS");
				} else {
					ServerLog.writeNewLine(sqlException.getMessage());
				}
			}

		} catch (SQLException sqlException) {
			if (sqlException.getErrorCode() == 1050) { // Table already exists
				// error
				ServerLog.writeNewLine("Table already exist: USERS");
			} else {
				ServerLog.writeNewLine(sqlException.getMessage());
			}
		}
	}
	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 */
	private boolean flag = false;

	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		String str = (String) msg;
		User user = null;
		String[] s = str.split(" ");
		if (s[0].equals("Login")) {
			System.out.println("Jen");
			user = ExistenceCheck(s[1], s[2]);
			System.out.println("Jen1");
			if (user != null) {
				try {
					System.out.println("Jen2");
					client.sendToClient(user);
					client.sendToClient(person);
					System.out.println("Jen3");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (user == null && flag) { //if user not found it and the flag is on it mean the user allready connected
				System.out.println("Jen4");
				try {
					client.sendToClient("alreadylogged");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Jen5");
			} else
				try {
					System.out.println("Jen6");
					client.sendToClient("not found");
					System.out.println("Jen7");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} else if (s[0].equals("Logout")) {
			System.out.println("Jen8");
			try {
				ChangeStatusOfConnection(GetUserName(s[1]), false);
				client.sendToClient("nothing");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String GetUserName(String id) throws SQLException {
		String sql = ("SELECT UserName FROM person WHERE ID='" + id + "';");
		Statement st = conn.createStatement();
		ResultSet rs;
		rs = st.executeQuery(sql);
		if (rs.next()) {
			String User = rs.getString("UserName");
			return User;
		}
		return null;

	}

	/*------------------------------------------------------------------------------------------------------------------------
	 *													 Queries
	--------------------------------------------------------------------------------------------------------------------------*/
	// check existence of a user name
	private User ExistenceCheck(String Username, String password) {
		String sql = ("SELECT * FROM USERS WHERE UserName='" + Username + "' AND Password='" + password + "';");
		User user = null;
		try {
			Statement st = conn.createStatement();
			ResultSet rs;
			rs = st.executeQuery(sql);
			if (rs.next()) {
				String UserName = rs.getString("UserName");
				String Password = rs.getString("Password");
				String Permssion = rs.getString("Permssion");
				boolean status = rs.getBoolean("Status");
				if (!status) {
					user = new User(UserName, Password, Permssion);
					ChangeStatusOfConnection(Username, true);
					getPersonData(Username);
				}
				flag=status;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	// change the status of connection
	private void ChangeStatusOfConnection(String username, boolean status) throws SQLException {
		Statement st = conn.createStatement();
		String sql = ("UPDATE users SET Status=" + status + " WHERE UserName='" + username + "';");
		st.executeUpdate(sql);
		flag=status;
	}

	private Person person;

	// this method used to get all the personal data of a person
	private void getPersonData(String username) {
		Person person = null;
		String sql = ("SELECT * FROM person WHERE UserName='" + username + "';");
		try {
			Statement st = conn.createStatement();
			ResultSet rs;
			rs = st.executeQuery(sql);
			if (rs.next()) {
				String Id = rs.getString("ID");
				String Firstname = rs.getString("FirstName");
				String Lastname = rs.getString("LastName");
				String Phone = rs.getString("Phone");
				String Email = rs.getString("Email");
				String Address = rs.getString("Address");
				person = new Person(Id, Firstname, Lastname, Phone, Email, Address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.person = person;
	}

//	private List<Test> UpdateDB(String IdUpdate, int newDuration) throws SQLException {
//		Statement st = conn.createStatement();
//		String sql = ("UPDATE Test SET Duration=" + newDuration + " WHERE ID=" + IdUpdate + ";");
//		st.executeUpdate(sql);
//		String sql2 = ("SELECT * FROM Test WHERE ID=" + IdUpdate + ";");
//		return QueryExecute(sql2);
//	}
//
//	private List<Test> GetTestsFromDBBySearch(String string) throws SQLException {
//		String sql = ("SELECT* FROM Test WHERE ID LIKE '%" + string + "%' OR Profession LIKE '%" + string
//				+ "%' OR Course LIKE '%" + string + "%';");
//		return QueryExecute(sql);
//	}
//
//	public List<Test> GetTestsFromDB() throws SQLException {
//		String sql = ("SELECT * FROM Test;");
////		return QueryExecute(sql);
////	}
//

	/*
	 * -----------------------------------------------------------------------------
	 * --------------------------------------
	 * -----------------------------------------------------------------------------
	 * --------------------------------------
	 */
	@Override
	protected void clientConnected(ConnectionToClient client) {
		super.clientConnected(client);
		ServerLog.writeNewLine("Client connected: " + client.toString());
	}

	@SuppressWarnings("deprecation")
	public void MySqlConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			ServerLog.writeNewLine("Driver definition succeed");
		} catch (Exception ex) {
			/* handle the error */
			ServerLog.writeNewLine("Driver definition failed");
		}
		try {

			conn = DriverManager.getConnection("jdbc:mysql://localhost/CEMS?serverTimezone=IST", "root", "DanitDoron93");

			CreateDataBase();
			ServerLog.writeNewLine("SQL connection succeed");
		} catch (SQLException ex) {/* handle any errors */
			ServerLog.writeNewLine("SQLException: " + ex.getMessage());
			ServerLog.writeNewLine("SQLState: " + ex.getSQLState());
			ServerLog.writeNewLine("VendorError: " + ex.getErrorCode());
		}
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		// connect to DB
		MySqlConnection();

	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		ServerLog.writeNewLine("Server has stopped listening for connections.");
	}

	// Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the server instance (there is
	 * no UI in this phase).
	 *
	 * @param args[0] The port number to listen on. Defaults to 5555 if no argument
	 *                is entered.
	 */
	public static void main(String[] args) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
		}

		EchoServer sv = new EchoServer(port);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			ServerLog.writeNewLine("ERROR - Could not listen for clients!");
		}
	}

}

//End of EchoServer class
