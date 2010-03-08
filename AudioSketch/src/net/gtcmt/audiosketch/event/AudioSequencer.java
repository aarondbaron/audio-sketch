package net.gtcmt.audiosketch.event;

import net.gtcmt.audiosketch.util.LogMessage;

/**
 * Abstract representation of client network.
 * 
 * @author akito
 * 
 */
public abstract class AudioSequencer extends Thread {
	
	// Indicator for client running state
	protected boolean isRunning=false; 
	// Queues messages to be sent to server
	protected AudioPriorityQueue audioQueue;
	protected TempoClock tempoClock;			//Clock source for timing
	private AudioInfo audioInfo;
	
	public AudioSequencer(TempoClock tempoClock) {
		this.tempoClock = tempoClock;
		audioQueue = new AudioPriorityQueue();
	}

	/**
	 * Continuously read incoming messages and write outgoing messages
	 */
	public void run() {
		isRunning = true;
		while (isRunning) {
			if(tempoClock.isTrigOn()){
				if(audioQueue.size() > 0){
					try {
						//audio queue waits until new audioinfo is added.
						while(((audioInfo = audioQueue.pop()) != null) && (audioInfo.getTrigTime() < System.currentTimeMillis())){
							sendAudioInfo(audioInfo);
						}
					} catch (InterruptedException e) {
						LogMessage.javaErr(e);
					}
				}

				tempoClock.setTrigOn(false);
				//Put back the one that did not get sent
				if(audioInfo != null){
					audioQueue.push(audioInfo);
				}
			}
		}
	}

	/**
	 * Shuts down the sequencer by stopping thread
	 */
	public void shutdown() {
		isRunning = false;
		interrupt();
	}

	/*-------------- abstract methods -------------*/
	/**
	 * send audio information
	 * 
	 * @param stdIn
	 */
	protected abstract void sendAudioInfo(AudioInfo audioInfo);
	/*-------------- getter/setter methods -------------*/
	public boolean isRunning() {
		return isRunning;
	}

	public AudioPriorityQueue getAudioQueue() {
		return this.audioQueue;
	}
}