package net.gtcmt.audiosketch.network.util;

import net.gtcmt.audiosketch.network.client.ClientNetwork;
import net.gtcmt.audiosketch.network.data.AudioSketchData;
import net.gtcmt.audiosketch.network.server.ClientMessageHandler;

public class AudioSketchProtocol {

	public static final String SPLITTER = " ";
	public static final String TERMINATOR = " \n";
	public static final int TERMINATOR_BYTE = 10;
	
	/**
	 * Splits up token based on splitter
	 * @param data
	 * @return
	 */
	public static String[] createTokens(String data){
		return data.split(SPLITTER);
	}
	
	/**
	 * Processes message received from client. mostly ends up broad casting back
	 * to all clients.
	 * @param asData
	 * @param clientHandler
	 */
	public void processClientInput(AudioSketchData asData, ClientMessageHandler clientHandler){
		
	}
	
	public void processServerInput(AudioSketchData asData, ClientNetwork clientNetwork){
		
	}
}
