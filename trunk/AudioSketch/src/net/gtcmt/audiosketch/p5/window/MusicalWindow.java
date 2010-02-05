package net.gtcmt.audiosketch.p5.window;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Random;

import net.gtcmt.audiosketch.gui.client.AudioSketchMainFrame;
import net.gtcmt.audiosketch.gui.util.GUIConstants;
import net.gtcmt.audiosketch.network.client.Client;
import net.gtcmt.audiosketch.network.data.AudioEffectData;
import net.gtcmt.audiosketch.network.data.AudioSketchData;
import net.gtcmt.audiosketch.network.data.PlaybackData;
import net.gtcmt.audiosketch.network.data.RelocationData;
import net.gtcmt.audiosketch.network.data.SoundObjectData;
import net.gtcmt.audiosketch.network.data.UserData;
import net.gtcmt.audiosketch.network.util.MsgType;
import net.gtcmt.audiosketch.p5.object.EffectBox;
import net.gtcmt.audiosketch.p5.object.ObjectAction;
import net.gtcmt.audiosketch.p5.object.PlayBackBar;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Size2D;
import net.gtcmt.audiosketch.p5.util.SoundObject;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Constants.ObjectColorType;
import net.gtcmt.audiosketch.p5.util.P5Constants.ObjectShapeType;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import net.gtcmt.audiosketch.sound.util.SndConstants;
import net.gtcmt.audiosketch.sound.util.SndConstants.EffectType;
import net.gtcmt.audiosketch.sound.util.SndConstants.SndType;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import ddf.minim.EffectsChain;
import ddf.minim.Minim;

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
	private boolean mouseClicked=false;
	private boolean mouseReleased=false;
	private boolean mouseDragged=false;
	private int xPos=0;
	private int yPos=0;
	private LinkedList<SoundObject> soundObject;
	private LinkedList<PlayBackBar> playBackBar;		
	private ObjectAction action;
	private LinkedList<String> players;
	private LinkedList<Integer> playerID;
	private LinkedList<Integer> playerTimeOut;
	private LinkedList<String> users;
	private LinkedList<Integer> nameID;
	private LinkedList<String> effUsers;
	private LinkedList<Integer> effNameID;
	private LinkedList<Integer> effTimeOut;
	private LinkedList<EffectBox> effectBox;
	private int[] shuffle;
	private PFont font;
	private Random rgen = new Random();
	private int incr=0;	
	private EffectType effType;
	private Minim minim;
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
	public MusicalWindow(AudioSketchMainFrame mainFrame) throws UnknownHostException, IOException, InterruptedException {
		this.mainFrame = mainFrame;
		
		//set initial effect type
		effType=EffectType.REVERB;

		//Used for thread synchronization
		lockObject = new Object();

		//Initialize collections
		soundObject = new LinkedList<SoundObject>();
		playBackBar = new LinkedList<PlayBackBar>();
		players = new LinkedList<String>();
		playerID = new LinkedList<Integer>();
		playerTimeOut = new LinkedList<Integer>();
		users = new LinkedList<String>();
		nameID = new LinkedList<Integer>();
		effUsers = new LinkedList<String>();
		effNameID = new LinkedList<Integer>();
		effTimeOut = new LinkedList<Integer>();
		effectBox = new LinkedList<EffectBox>();
		shuffle = new int[SndConstants.NUM_EFFECT];
		shuffleEffect();
	}

	/*----------------------- Main Processing methods -----------------------------*/
	/**
	 * Set up condition for drawing
	 */
	public void setup() {
		size(GUIConstants.WINDOW_WIDTH, GUIConstants.WINDOW_HEIGHT);
		smooth();
		action = new ObjectAction(soundObject, this);
		font = this.createFont("skia", 14);
		minim = new Minim(this);
	}

	/**
	 * Main method where p5 is active
	 */
	public void draw() {
		synchronized (lockObject) {
			background(0);
			drawSoundObject();
			drawEffectBox();
			playBar();
			playMode();
			editMode();
			effectMode();
		}
	}

	public void stop() {
		minim.stop();
		super.stop();
	}

	/*----------------------- Methods that correspond to server message -----------------------------*/
	/**
	 * Checks the message received from server and decides whether 
	 * to add new SoundObject in LinkedList.
	 * @param data data received from server in string format
	 */
	public synchronized void addSoundObject(SoundObjectData data){
		//Add new soundObject to Musical Window
		if(data != null){
			add(data.getObjPos(), data.getObjSize(),data.getColorType(), data.getShapeType(), data.getMidiNote(), data.getSndType());
		}
	}

	/**
	 * Adds new SoundObject in LinkedList
	 * @param x Location on X-axis where soundObject will reside
	 * @param y Location on Y-axis where soundObject will reside
	 * @param vertices	Vertices that will be plotted
	 * @param soundType type of sound this soundObject will play
	 */
	private synchronized void add(P5Points2D points, P5Size2D size, ObjectColorType color, ObjectShapeType shape, int midiNote, SndType sndType){
		soundObject.add(new SoundObject(points, size, color, shape, midiNote, sndType, minim, this));
		action.addActionObject(soundObject.getLast());
		addEffects(soundObject, effectBox);
	}

	/**
	 * Adds new Effects for newly added sound object
	 * @param soundObject
	 * @param effectBox
	 */
	private synchronized void addEffects(LinkedList<SoundObject>soundObject, LinkedList<EffectBox> effectBox){
		EffectsChain chain = new EffectsChain();
		for(int i=0;i<effectBox.size();i++)
			if(effectBox.get(i).bound(soundObject.getLast()))	
				chain.add(effectBox.get(i).effect());
		if(chain.size() > 0)
			soundObject.getLast().getAudioOut().addEffect(chain);
	}

	/**
	 * Removes soundObject and related action listener
	 */
	public synchronized void remove(){
		if(soundObject.size() > 0){
			action.removeMouseEvent(soundObject.size()-1);
			soundObject.removeLast();	
			for(int i=0;i<playBackBar.size();i++){
				playBackBar.get(i).setNumObject(playBackBar.get(i).getNumObject()-1);
			}
		}
	}
	/*----------------------- SoundObject drawing -----------------------------*/
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
		displayName();
		timeOutName();
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
				float speed = (float) Math.sqrt(Math.pow(mouseX-xPos, 2)+Math.pow(mouseY-yPos, 2));
				float angle = (float) Math.atan2(mouseY-yPos, mouseX-xPos);

				getClient().getOutQueue().push(new AudioSketchData(MsgType.PLAY_BAR, new PlaybackData(PlayBackType.values()[getPBIndex()], 
						new P5Points2D(mouseX, mouseY), speed, angle), mainFrame.getUserName(), playBackBar.size()));

				mouseReleased = false;
				mouseDragged = false;
			}
		}
	}

	/**
	 * Adds play back bar upon receiving message from server
	 * @param data data sent from server
	 */
	public synchronized void addPlayBackBar(PlaybackData data, UserData userData) {
		if(data != null){
			playBackBar.add(new PlayBackBar(data.getMousePoints(), data.getPlaybackSpeed(), data.getAngle(), 
					data.getPlaybackType(), soundObject, this));
			addName(userData);
		}
	}

	/**
	 * Draws and animates play back bar. 
	 * It also handles collision detection and removing of play back bar.
	 */
	private synchronized void playBar(){
		for(int i=0;i<playBackBar.size();i++){
			playBackBar.get(i).draw();
			switch(playBackBar.get(i).getPlaybackType()){
			case RADIAL:
				playBackBar.get(i).collideCircle();
				if(playBackBar.get(i).getWidth() > this.width*2 && playBackBar.get(i).getWidth() > this.height*2){
					playBackBar.remove(i);
				}
				break;
			case RADIAL2:
				playBackBar.get(i).collideCircle();
				if(playBackBar.get(i).getWidth() > this.width/2 && playBackBar.get(i).getWidth() > this.height/2){
					//playBackBar.remove(i);
					//int x  = playBackBar.get(i).getInitX();
					//int y = playBackBar.get(i).getInitY();
					//playBackBar.get(i).setPosX(x);
					//playBackBar.get(i).setPosX(y);
					playBackBar.get(i).setSize(0, 0); // reset the circle
					Boolean b = false;
					playBackBar.get(i).setTrigState(i, b);// i put this here so it will change the trigger state object, when it resets in the previous line
					
					//playBackBar.get(i).setWidth(0);
					//playBackBar.get(i).setHeight(0);
				}
				break;
				
			case BAR2:
				playBackBar.get(i).collideBar();
				if((playBackBar.get(i).getPosX() < 0 && playBackBar.get(i).getPosY() < 0) 
						|| (playBackBar.get(i).getPosX() < 0 && playBackBar.get(i).getPosY() > this.height) 
						|| (playBackBar.get(i).getPosX() > this.width && playBackBar.get(i).getPosY() < 0) 
						|| (playBackBar.get(i).getPosX() > this.width && playBackBar.get(i).getPosY() > this.height)
						|| (playBackBar.get(i).getPosX() < -(P5Constants.BAR_WIDTH)) || (playBackBar.get(i).getPosX() > this.width+P5Constants.BAR_WIDTH)
						|| (playBackBar.get(i).getPosY() < -(P5Constants.BAR_WIDTH)) || (playBackBar.get(i).getPosY() > this.height+P5Constants.BAR_WIDTH)) {
					//playBackBar.remove(i);
					int x = playBackBar.get(i).getInitX();
					int y = playBackBar.get(i).getInitY();
					playBackBar.get(i).setPosX(0);
					playBackBar.get(i).setPosY(0);
					
					//Boolean b = false;
					//playBackBar.get(i).setTrigState(i, b);// i put this here so it will change the trigger state object, when it resets in the previous line
					
					System.out.println("x and y : " + x + "," + y);
					//playBackBar.get(i).setSize(0, 0);
				}
				break;
			case BAR:
				playBackBar.get(i).collideBar();
				if((playBackBar.get(i).getPosX() < 0 && playBackBar.get(i).getPosY() < 0) 
						|| (playBackBar.get(i).getPosX() < 0 && playBackBar.get(i).getPosY() > this.height) 
						|| (playBackBar.get(i).getPosX() > this.width && playBackBar.get(i).getPosY() < 0) 
						|| (playBackBar.get(i).getPosX() > this.width && playBackBar.get(i).getPosY() > this.height)
						|| (playBackBar.get(i).getPosX() < -(P5Constants.BAR_WIDTH)) || (playBackBar.get(i).getPosX() > this.width+P5Constants.BAR_WIDTH)
						|| (playBackBar.get(i).getPosY() < -(P5Constants.BAR_WIDTH)) || (playBackBar.get(i).getPosY() > this.height+P5Constants.BAR_WIDTH)) {
					playBackBar.remove(i);
				}
			}
		}
	}

	/**
	 * Adds name of the user and play back bar id
	 * @param name name of user
	 * @param id id of play back bar
	 */
	private synchronized void addName(UserData data){
		players.add(data.getUserName());
		playerID.add(data.getUserID());
		playerTimeOut.add(this.millis());
	}

	/**
	 * displays user name who triggered play back bar
	 */
	private synchronized void displayName(){
		for(int i=0;i<playerID.size();i++){
			for(int j=0;j<playBackBar.size();j++){
				if(playerID.get(i) == j){
					this.textFont(font);
					this.fill(255, 255, 255, 200);
					text(players.get(i), playBackBar.get(j).getInitX(), playBackBar.get(j).getInitY());
				}
			}	
		}
	}

	/**
	 * removes name of user who triggered play back bar
	 */
	private synchronized void timeOutName(){
		for(int i=0;i<playerID.size();i++){
			if(this.millis() - playerTimeOut.get(i) > 2000){
				players.remove(i);
				playerID.remove(i);
				playerTimeOut.remove(i);
			}
		}
	}
	/*----------------------- Edit Mode -----------------------------*/
	/**
	 * Actions related to editing position of soundObjects are 
	 * put into this method.
	 */
	private synchronized void editMode(){
		drawName();
		removeName();
		if(mainFrame.getActionPanel().getEditButton().isSelected()){
			sendName();
			action.detectObjectArea();
			action.mousePressed();
			action.mouseDragged();
			action.mouseReleased();
			if(mouseReleased)
				mouseReleased = false;
			if(mouseDragged)
				mouseDragged = false;
			//			if(clicked)
			//				clicked = false;
		}
	}

	/**
	 * Moves soundObject upon receiving message from server
	 * @param data data sent from server
	 */
	public synchronized void moveObject(RelocationData data){
		if(data != null){
			soundObject.get(data.getObjectIndex()).setPosX(data.getPosX());
			soundObject.get(data.getObjectIndex()).setPosY(data.getPosY());
			reInsertEffect(data.getObjectIndex());
		}
	}

	/**
	 * Sends name of the user who is moving the ball to the server
	 */
	//TODO think about sending name
	private synchronized void sendName(){
/*		for(int i=0;i<soundObject.size();i++){
			if(mouseClicked){
				if(action.getMousePressed().get(i)) {
					getClient().write(MsgType.USER_NAME.toString()+AudioSketchProtocol.SPLITTER+
							mainFrame.getUserName()+AudioSketchProtocol.SPLITTER+i+AudioSketchProtocol.TERMINATOR);
					mouseClicked = false;
				}
			}
		}
*/	}

	/**
	 * Adds name to linked list that was returned from server
	 * @param data
	 */
	//TODO can we remove this?
/*	private synchronized void addName(String[] data){
		if(data.length > 2){
			users.add(data[1]);
			nameID.add(Integer.parseInt(data[2]));
		}
	}
*/
	/**
	 * Draws name on the musical window if any of the users are dragging the soundObject
	 */
	private synchronized void drawName(){
		for(int i=0;i<nameID.size();i++){
			for(int j=0;j<soundObject.size();j++){
				if(nameID.get(i) == j) {
					this.textFont(font);
					this.fill(255, 255, 255, 200);
					if(soundObject.get(j).getPosX()+(soundObject.get(j).getWidth()/2) > this.width-80)
						text(users.get(i), this.width-80, soundObject.get(j).getPosY()-(soundObject.get(j).getHeight()/2));
					else if(soundObject.get(j).getPosY()-(soundObject.get(j).getHeight()/2) < 20)
						text(users.get(i), soundObject.get(j).getPosX()+(soundObject.get(j).getWidth()/2), 20);
					else
						text(users.get(i), soundObject.get(j).getPosX()+(soundObject.get(j).getWidth()/2), soundObject.get(j).getPosY()-(soundObject.get(j).getHeight()/2));
				}		
			}
		}
	}

	/**
	 * Removes name of the user when user finishes moving the soundObject.
	 * It also reinserts the effect chain for the play back.
	 */
	private synchronized void removeName(){
		for(int i=nameID.size()-1;i>=0;i--){
			for(int j=0;j<soundObject.size();j++){
				if(nameID.get(i) == j) {
					if(!action.getMousePressed().get(j)){
						reInsertEffect(i);
						users.remove(i);
						nameID.remove(i);
						break;
					}
				}
			}	
		}
	}

	/**
	 * Reinserts effect into specified soundObject
	 * @param id id of soundObject in LinkedList
	 */
	private synchronized void reInsertEffect(int id){
		EffectsChain chain = new EffectsChain();
		for(int i=0;i<soundObject.get(id).getAudioOut().effectCount();i++)
			soundObject.get(id).getAudioOut().removeEffect(i);
		for(int i=0;i<effectBox.size();i++)
			if(effectBox.get(i).bound(soundObject.get(id)))
				chain.add(effectBox.get(i).effect());
		if(chain.size() > 0)
			soundObject.get(id).getAudioOut().addEffect(chain);
	}
	/*----------------------- Effect Mode -----------------------------*/
	/**
	 * Allows users to draw effect box when effectButton is selected
	 */
	private synchronized void effectMode(){
		if(mainFrame.getActionPanel().effectButton.isSelected()){
			drawPrevBoxThenSend();	
		}
		displayEffName();
		timeOutEffName();
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
			
			//Broadcast message
			getClient().getOutQueue().push(new AudioSketchData(MsgType.EFFECT_BOX, new AudioEffectData(effType, new P5Points2D(xPos, yPos), 
					new P5Size2D(mouseX-xPos, mouseY-yPos)), mainFrame.getUserName(), effectBox.size()));
			
			mouseReleased = false;
			mouseDragged = false;
		}
	}

	/**
	 * Draws and maintains effect box on musical window
	 */
	private synchronized void drawEffectBox(){
		for(int i=0;i<effectBox.size();i++)
			effectBox.get(i).draw();
	}

	/**
	 * Adds effect box upon receiving message from server
	 * @param data data sent from server
	 */
	public synchronized void addEffectBox(AudioEffectData data, UserData userData){
		if(data != null){
			effectBox.add(new EffectBox(data.getBoxPos(),data.getBoxSize(),data.getEffType(),this));
			addEffName(userData);
			for(int i=0;i<soundObject.size();i++)
				reInsertEffect(i);
		}
	}

	/**
	 * removes effect Box from musical window
	 */
	public synchronized void removeEffectBox(){
		effectBox.removeLast();
		for(int i=0;i<soundObject.size();i++)
			reInsertEffect(i);
	}

	/**
	 * Adds name of the user who added effect box in musical window
	 * @param name name of the user
	 * @param id id of effect box. Usually the last element in the linked list
	 */
	private synchronized void addEffName(UserData data){
		effUsers.add(data.getUserName());
		effNameID.add(data.getUserID());
		effTimeOut.add(this.millis());
	}

	/**
	 * draws name of user who added effect box in musical window
	 */
	private synchronized void displayEffName(){
		for(int i=0;i<effNameID.size();i++){
			for(int j=0;j<effectBox.size();j++){
				if(effNameID.get(i) == j){
					this.textFont(font);
					this.fill(255, 255, 255, 200);
					text(effUsers.get(i), effectBox.get(j).getPosX()+5, effectBox.get(j).getPosY()+ effectBox.get(j).getHeight() - 20);
				}
			}	
		}
	}

	/**
	 * Removes name of user after displaying 2 second 
	 */
	private synchronized void timeOutEffName(){
		for(int i=0;i<effNameID.size();i++){
			if(this.millis() - effTimeOut.get(i) > 2000){
				effUsers.remove(i);
				effNameID.remove(i);
				effTimeOut.remove(i);
			}
		}
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
	
	/*---------------------- Key Action -----------------------------*/
	@Override
	public void keyReleased() {
		soundObject.get(0).play();
		soundObject.get(0).setCollide(true);
		soundObject.get(0).setGetFrame(true);
		soundObject.get(0).startTime = millis();
		super.keyReleased();
	}
	
	@Override
	public void keyPressed() {
		soundObject.get(0).play();
		soundObject.get(0).setCollide(true);
		soundObject.get(0).setGetFrame(true);
		soundObject.get(0).startTime = millis();
		super.keyPressed();
		
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
	
	public Client getClient(){
		return mainFrame.getClient();
	}
	
	public int getPBIndex(){
		return mainFrame.getActionPanel().getBarMode().getSelectedIndex();
	}
	
	public String getUserName(){
		return mainFrame.getUserName();
	}
}
