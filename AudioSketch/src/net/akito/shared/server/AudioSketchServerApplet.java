package net.akito.shared.server;

import processing.core.PApplet;
import processing.net.Client;
import processing.net.Server;

public class AudioSketchServerApplet extends PApplet {

	private static final long serialVersionUID = -6803575010894689664L;
	private Server server;
	private Client client;
	private String input;
	private static int PORT = 12345;
	
	public AudioSketchServerApplet(){}
	
	public void setup() {
	  server = new Server(this, PORT); // Start a simple server on a port
	}

	public void draw() {
		// Receive data from client
		client = server.available();
		if (client != null) {
			input = client.readString();
			server.write(input);
		}
	}
	
	public static void main(String[] args){
		new AudioSketchServerApplet();
	}
}