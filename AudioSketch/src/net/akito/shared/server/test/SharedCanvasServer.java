package net.akito.shared.server.test;

import processing.core.PApplet;
import processing.net.Client;
import processing.net.Server;

public class SharedCanvasServer extends PApplet {

	private static final long serialVersionUID = 1L;
	private Server server;
	private Client client;
	private String input;
	private String data[];
	
	public void setup() 
	{
	  size(450, 255);
	  background(204);
	  stroke(0);
	  frameRate(5); // Slow it down a little
	  server = new Server(this, 12345); // Start a simple server on a port
	}

	public void draw() {
	  if (mousePressed == true) {
	    // Draw our line
	    stroke(255);
	    line(pmouseX, pmouseY, mouseX, mouseY);
	    // Send mouse coords to other person
	    server.write(pmouseX + " " + pmouseY + " " + mouseX + " " + mouseY + "\n");
	  }
	  // Receive data from client
	  client = server.available();
	  if (client != null) {
	    input = client.readString();
	    input = input.substring(0, input.indexOf("\n")); // Only up to the newline
	    data = input.split(" ");

	    // Draw line using received coords
	    stroke(0);
	    line(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
	    //Send to other client
	    server.write(data[0]+" "+data[1]+" "+data[2]+" "+data[3]+"\n");
	  }
	}
}
