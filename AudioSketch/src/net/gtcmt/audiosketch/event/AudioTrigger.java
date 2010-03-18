package net.gtcmt.audiosketch.event;

import net.gtcmt.audiosketch.sound.util.AudioControl;

/**
 * Trigger sound. add audioinfo to this class to trigger sound.
 * @author akito
 *
 */
public class AudioTrigger extends AudioSequencer {

	private static AudioTrigger audioTrigger =  null;
	private AudioControl audioCtrl;

	public AudioTrigger() {
		super(TempoClock.getTempoClock());
		this.audioCtrl = new AudioControl();
	}

	@Override
	public void sendAudioInfo(AudioInfo audioInfo){
		audioCtrl.trigger(audioInfo);
	}
	
	public void addNewAudioInfo(AudioInfo audioInfo){
		this.getAudioQueue().push(audioInfo);
	}
	
	public static void initAudioTrigger(){
		audioTrigger = new AudioTrigger();
		audioTrigger.start();
	}
	
	public static AudioTrigger getAudioTrigger(){
		if(audioTrigger == null){
			audioTrigger = new AudioTrigger();
			audioTrigger.start();
		}
		return audioTrigger;
	}
	
	/**
	 * Sub beat update
	 * @return
	 */
	public long getNextTrigTime(){
		return tempoClock.getNextTrigTime(AudioConstants.QUARTER_NOTE);
	}
}
