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
	private OscP5 oscP5;
	private NetAddress remoteLocation;
	private boolean haveReceivedMsg;

	private static AudioControl audioCtrl = null;
	private static final String OUT_IP = "localhost";
	private static final int IN_PORT = 57849;
	private static final int OUT_PORT = 57848;
	public static final int TRIG_ON = 1;
	public static final int TRIG_OFF = 0;
	
	public AudioControl(String outIP,int thisPort,int outPort){
	/* start oscP5, listening for incoming messages at port inPort */
		
		oscP5 = new OscP5(this,thisPort);
	  
	  /* myRemoteLocation is a NetAddress. a NetAddress takes 2 parameters,
	   * an ip address and a port number. myRemoteLocation is used as parameter in
	   * oscP5.send() when sending osc packets to another computer, device, 
	   * application. usage see below. for testing purposes the listening port
	   * and the port of the remote location address are the same, hence you will
	   * send messages back to this sketch.
	   */
	  remoteLocation = new NetAddress(outIP,outPort);
	  
	  this.haveReceivedMsg=false;
	}

	/**
	 * 
	 * @param soundID
	 * @param midi
	 */
	public void trigger(String soundID, float midi) {
		OscMessage oscMsg = new OscMessage("/trigger");
		oscMsg.add(soundID);
		oscMsg.add(midi);
		
		/* send the message */
		 oscP5.send(oscMsg, remoteLocation);
	}
	
	/* incoming osc message are forwarded to the oscEvent method. */
	public void oscEvent(OscMessage theOscMessage) {
	  System.out.println(theOscMessage.toString());
	  this.haveReceivedMsg=true;
	}
	
	public static AudioControl getAudioCtrl(){
		if(audioCtrl == null){
			audioCtrl = new AudioControl(OUT_IP, IN_PORT, OUT_PORT);
		}
		return audioCtrl;
	}
	
	/*-------------- Getter/Setter -------------*/
	public OscP5 getOscP5() {
		return oscP5;
	}

	public void setOscP5(OscP5 oscP5) {
		this.oscP5 = oscP5;
	}

	public NetAddress getMyRemoteLocation() {
		return remoteLocation;
	}

	public void setMyRemoteLocation(NetAddress myRemoteLocation) {
		this.remoteLocation = myRemoteLocation;
	}

	public boolean receivedMsg() {
		return haveReceivedMsg;
	}

	public void setReceivedMsg(boolean receivedMsg) {
		this.haveReceivedMsg = receivedMsg;
	}
}
	
	
