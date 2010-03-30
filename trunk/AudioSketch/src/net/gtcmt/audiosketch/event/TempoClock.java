package net.gtcmt.audiosketch.event;

public class TempoClock extends Thread {

	private static TempoClock tempoClock = null;
	private static final long THRESH_TIME = 50;
	private long appStartTime;						//Time when the application start in millisecond
	private int tempo;								//Tempo in bpm
	private int shortestNote;						//the shortest note of all notes
	private int beatPerMeasure;
	private int beatUnit;
	private int beatPerHyperMeasure;
	public boolean trigOn;
	private boolean isRunning;
	private long trigTime;
	
	/**
	 * TempoClock currently takes place in two parts: jerk detection and soundObject trigger.
	 */
	public TempoClock(){
		this.appStartTime = System.currentTimeMillis();
		this.tempo = 100;
		this.shortestNote = AudioConstants.HUNDRED_TWENTY_EIGHTH;	
		this.beatPerMeasure = 4;
		this.beatPerHyperMeasure = 4;	
		this.beatUnit = AudioConstants.QUARTER_NOTE;
		isRunning = false;
		trigTime = getNextTrigTime(AudioConstants.SIXTEENTH_NOTE);
	}

	public static double bpm2bps(int bpm) {
		return  (double) bpm / 60.0;
	}
	
	public static long bps2millisec(double bps){
		return (long) ((1.0 / bps) * 1000.0);
	}
	
	/**
	 * Fine grain trigger time calculator. based on sixteenth note
	 * @return
	 */
	public long getNextTrigTime(int note) {
		// get beat length
		long beatLengthInMs = bps2millisec(bpm2bps(tempo))/note;
		long elapsedTime = System.currentTimeMillis() - appStartTime;
		long nextBeatStartTime = elapsedTime - (elapsedTime % beatLengthInMs) + beatLengthInMs + appStartTime;

		return nextBeatStartTime;
	}
	
	/**
	 * Core of the clock
	 */
	public void run() {
		isRunning = true;
		while(isRunning){
			if(System.currentTimeMillis() > trigTime){
				trigOn = true;
				trigTime = getNextTrigTime(shortestNote);
			}
		}
	}

	public synchronized boolean isTrigOn(){
		return trigOn;
	}
	
	public void shutdown(){
		isRunning = false;
		interrupt();
	}
	
	/**
	 * Gets next event trigger time based on the previous event duration
	 * @param dur beat time
	 * @return
	 */
	public long getNextTrigTime(int dur, long startTrigTime) {
		long beatLengthInMs = bps2millisec(bpm2bps(tempo));
		return (long)(((double) dur * 4.0 / shortestNote) * beatLengthInMs + startTrigTime);
	}
	
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	
	public int getCurrentBeat() {
		long beatLengthInMs = bps2millisec(bpm2bps(tempo));
		long elapsedTime = System.currentTimeMillis() - appStartTime;
		return ((int) (elapsedTime % (beatLengthInMs * beatPerMeasure)) / (int) beatLengthInMs) + 1;
	}
	
	public int getCurrentMeasure() {
		return (int) ((System.currentTimeMillis() - appStartTime) 
				/ (bps2millisec(bpm2bps(tempo)) * beatPerMeasure));		
	}

	public long getCurrentMeasureInMillis() {
		return getCurrentMeasure()*beatPerMeasure*bps2millisec(bpm2bps(tempo));
	}
	
	public boolean checkBeatStartTime() {
		long beatLengthInMs = bps2millisec(bpm2bps(tempo));
		long elapsedTime = System.currentTimeMillis() - appStartTime;
		long nextBeatStartTime = elapsedTime - (elapsedTime % beatLengthInMs) + beatLengthInMs + appStartTime;
		if ((nextBeatStartTime - System.currentTimeMillis()) < THRESH_TIME){
			return true;
		}
		else {
			return false;
		}
	}
	
	public void setBeatPerHyperMeasure(int beatPerHyperMeasure) {
		this.beatPerHyperMeasure = beatPerHyperMeasure;
	}
	
	public static void initTempoClock(){
		tempoClock = new TempoClock();
		tempoClock.start();
	}
	
	public static TempoClock getTempoClock(){
		if(tempoClock == null){
			tempoClock = new TempoClock();
		}
		return tempoClock;
	}
	
	/*---------------- Getter/Setter methods --------------*/
	public int getBeatUnit() {
		return beatUnit;
	}

	public void setBeatUnit(int beatUnit) {
		this.beatUnit = beatUnit;
	}
	
	public int getBeatPerMeasure() {
		return beatPerMeasure;
	}

	public void setBeatPerMeasure(int beatPerMeasure) {
		this.beatPerMeasure = beatPerMeasure;
	}
	
	public int getBeatPerHyperMeasure() {
		return beatPerHyperMeasure;
	}
	
	public int getTempo() {
		return tempo;
	}
	
	public int getShortestNote() {
		return shortestNote;
	}

	public void setShortestNote(int shortestNote) {
		this.shortestNote = shortestNote;
	}
	
	public long getAppStartTime() {
		return appStartTime;
	}
	
	public void setAppStartTime(long appStartTime) {
		this.appStartTime = appStartTime;
	}

	public void setTrigOn(boolean b) {
		this.trigOn = b;
	}
}
