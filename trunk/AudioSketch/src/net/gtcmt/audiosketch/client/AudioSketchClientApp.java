package net.gtcmt.audiosketch.client;

import net.gtcmt.audiosketch.client.gui.AudioSketchMainFrame;
import net.gtcmt.audiosketch.client.gui.WelcomePanel;
import net.gtcmt.audiosketch.network.client.Client;
import net.gtcmt.audiosketch.network.data.AudioSketchData;
import net.gtcmt.audiosketch.network.data.LoginData;
import net.gtcmt.audiosketch.network.util.MsgType;
import net.gtcmt.audiosketch.util.LogMessage;

/**
 * Entry class for application
 * @author akito
 *
 */
public class AudioSketchClientApp {

	private static final long serialVersionUID = 1341314636999597438L;
	private static String ADDRESS="localhost";	//set server address
	private static int PORT = 12345;				// port number to connect to
	public Client client;						// client network 

	/**
	 * Constructor for starting the application
	 * @param userName			user name who uses the app
	 */
	public AudioSketchClientApp(String userName) {
		
		//Initialize client network
		client = new Client(ADDRESS, PORT);
		client.start();
		
		AudioSketchMainFrame mainFrame = null;
		//Initialize gui
		try {
			mainFrame = new AudioSketchMainFrame(client, userName);
		} catch (Throwable e) {
			LogMessage.javaErr(e);
		}
		
		client.setMainFrame(mainFrame);
		
		//send message
		client.getOutQueue().push(new AudioSketchData(MsgType.LOGIN, new LoginData(), userName, 0));
	}
	
	/*---------------------- Getter/Setter ------------------*/
	
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
				LogMessage.javaErr(e);
			}
		} 
		
		//Start the main app after name is typed in
		new AudioSketchClientApp(panel.getUserName());
	}
}
