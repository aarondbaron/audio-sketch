package net.gtcmt.audiosketch;

import net.gtcmt.audiosketch.gui.client.AudioSketchMainFrame;
import net.gtcmt.audiosketch.gui.client.WelcomePanel;
import net.gtcmt.audiosketch.network.client.Client;
import net.gtcmt.audiosketch.network.data.AudioSketchData;
import net.gtcmt.audiosketch.network.data.LoginData;
import net.gtcmt.audiosketch.network.util.MsgType;
import net.gtcmt.audiosketch.sound.util.AudioControl;
import net.gtcmt.audiosketch.util.LogMessage;

/**
 * Entry class for application
 * @author akito
 *
 */
public class AudioSketchClientApp {

	private static final long serialVersionUID = 1341314636999597438L; 

	/**
	 * Constructor for starting the application
	 * @param userName			user name who uses the app
	 */
	public AudioSketchClientApp(String userName, Client client) {
			
		//Get p5 to init
		AudioControl.getAudioCtrl();
		
		AudioSketchMainFrame mainFrame = null;
		//Initialize gui
		try {
			mainFrame = new AudioSketchMainFrame(client, userName);
		} catch (Throwable e) {
			LogMessage.javaErr(e);
		}
		
		//Reference to main frame is passed
		client.setMainFrame(mainFrame);
		
		//send message
		client.sendData(new AudioSketchData(MsgType.LOGIN, new LoginData(), userName));
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
		new AudioSketchClientApp(panel.getUserName(), panel.getClient());
	}
}
