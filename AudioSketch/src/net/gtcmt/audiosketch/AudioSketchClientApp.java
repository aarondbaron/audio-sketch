package net.gtcmt.audiosketch;

import net.gtcmt.audiosketch.gui.client.AudioSketchMainFrame;
import net.gtcmt.audiosketch.sound.util.AudioControl;
import net.gtcmt.audiosketch.util.LogMessage;

/**
 * Entry class for application
 * @author akito
 * @deprecated
 */
public class AudioSketchClientApp {

	private static final long serialVersionUID = 1341314636999597438L; 

	/**
	 * Constructor for starting the application
	 * @param userName			user name who uses the app
	 */
	public AudioSketchClientApp() {	
		//Get p5 to init
		AudioControl.getAudioCtrl();
		
		//Initialize gui
		try {
			new AudioSketchMainFrame("SHIT");
		} catch (Throwable e) {
			LogMessage.javaErr(e);
		}
	}
	
	/*---------------------- Getter/Setter ------------------*/
	
	/**
	 * Run the app
	 * @param args
	 */
	public static void main(String[] args) {
	
		//Start the main app after name is typed in
		new AudioSketchClientApp();
	}
}
