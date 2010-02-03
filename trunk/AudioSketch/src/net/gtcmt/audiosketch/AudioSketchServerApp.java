package net.gtcmt.audiosketch;

import net.gtcmt.audiosketch.gui.server.LogWindow;
import net.gtcmt.audiosketch.network.server.ServerNetwork;

public class AudioSketchServerApp {

	private static final long serialVersionUID = -6803575010894689664L;
	private ServerNetwork serverNetwork;
	
	public AudioSketchServerApp(){
		LogWindow.getLogWindow().setVisible(true);
		
		serverNetwork = new ServerNetwork(ServerNetwork.DEFAULT_PORT);
		serverNetwork.start();
		
		LogWindow.getLogWindow().setServerNetwork(serverNetwork);
	}
		
	public static void main(String[] args){
		new AudioSketchServerApp();
	}
}