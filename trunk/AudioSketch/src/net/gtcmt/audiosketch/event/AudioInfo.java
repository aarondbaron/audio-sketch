package net.gtcmt.audiosketch.event;


/**
 * Contains Audio playback related information such as OSC and trigTime 
 * @author akito
 *
 */
public class AudioInfo {

	private long trigTime;
	private String soundID;
	private int midi;
	
	public AudioInfo(long trigTime, String soundID, int midi){
		this.trigTime = trigTime;
		this.soundID = soundID;
		this.midi = midi;
	}
	
	public long getTrigTime() {
		return trigTime;
	}

	public String getSoundID() {
		return soundID;
	}
	
	public int getMidi() {
		return midi;
	}
}
