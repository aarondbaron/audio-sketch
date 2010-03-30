package net.gtcmt.audiosketch.p5.window;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import net.gtcmt.audiosketch.event.AudioTrigger;
import net.gtcmt.audiosketch.event.TempoClock;
import net.gtcmt.audiosketch.gui.client.AudioSketchMainFrame;
import net.gtcmt.audiosketch.gui.util.GUIConstants;
import net.gtcmt.audiosketch.p5.object.EffectBox;
import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.object.playbar.PlayBackBar;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Size2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.ObjectColorType;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import net.gtcmt.audiosketch.sound.util.SndConstants;
import net.gtcmt.audiosketch.sound.util.SndConstants.EffectType;
import net.gtcmt.audiosketch.sound.util.SndConstants.SndType;
import net.gtcmt.audiosketch.wii.IRDisplay;
import net.gtcmt.audiosketch.wii.MoteConnector;
import processing.core.PApplet;

/**
 * This is the main window where all the action user makes happen such
 * as triggering playback bar and so forth.
 * @author akito
 *
 */
//TODO there are way too many things in this class. break it down.
public class MusicalWindow extends PApplet {

	private static final long serialVersionUID = 2781100810368642853L;
	//Mouse actions
	private LinkedList<SoundObject> soundObject;
	//private int[] soundObjectIndices={-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};		//Stores the LinkedList index values for each added SoundObject
	private LinkedList<PlayBackBar> playBackBar;		
	private LinkedList<EffectBox> effectBox; 
	private int[] shuffle;
	private Random rgen;
	private int incr=0;	
	private EffectType effType;
	private Object lockObject;
	private AudioSketchMainFrame mainFrame;
	private LinkedList<IRDisplay> irDisplay;
	
	/**
	 * Constructor for MusicalWindow
	 * @param width Width of Processing window
	 * @param height Height of Processing window
	 * @param collab need this to get the messages from GUI
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws InterruptedException 
	 */
	public MusicalWindow(AudioSketchMainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		//set initial effect type
		effType=EffectType.REVERB;

		//Used for thread synchronization
		lockObject = new Object();

		//Initialize collections
		soundObject = new LinkedList<SoundObject>();
		playBackBar = new LinkedList<PlayBackBar>();
		irDisplay = new LinkedList<IRDisplay>();
		effectBox = new LinkedList<EffectBox>();
		shuffle = new int[SndConstants.NUM_EFFECT];

		rgen = new Random();
		
		shuffleEffect();

		//Initialize tempo clock
		TempoClock.initTempoClock();
		
		//Initialize audioTrigger
		AudioTrigger.initAudioTrigger();
	}

	/*----------------------- Main Processing methods -----------------------------*/
	/**
	 * Set up condition for drawing
	 */
	public void setup() {
		size(GUIConstants.WINDOW_WIDTH, GUIConstants.WINDOW_HEIGHT);
		smooth();
		this.frameRate = P5Constants.FRAME_RATE;
		//XXX For testing
		//addTableObject(1, 11, ObjectColorType.BLUE, SndType.BUZZ, new P5Points2D((this.width>>1)+200,(this.height>>1)-200), new P5Size2D(100,100), 0);
		//addTableObject(2, 10, ObjectColorType.BLUE, SndType.CHING, new P5Points2D((this.width>>1)-200,(this.height>>1)+200), new P5Size2D(100,100), 0);
		//addTableObject(3, 9, ObjectColorType.BLUE, SndType.BANJO, new P5Points2D((this.width>>1)+200,(this.height>>1)+200), new P5Size2D(100,100), 0);
		//addTableObject(4, 8, ObjectColorType.BLUE, SndType.FEMALE, new P5Points2D((this.width>>1)-200,(this.height>>1)-200), new P5Size2D(100,100), 0);
	}

	/**
	 * Main method where p5 is active
	 */
	public void draw() {
		background(0,0,0,0);
		//synchronized (lockObject) {
			playBar();
			drawSoundObject();
			drawPointer();
		//}
		//drawEffectBox();
		//effectMode();
	}
	
	/*----------------------- SoundObject methods -----------------------------*/
	/*
	public void addSoundObject(int shape,ObjectColorType color, SndType sndType, P5Points2D objPos, P5Size2D objSize, int midiNote) {
		synchronized (lockObject) {
			soundObject.add(new SoundObject(objPos, objSize, color, shape, midiNote, sndType, this));
			//TODO before adding playback bar check collision state and pass in appropriate boolean
			for(int i=0;i<playBackBar.size();i++) {
				soundObject.getLast().addCollideState(false);
			}
		}
	}
	*/

	/**
	 * 
	 */
	public void addTableObject(int id, int shape,ObjectColorType color, SndType sndType, P5Points2D objPos, P5Size2D objSize, 
			float angle, float[] playSpeedMultiply) {
		soundObject.add(new SoundObject(id, objPos, objSize, color, shape, sndType, this, angle, playSpeedMultiply));
		//			this.soundObjectIndices[id]=soundObject.size()-1;
		//TODO before adding playback bar check collision state and pass in appropriate boolean
		for(int i=0;i<playBackBar.size();i++) {
			soundObject.getLast().addCollideState(false);
		}
		//System.out.println("Length of SoundObjectLL: "+soundObject.size());

	}
	
	/**
	 * Removes soundObject and related action listener
	 */
	public void remove() {
		if(soundObject.size() > 0){
			soundObject.removeLast();	
		}	
	}

	/**
	 * 
	 */
	public void removeTableObject(int id) {
		ListIterator<SoundObject> iter=soundObject.listIterator(0);
		while(iter.hasNext()) {
			SoundObject tempSoundObject=iter.next();
			if (tempSoundObject.getId()==id) {
				//System.out.println("Were able to remove soundObject ID="+id);
				//System.out.println(""+soundObject.remove(tempSoundObject));
				soundObject.remove(tempSoundObject);
				System.out.println("Remove Object ID: " + id);
				break;
			}
		}
	}
	
	/**
	 * Handles drawing object in p5 window
	 */
	private void drawSoundObject(){
		for(int i=0; i<soundObject.size();i++){
			soundObject.get(i).draw(this);
		}
	}
	
	/*----------------------- PlayBack Mode -----------------------------*/

	/**
	 * Adds play back bar upon receiving message from server
	 * @param data data sent from server
	 */
	public void addPlayBackBar(PlayBackType playType, P5Points2D mousePnt,  float speed, float angle) {
		playBackBar.add(PlayBackBar.createPlayBar(playType, mousePnt, speed, angle, this));
		for(int j=0;j<soundObject.size();j++){
			soundObject.get(j).addCollideState(false);
		}
	}

	/**
	 * Draws and animates play back bar. 
	 * It also handles collision detection and removing of play back bar.
	 */
	private void playBar(){
		//Go through each play bar
		synchronized (lockObject) {
			for(int i=0;i<playBackBar.size();i++){			
				playBackBar.get(i).draw();
				if(playBackBar.get(i).checkState(soundObject, i)){
					playBackBar.remove(i);
				}
			}
		}	
	}

	/**
	 * Removes last playBackBar
	 */
	public void removeLastPlayBar(){
		synchronized (lockObject) {
			playBackBar.removeLast();
		}
	}
	/*----------------------- Edit Mode -----------------------------*/
	
	public void moveObject(SoundObject soundObject, int posX, int posY, float angle){
		soundObject.setPos(posX,posY);
		soundObject.setAngle(angle);
	}

	/*----------------------- Effect Mode -----------------------------*/
	/**
	 * Allows users to draw effect box when effectButton is selected
	 */
	private void effectMode(){
//		if(mainFrame.getActionPanel() != null && mainFrame.getActionPanel().effectButton.isSelected()){
//			drawPreviewEffectBox();	
//		}
	}

	/**
	 * Draws and maintains effect box on musical window
	 */
	private void drawEffectBox(){
		for(int i=0;i<effectBox.size();i++){
			effectBox.get(i).draw();
		}
	}
	
	/**
	 * Draws Box according to mouse action
	 * Only the drawer of the box can see this.
	 */
	private void drawPreviewEffectBox(){
//		if(mouseDragged){
//			this.stroke(255, 255, 255, 200);
//			this.noFill();
//			this.rectMode(PConstants.CORNER);
//			this.rect(xPos, yPos, mouseX-xPos, mouseY-yPos);
//		}	
	}

	/**
	 * Adds effect box upon receiving message from server
	 * @param data data sent from server
	 */
	public void addEffectBox(){
		if(incr > SndConstants.NUM_EFFECT) {	//Shuffle effect
			shuffleEffect();
			incr = 0;
		}
		else {
			effType = EffectType.values()[shuffle[incr++]];
		}
		
		//effectBox.add(new EffectBox(new P5Points2D(mouseX, mouseY), new P5Size2D(mouseX-xPos, mouseY-yPos), effType, this));
	}

	/**
	 * removes effect Box from musical window
	 */
	public void removeEffectBox(){
		effectBox.removeLast();
	}

	/**
	 * Shuffle effect order. 
	 */
	private synchronized void shuffleEffect(){
		for(int i=0;i<SndConstants.NUM_EFFECT;i++)
			shuffle[i]=i;

		for (int i=0; i<shuffle.length; i++) {
			int randomPosition = rgen.nextInt(shuffle.length);
			int temp = shuffle[i];
			shuffle[i] = shuffle[randomPosition];
			shuffle[randomPosition] = temp;
		}
	}
	
	/*---------------------- Wii mote stuff ----------------------------*/

	public void initPointer(LinkedList<MoteConnector> connector) {
		for(int i=0;i<connector.size();i++){
			irDisplay.add(new IRDisplay(this, connector.get(i).getMoteListener()));
		}
	}
	
	public void drawPointer(){
		if(irDisplay != null){
			for(int i=0;i<irDisplay.size();i++){
				irDisplay.get(i).draw();
			}
		}
	}

	/*------------------ Getter/Setter --------------------*/
	public Object getLockObject() {
		return lockObject;
	}

	public void setLockObject(Object lockObject) {
		this.lockObject = lockObject;
	}
	
	public AudioSketchMainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(AudioSketchMainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	public int getPlayBarSize(){
		return playBackBar.size();
	}
	
	public LinkedList<SoundObject> getSoundObject() {
		return soundObject;
	}
}
