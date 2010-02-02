package net.gtcmt.audiosketch.client.visual;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Random;

import net.gtcmt.audiosketch.client.gui.AudioSketchMainFrame;
import net.gtcmt.audiosketch.client.gui.util.GUIConstants;
import net.gtcmt.audiosketch.client.sound.util.SndConstants;
import net.gtcmt.audiosketch.client.sound.util.SndConstants.EffectType;
import net.gtcmt.audiosketch.client.sound.util.SndConstants.SndType;
import net.gtcmt.audiosketch.client.util.SoundObject;
import net.gtcmt.audiosketch.client.visual.util.VisualConstants;
import net.gtcmt.audiosketch.client.visual.util.VisualConstants.ObjectColor;
import net.gtcmt.audiosketch.client.visual.util.VisualConstants.ObjectShape;
import net.gtcmt.audiosketch.protocol.AudioSketchProtocol;
import net.gtcmt.audiosketch.protocol.AudioSketchProtocol.MsgType;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.net.Client;
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
	private String input;
	
	//Mouse actions
	private boolean clicked=false;
	private boolean released=false;
	private boolean dragged=false;

	private int xPos=0;
	private int yPos=0;

	private String[] data;							//Used for transmitting data between server
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
			read();

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
	/*----------------------- Communication -----------------------------*/
	/**
	 * Receives message from server and passes it to corresponding methods
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	private void read() {
		if(getClient() != null && getClient().available() > 0){
			input = getClient().readString();
			data = AudioSketchProtocol.createTokens(input);

			switch(MsgType.valueOf(data[0])){
			case MOVE:
				moveObject(data);
				break;
			case BAR:
				addPlayBackBar(data);
				break;
			case NAME:
				addName(data);
				break;
			case BOX:
				addEffectBox(data);
				break;
			case ADD:
				addSoundObject(data);
			}
		}
	}

	/*----------------------- Methods that correspond to server message -----------------------------*/
	/**
	 * Checks the message received from server and decides whether 
	 * to add new SoundObject in LinkedList.
	 * @param data data received from server in string format
	 */
	private void addSoundObject(String[] data){
		//Add new soundObject to Musical Window
		if(data.length > 7){
			add(Float.parseFloat(data[1]), Float.parseFloat(data[2]), Integer.parseInt(data[3]), 
					Integer.parseInt(data[4]), ObjectColor.valueOf(data[5]), 
					ObjectShape.valueOf(data[6]), Integer.parseInt(data[7]), SndType.valueOf(data[8]));
		}
	}

	/**
	 * Adds new SoundObject in LinkedList
	 * @param x Location on X-axis where soundObject will reside
	 * @param y Location on Y-axis where soundObject will reside
	 * @param vertices	Vertices that will be plotted
	 * @param soundType type of sound this soundObject will play
	 */
	private void add(float x, float y, int width, int height, ObjectColor color, ObjectShape shape, int midiNote, SndType sndType){
		soundObject.add(new SoundObject(x, y, width, height, color, shape, midiNote, sndType, minim, this));
		action.addActionObject(soundObject.getLast());
		addEffects(soundObject, effectBox);
	}

	/**
	 * Adds new Effects for newly added sound object
	 * @param soundObject
	 * @param effectBox
	 */
	private void addEffects(LinkedList<SoundObject>soundObject, LinkedList<EffectBox> effectBox){
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
	public void remove(){
		action.removeMouseEvent(soundObject.size()-1);
		soundObject.removeLast();	
		for(int i=0;i<playBackBar.size();i++)
			playBackBar.get(i).setNumObject(playBackBar.get(i).getNumObject()-1);
	}
	/*----------------------- SoundObject drawing -----------------------------*/
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
	 * Allows user to trigger play back bar when playButton is selected
	 */
	private void playMode(){
		if(mainFrame.getActionPanel().getPlayButton().isSelected()) {
			trigPlayBackBar();
		}
		displayName();
		timeOutName();
	}

	/**
	 * Sends triggered play back info to server when user clicks and releases mouse.
	 */
	private void trigPlayBackBar(){
		if(clicked){
			xPos = mouseX;
			yPos = mouseY;
			clicked = false;
		}
		if(released){
			if(mouseX - xPos == 0 && mouseY - yPos == 0)
				xPos -= 1;
			float speed = (float) Math.sqrt(Math.pow(mouseX-xPos, 2)+Math.pow(mouseY-yPos, 2));
			float angle = (float) Math.atan2(mouseY-yPos, mouseX-xPos);
			//msgtype, mousex, mousey, speed, angle, barmode, user name, size
			getClient().write(MsgType.BAR.toString()+AudioSketchProtocol.SPLITTER+mouseX+AudioSketchProtocol.SPLITTER+
					mouseY+AudioSketchProtocol.SPLITTER+speed+AudioSketchProtocol.SPLITTER+angle+AudioSketchProtocol.SPLITTER+
					mainFrame.getActionPanel().getBarMode().getSelectedIndex()+AudioSketchProtocol.SPLITTER+
					mainFrame.getUserName()+AudioSketchProtocol.SPLITTER+playBackBar.size()+AudioSketchProtocol.TERMINATOR);
			released = false;
			dragged = false;
		}
	}

	/**
	 * Adds play back bar upon receiving message from server
	 * @param data data sent from server
	 */
	private void addPlayBackBar(String[] data) {
		if(data.length == 8){
			playBackBar.add(new PlayBackBar(Integer.parseInt(data[1]), Integer.parseInt(data[2]), Float.parseFloat(data[3]), Float.parseFloat(data[4]), Integer.parseInt(data[5]), soundObject, this));
			addName(data[6], Integer.parseInt(data[7]));
		}
	}

	/**
	 * Draws and animates play back bar. 
	 * It also handles collision detection and removing of play back bar.
	 */
	private void playBar(){
		for(int i=0;i<playBackBar.size();i++){
			playBackBar.get(i).play();
			switch(playBackBar.get(i).getPlaybackType()){
			case RADIAL:
				playBackBar.get(i).collideCircle();
				if(playBackBar.get(i).getWidth() > this.width*2 && playBackBar.get(i).getWidth() > this.height*2){
					playBackBar.remove(i);
				}
				break;
			case BAR:
				playBackBar.get(i).collideBar();
				if((playBackBar.get(i).getPosX() < 0 && playBackBar.get(i).getPosY() < 0) 
						|| (playBackBar.get(i).getPosX() < 0 && playBackBar.get(i).getPosY() > this.height) 
						|| (playBackBar.get(i).getPosX() > this.width && playBackBar.get(i).getPosY() < 0) 
						|| (playBackBar.get(i).getPosX() > this.width && playBackBar.get(i).getPosY() > this.height)
						|| (playBackBar.get(i).getPosX() < -(VisualConstants.BAR_WIDTH)) || (playBackBar.get(i).getPosX() > this.width+VisualConstants.BAR_WIDTH)
						|| (playBackBar.get(i).getPosY() < -(VisualConstants.BAR_WIDTH)) || (playBackBar.get(i).getPosY() > this.height+VisualConstants.BAR_WIDTH)) {
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
	private void addName(String name, int id){
		players.add(name);
		playerID.add(id);
		playerTimeOut.add(this.millis());
	}

	/**
	 * displays user name who triggered play back bar
	 */
	private void displayName(){
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
	private void timeOutName(){
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
	private void editMode(){
		drawName();
		removeName();
		if(mainFrame.getActionPanel().getEditButton().isSelected()){
			sendName();
			action.detectObjectArea();
			action.mousePressed();
			action.mouseDragged();
			action.mouseReleased();
			if(released)
				released = false;
			if(dragged)
				dragged = false;
			//			if(clicked)
			//				clicked = false;
		}
	}

	/**
	 * Moves soundObject upon receiving message from server
	 * @param data data sent from server
	 */
	private void moveObject(String[] data){
		if(data.length > 3){
			soundObject.get(Integer.parseInt(data[1])).setPosX(Float.parseFloat(data[2]));
			soundObject.get(Integer.parseInt(data[1])).setPosY(Float.parseFloat(data[3]));
			reInsertEffect(Integer.parseInt(data[1]));
		}
	}

	/**
	 * Sends name of the user who is moving the ball to the server
	 */
	private void sendName(){
		for(int i=0;i<soundObject.size();i++){
			if(clicked){
				if(action.getMousePressed().get(i)) {
					getClient().write(MsgType.NAME.toString()+" "+mainFrame.getUserName()+" "+i+"\n");
					clicked = false;
				}
			}
		}
	}

	/**
	 * Adds name to linked list that was returned from server
	 * @param data
	 */
	private void addName(String[] data){
		if(data.length > 2){
			users.add(data[1]);
			nameID.add(Integer.parseInt(data[2]));
		}
	}

	/**
	 * Draws name on the musical window if any of the users are dragging the soundObject
	 */
	private void drawName(){
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
	private void removeName(){
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
	private void reInsertEffect(int id){
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
	private void effectMode(){
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
	private void drawPrevBoxThenSend(){
		if(clicked){
			xPos = mouseX;
			yPos = mouseY;
			clicked = false;
		}
		if(dragged){
			this.stroke(255, 255, 255, 200);
			this.noFill();
			this.rectMode(PConstants.CORNER);
			this.rect(xPos, yPos, mouseX-xPos, mouseY-yPos);
		}
		if(released){
			if(mouseX - xPos == 0 || mouseY - yPos == 0){
				xPos += 1; yPos +=1;
			}
			if(incr > SndConstants.NUM_EFFECT){
				shuffleEffect();
				incr = 0;
			}
			else
				//TODO
				effType = EffectType.values()[shuffle[incr++]];
			getClient().write(MsgType.BOX.toString()+" "+xPos+" "+yPos+" "+(mouseX-xPos)+" "+(mouseY-yPos)+" "+effType.ordinal()+" "+mainFrame.getUserName()+" "+effectBox.size()+"\n");
			released = false;
			dragged = false;
		}
	}

	/**
	 * Draws and maintains effect box on musical window
	 */
	private void drawEffectBox(){
		for(int i=0;i<effectBox.size();i++)
			effectBox.get(i).draw();
	}

	/**
	 * Adds effect box upon receiving message from server
	 * @param data data sent from server
	 */
	private void addEffectBox(String[] data){
		if(data.length == 8){
			//TODO make sure this matches
			String name = getEffName(EffectType.valueOf(data[5]));
			effectBox.add(new EffectBox(Integer.parseInt(data[1]), Integer.parseInt(data[2]), 
					Integer.parseInt(data[3]), Integer.parseInt(data[4]), 
					EffectType.values()[Integer.parseInt(data[5])], name, this));		
			addEffName(data[6], Integer.parseInt(data[7]));
			for(int i=0;i<soundObject.size();i++)
				reInsertEffect(i);
		}
	}

	/**
	 * Get name of effect
	 * @param id
	 * @return
	 */
	private String getEffName(EffectType id){
		return SndConstants.EFFECT_NAME[id.ordinal()];
	}

	/**
	 * removes effect Box from musical window
	 */
	public void removeEffectBox(){
		effectBox.removeLast();
		for(int i=0;i<soundObject.size();i++)
			reInsertEffect(i);
	}

	/**
	 * Adds name of the user who added effect box in musical window
	 * @param name name of the user
	 * @param id id of effect box. Usuasally the last element in the linked list
	 */
	private void addEffName(String name, int id){
		effUsers.add(name);
		effNameID.add(id);
		effTimeOut.add(this.millis());
	}

	/**
	 * draws name of user who added effect box in musical window
	 */
	private void displayEffName(){
		for(int i=0;i<effNameID.size();i++){
			for(int j=0;j<effectBox.size();j++){
				if(effNameID.get(i) == j){
					this.textFont(font);
					this.fill(255, 255, 255, 200);
					text(effUsers.get(i), effectBox.get(j).getxPos()+5, effectBox.get(j).getyPos() + effectBox.get(j).getHeight() - 20);
				}
			}	
		}
	}

	/**
	 * Removes name of user after displaying 2 second 
	 */
	private void timeOutEffName(){
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
	private void shuffleEffect(){
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
		clicked = true;
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		super.mouseReleased(arg0);
		released = true;
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		dragged = true;
		super.mouseDragged(arg0);
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
}
