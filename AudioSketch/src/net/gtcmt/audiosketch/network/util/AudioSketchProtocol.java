package net.gtcmt.audiosketch.network.util;

import net.gtcmt.audiosketch.gui.client.AudioSketchMainFrame;
import net.gtcmt.audiosketch.network.client.ClientNetwork;
import net.gtcmt.audiosketch.network.data.AudioEffectData;
import net.gtcmt.audiosketch.network.data.AudioSketchData;
import net.gtcmt.audiosketch.network.data.PlaybackData;
import net.gtcmt.audiosketch.network.data.RelocationData;
import net.gtcmt.audiosketch.network.data.SoundObjectData;
import net.gtcmt.audiosketch.network.server.ClientMessageHandler;
import net.gtcmt.audiosketch.util.LogMessage;

/**
 * Protocol for server/client communication is defined here.
 * @author akito
 *
 */
public class AudioSketchProtocol {

	public static final String SPLITTER = " ";
	public static final String TERMINATOR = " \n";
	public static final int TERMINATOR_BYTE = 10;
	
	private AudioSketchMainFrame mainFrame;		//Reference to musical window so that we can write some stuff in it.

	/**
	 * Splits up token based on splitter
	 * @param data
	 * @return
	 */
	public static String[] createTokens(String data){
		return data.split(SPLITTER);
	}
	
	/**
	 * Processes message received from client. mostly ends up broadcasting back
	 * to all clients.
	 * @param asData
	 * @param clientHandler
	 */
	//TODO: think about what needs to get stored on server side to make things easier for clients
	public void processClientInput(AudioSketchData asData, ClientMessageHandler clientHandler) {
		clientHandler.getServer().broadCastEvent(asData);
	}
	
	//TODO synch
	public void processServerInput(AudioSketchData asData, ClientNetwork clientNetwork){
		switch (asData.getMsgType()) {
		case ADD_OBJECT:
			synchronized (mainFrame.getMusicalWindow().getLockObject()) {
			mainFrame.getMusicalWindow().addSoundObject((SoundObjectData) asData.getMsgData());
			}
			break;
		case MOVE_OBJECT:
			mainFrame.getMusicalWindow().moveObject((RelocationData) asData.getMsgData());
			break;
		case PLAY_BAR:
			synchronized (mainFrame.getMusicalWindow().getLockObject()) {
			mainFrame.getMusicalWindow().addPlayBackBar((PlaybackData) asData.getMsgData(), asData.getUserData());
			}
			break;
		case EFFECT_BOX:
			synchronized (mainFrame.getMusicalWindow().getLockObject()) {
			mainFrame.getMusicalWindow().addEffectBox((AudioEffectData) asData.getMsgData(), asData.getUserData());
			}
			break;
		case REMOVE_OBJECT:
			synchronized (mainFrame.getMusicalWindow().getLockObject()) {
				mainFrame.getMusicalWindow().remove();
			}
			break;
		case REMOVE_EFFECT:
			synchronized (mainFrame.getMusicalWindow().getLockObject()) {
				mainFrame.getMusicalWindow().removeEffectBox();
			}
			break;
		case USER_NAME:
		case CHAT:
		case LOGIN:
		case QUIT:
			//TODO
			break;
		default:
			LogMessage.err("Doesn't understand message type "+asData.getMsgData().toString());
			break;
		}
	}
	
	public AudioSketchMainFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * This needs to be called once before processServerInput is called for the first time
	 * @param mainFrame
	 */
	public void setMainFrame(AudioSketchMainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
}
