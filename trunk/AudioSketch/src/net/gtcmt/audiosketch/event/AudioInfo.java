package net.gtcmt.audiosketch.event;


/**
 * Contains Audio playback related information such as OSC and trigTime 
 * @author akito
 *
 */
public class AudioInfo {

	private long trigTime;
	private String soundID;
	private float playSpeedMultiply;
	
	public AudioInfo(long trigTime, String soundID, float playSpeedMultiply){
		this.trigTime = trigTime;
		this.soundID = soundID;
		this.playSpeedMultiply = playSpeedMultiply;
	}
	
	public long getTrigTime() {
		return trigTime;
	}

	public String getSoundID() {
		return soundID;
	}
	
	public float getPlaySpeedMultiply() {
		return playSpeedMultiply;
	}
}
