package net.akito.shared.client;

import java.io.IOException;

import net.akito.shared.client.gui.AudioSketchMainFrame;
import net.akito.shared.client.gui.WelcomePanel;
import net.akito.shared.protocol.AudioSketchProtocol;
import net.akito.shared.protocol.AudioSketchProtocol.MsgType;
import processing.net.Client;

/**
 * Entry class for application
 * @author akito
 *
 */
public class AudioSketchClientApp {

	private static final long serialVersionUID = 1341314636999597438L;
	private static String ADDRESS="localhost";	//set server address
	private static int PORT = 12345;				// port number to connect to
	private AudioSketchMainFrame mainPanel;					// main gui panel
	public Client client;						// client network 
	private String userName;

	/**
	 * Constructor for starting the application
	 * @param userName			user name who uses the app
	 */
	public AudioSketchClientApp(String userName) {
		//keep the reference to user name
		this.userName = userName;
				
		//Initialize gui
		try {
			mainPanel = new AudioSketchMainFrame(this);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		//Initialize client network
		client = new Client(mainPanel.getMusicalWindow(), ADDRESS, PORT);

		mainPanel.setClient(client);
		
		//send message
		String msg = MsgType.INIT.toString()+AudioSketchProtocol.SPLITTER+userName+AudioSketchProtocol.TERMINATOR;
		System.out.println(msg);
		client.write(msg);
	}
	
	public void close() throws IOException{
		client.write(MsgType.QUIT+AudioSketchProtocol.SPLITTER+userName+AudioSketchProtocol.TERMINATOR);
		client.stop();
	}
	
	/*---------------------- Getter/Setter ------------------*/
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Run the app
	 * @param args
	 */
	public static void main(String[] args) {

		//Show welcome panel so that user can type in their name
		WelcomePanel panel = new WelcomePanel();	
		while(!panel.isNameEntered()){	//Wait until user enters name
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		
		//Start the main app after name is typed in
		new AudioSketchClientApp(panel.getUserName());
	}
}
