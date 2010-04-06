package net.gtcmt.audiosketch.sound.util;

import net.gtcmt.audiosketch.event.AudioInfo;
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

	private static final String OUT_IP = "localhost";
	private static final int IN_PORT = 57849;
	private static final int OUT_PORT = 57850;
	public static final int TRIG_ON = 1;
	public static final int TRIG_OFF = 0;
	
	/**
	 * Constructor: out ip addr is set to localhost
	 * in port is 57849
	 * out port is 57848
	 */
	public AudioControl(){
		/* start oscP5, listening for incoming messages at port inPort */
		oscP5 = new OscP5(this,IN_PORT);
		remoteLocation = new NetAddress(OUT_IP,OUT_PORT);
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
	
	public void trigger(AudioInfo audioInfo) {
		OscMessage oscMsg = new OscMessage("/trigger");
		oscMsg.add(audioInfo.getSoundID());
		oscMsg.add(audioInfo.getPlaySpeedMultiply());
		
		/* send the message */
		 oscP5.send(oscMsg, remoteLocation);
	}

	public void sendSoundFile(String fileName) {
		OscMessage oscMsg = new OscMessage("/file");
		oscMsg.add(fileName);
		double randy=Math.random();
		
		oscMsg.add(Double.toString(randy));
		//oscMsg.add(midi);
		
		/* send the message */
		 oscP5.send(oscMsg, remoteLocation);
	}
	
	/* incoming osc message are forwarded to the oscEvent method. */
	public void oscEvent(OscMessage theOscMessage) {
	  System.out.println(theOscMessage.toString());
	  this.haveReceivedMsg=true;
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
	
	
