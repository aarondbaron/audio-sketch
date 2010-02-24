package net.gtcmt.audiosketch.p5.window;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Random;

import net.gtcmt.audiosketch.gui.client.AudioSketchMainFrame;
import net.gtcmt.audiosketch.gui.util.GUIConstants;
import net.gtcmt.audiosketch.p5.action.MouseAction;
import net.gtcmt.audiosketch.p5.object.EffectBox;
import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.object.playbar.Bar;
import net.gtcmt.audiosketch.p5.object.playbar.Bar2;
import net.gtcmt.audiosketch.p5.object.playbar.ClockBar;
import net.gtcmt.audiosketch.p5.object.playbar.PlayBackBar;
import net.gtcmt.audiosketch.p5.object.playbar.Radial2Bar;
import net.gtcmt.audiosketch.p5.object.playbar.RadialBar;
import net.gtcmt.audiosketch.p5.object.playbar.SquareBar;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Size2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.ObjectColorType;
//import net.gtcmt.audiosketch.p5.util.P5Constants.ObjectShapeType;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import net.gtcmt.audiosketch.sound.util.SndConstants;
import net.gtcmt.audiosketch.sound.util.SndConstants.EffectType;
import net.gtcmt.audiosketch.sound.util.SndConstants.SndType;
import processing.core.PApplet;
import processing.core.PConstants;

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
	private boolean mouseClicked;
	private boolean mouseReleased;
	private boolean mouseDragged;
	private int xPos;
	private int yPos;
	private LinkedList<SoundObject> soundObject;
	private LinkedList<PlayBackBar> playBackBar;		
	private MouseAction action;
	private LinkedList<EffectBox> effectBox; 
	private int[] shuffle;
	private Random rgen;
	private int incr=0;	
	private EffectType effType;
	private Object lockObject;
	private AudioSketchMainFrame mainFrame;
	
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
		effectBox = new LinkedList<EffectBox>();
		shuffle = new int[SndConstants.NUM_EFFECT];
		
		mouseClicked=false;
		mouseReleased=false;
		mouseDragged=false;
		xPos=0;
		yPos=0;
		rgen = new Random();
		
		shuffleEffect();
	}

	/*----------------------- Main Processing methods -----------------------------*/
	/**
	 * Set up condition for drawing
	 */
	public void setup() {
		size(GUIConstants.WINDOW_WIDTH, GUIConstants.WINDOW_HEIGHT);
		smooth();
		action = new MouseAction(soundObject, this);
	}

	/**
	 * Main method where p5 is active
	 */
	public void draw() {
		background(0);
		
		synchronized (lockObject) {
			drawSoundObject();
			drawEffectBox();
			playBar();
			playMode();
			editMode();
			effectMode();
		}
	}

	/*----------------------- SoundObject methods -----------------------------*/
	public void addSoundObject(int shape,ObjectColorType color, SndType sndType, P5Points2D objPos, P5Size2D objSize, int midiNote) {
		soundObject.add(new SoundObject(objPos, objSize, color, shape, midiNote, sndType, this));
		action.addActionObject(soundObject.getLast());
		//TODO before adding playback bar check collision state and pass in appropriate boolean
		for(int i=0;i<playBackBar.size();i++) {
			soundObject.getLast().putCollideState(playBackBar.get(i), false);
		}
	}

	/**
	 * Removes soundObject and related action listener
	 */
	public synchronized void remove() {
		if(soundObject.size() > 0){
			action.removeMouseEvent(soundObject.size()-1);
			soundObject.removeLast();	
		}
	}
	
	/**
	 * Handles drawing object in p5 window
	 */
	private synchronized void drawSoundObject(){
		for(int i=0; i<soundObject.size();i++){
			soundObject.get(i).draw(this);
		}
	}
	
	/*----------------------- PlayBack Mode -----------------------------*/
	/**
	 * Allows user to trigger play back bar when playButton is selected
	 */
	private synchronized void playMode(){
		if(mainFrame.getActionPanel().getPlayButton().isSelected()) {
			trigPlayBackBar();
		}
	}

	/**
	 * Sends play back info to server when user clicks and releases mouse.
	 */
	private synchronized void trigPlayBackBar(){
		if(playBackBar.size() <= PlayBackBar.MAX_TRIG){
			if(mouseClicked){ //Store mouse click position
				xPos = mouseX;
				yPos = mouseY;
				mouseClicked = false;
			}

			if(mouseReleased){
				if(mouseX - xPos == 0 && mouseY - yPos == 0){
					xPos -= 1;
				}
				//Calculate speed and angle from mouse actions
				float speed = (float) Math.sqrt(Math.pow(mouseX-xPos, 2)+Math.pow(mouseY-yPos, 2))/P5Constants.MAX_TRIG_DISTANCE;
				float angle = (float) Math.atan2(mouseY-yPos, mouseX-xPos);

				addPlayBackBar(PlayBackType.values()[getPlayBarIndex()], new P5Points2D(xPos, yPos), speed, angle);
				mouseReleased = false;
				mouseDragged = false;
			}
		}
	}

	/**
	 * Adds play back bar upon receiving message from server
	 * @param data data sent from server
	 */
	public synchronized void addPlayBackBar(PlayBackType playType, P5Points2D mousePnt,  float speed, float angle) {
		playBackBar.add(PlayBackBar.createPlayBar(playType, mousePnt, speed, angle, this));
		for(int j=0;j<soundObject.size();j++){
			soundObject.get(j).putCollideState(playBackBar.getLast(), false);
		}
	}

	/**
	 * Draws and animates play back bar. 
	 * It also handles collision detection and removing of play back bar.
	 */
	private synchronized void playBar(){
		//Go through each play bar
		for(int i=0;i<playBackBar.size();i++){			
			playBackBar.get(i).draw();
			if(playBackBar.get(i).checkState(soundObject)){
				playBackBar.remove(i);
			}
		}
	}

	/*----------------------- Edit Mode -----------------------------*/
	/**
	 * Actions related to editing position of soundObjects are 
	 * put into this method.
	 */
	private synchronized void editMode(){
		if(mainFrame.getActionPanel().getEditButton().isSelected()){
			action.detectObjectArea();
			action.mousePressed();
			action.mouseDragged();
			action.mouseReleased();
			if(mouseReleased)
				mouseReleased = false;
			if(mouseDragged)
				mouseDragged = false;
		}
	}
	
	public synchronized void moveObject(int index, int posX, int posY){
		soundObject.get(index).setPos(posX,posY);
	}

	/*----------------------- Effect Mode -----------------------------*/
	/**
	 * Allows users to draw effect box when effectButton is selected
	 */
	private synchronized void effectMode(){
		if(mainFrame.getActionPanel().effectButton.isSelected()){
			drawPrevBoxThenSend();	
		}
	}

	/**
	 * Draws and maintains effect box on musical window
	 */
	private synchronized void drawEffectBox(){
		for(int i=0;i<effectBox.size();i++){
			effectBox.get(i).draw();
		}
	}
	
	/**
	 * Draws Box according to mouse action
	 * Only the drawer of the box can see this.
	 */
	private synchronized void drawPrevBoxThenSend(){
		//TODO combine this if statement to one method
		if(mouseClicked){ //Get mouse click position
			xPos = mouseX;
			yPos = mouseY;
			mouseClicked = false;
		}
		if(mouseDragged){
			this.stroke(255, 255, 255, 200);
			this.noFill();
			this.rectMode(PConstants.CORNER);
			this.rect(xPos, yPos, mouseX-xPos, mouseY-yPos);
		}
		if(mouseReleased){
			if(mouseX - xPos == 0 || mouseY - yPos == 0){
				xPos += 1; yPos +=1;
			}
			//Shuffle effect
			//TODO instead of shuffling. Can we come up with something else?
			if(incr > SndConstants.NUM_EFFECT){
				shuffleEffect();
				incr = 0;
			}
			else{
				effType = EffectType.values()[shuffle[incr++]];
			}
			
			addEffectBox(effType, new P5Points2D(xPos, yPos), new P5Size2D(mouseX-xPos, mouseY-yPos));
			
			mouseReleased = false;
			mouseDragged = false;
		}
	}

	/**
	 * Adds effect box upon receiving message from server
	 * @param data data sent from server
	 */
	public synchronized void addEffectBox(EffectType effType, P5Points2D pnts, P5Size2D size){
		effectBox.add(new EffectBox(pnts, size, effType, this));
	}

	/**
	 * removes effect Box from musical window
	 */
	public synchronized void removeEffectBox(){
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
	/*----------------------- Mouse Action -----------------------------*/
	@Override
	public void mousePressed(MouseEvent arg0) {
		super.mousePressed(arg0);
		mouseClicked = true;
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		super.mouseReleased(arg0);
		mouseReleased = true;
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		mouseDragged = true;
		super.mouseDragged(arg0);
	}
	
	@Override
	public void mouseClicked() {

		super.mouseClicked();
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
	
	public int getPlayBarIndex(){
		return mainFrame.getActionPanel().getBarMode().getSelectedIndex();
	}
	
	public String getUserName(){
		return mainFrame.getUserName();
	}
}
