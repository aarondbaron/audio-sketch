package net.gtcmt.audiosketch.sound.util;

import netP5.NetAddress;
import oscP5.OscMessage;
import oscP5.OscP5;

/**
	 * oscP5message by andreas schlegel
	 * example shows how to create osc messages.
	 * oscP5 website at http://www.sojamo.de/oscP5
	 */

	
public class AudioControl {
	OscP5 oscP5;
	NetAddress myRemoteLocation;
	boolean haveReceivedMsg;
	
	
	AudioControl(String outIP,int thisPort,int outPort){
	/* start oscP5, listening for incoming messages at port inPort */
		
		oscP5 = new OscP5(this,thisPort);
	  
	  /* myRemoteLocation is a NetAddress. a NetAddress takes 2 parameters,
	   * an ip address and a port number. myRemoteLocation is used as parameter in
	   * oscP5.send() when sending osc packets to another computer, device, 
	   * application. usage see below. for testing purposes the listening port
	   * and the port of the remote location address are the same, hence you will
	   * send messages back to this sketch.
	   */
	  myRemoteLocation = new NetAddress(outIP,outPort);
	  
	  this.haveReceivedMsg=false;
	}

	void trigger(int onOff) {
		OscMessage myMessage = new OscMessage("/trigger");
		myMessage.add(onOff);
		System.out.println("Sending...");
		/* send the message */
		  oscP5.send(myMessage, myRemoteLocation);
		
		
	}
	
	/* incoming osc message are forwarded to the oscEvent method. */
	void oscEvent(OscMessage theOscMessage) {
	  System.out.println(theOscMessage.toString());
	  this.haveReceivedMsg=true;
	}
	

}
	
	
