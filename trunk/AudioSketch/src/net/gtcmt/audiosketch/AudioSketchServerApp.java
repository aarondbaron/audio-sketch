package net.gtcmt.audiosketch;

import net.gtcmt.audiosketch.network.server.ServerNetwork;

public class AudioSketchServerApp {

	private static final long serialVersionUID = -6803575010894689664L;
	private static final int PORT = 12345;
	private ServerNetwork serverNetwork;
	
	public AudioSketchServerApp(){
		serverNetwork = new ServerNetwork(PORT);
		serverNetwork.start();
	}
		
	public static void main(String[] args){
		new AudioSketchServerApp();
	}
}