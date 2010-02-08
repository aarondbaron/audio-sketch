package net.gtcmt.audiosketch.p5.object;

import java.util.LinkedList;
import processing.core.PApplet;
import processing.core.PConstants;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Size2D;
import net.gtcmt.audiosketch.p5.util.SoundObject;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;

/**
 * Handles play back bar within musical window.
 * Acts as a trigger.
 * @author akito
 *
 */
public class PlayBackBar {

	public static final int MAX_TRIG = 10;
	private P5Points2D objPos;
	private P5Points2D initPos;
	private P5Size2D objSize;
	private float speed;
	private float angle;
	private LinkedList<SoundObject> soundObject;
	private PlayBackType playbackType;
	private PApplet p5;
	private LinkedList<Boolean> trigState;				//TrigState correspoinds to soundObject. So the size are the same.
	private LinkedList<Boolean> ignoreState;				
	private float collisionArea;
	private int numObject;
	
	/**
	 * Constructor for PlayBackBar
	 * @param x position on x-axis where the playback bar originates
	 * @param y position on y-axis where the playback bar originates
	 * @param speed Speed of playback bar
	 * @param angle the direction in which bar will progress
	 * @param barMode Decides which type of bar to trigger
	 * @param soundObject Need this to detect collision
	 * @param numObject 
	 * @param p
	 */
	public PlayBackBar(P5Points2D objPos, float speed, float angle, PlayBackType pbType, LinkedList<SoundObject> soundObject, PApplet p){
		this.initPos = this.objPos = objPos;
		this.p5 = p;
		
		if(speed > P5Constants.MAX_SPEED){
			this.speed = P5Constants.MAX_SPEED;
		}
		else{
			this.speed = speed;
		}
		
		if(speed < P5Constants.MIN_SPEED){
			this.speed = P5Constants.MIN_SPEED;
		}
		else{
			this.speed = speed;
		}
			
		this.angle = angle;
		this.soundObject = soundObject;
		this.playbackType = pbType;
		
		switch(pbType){
		case BAR:
			this.objSize = new P5Size2D(P5Constants.BAR_WIDTH,10);
			break;
		case BAR2:
			this.objSize = new P5Size2D(P5Constants.BAR_WIDTH,10);
			break;
		case RADIAL:
			this.objSize = new P5Size2D(0, 0);
			break;
		case RADIAL2:
			this.objSize = new P5Size2D(0, 0);
			break;
		default:
			this.objSize = new P5Size2D(0, 0);
		}
		
		collisionArea = P5Constants.COLLISION_AREA;
		numObject = soundObject.size();

		trigState = new LinkedList<Boolean>();
		for(int i=0;i<soundObject.size();i++)
			trigState.add(false);
		
		ignoreState = new LinkedList<Boolean>();
		for(int i=0;i<soundObject.size();i++){
			float objectX = (float) ((soundObject.get(i).getPosX()+(soundObject.get(i).getWidth()/2)) - (objPos.getPosX()+(Math.cos(angle+Math.PI)*(P5Constants.COLLISION_AREA/2))));
			float objectY = (float) ((soundObject.get(i).getPosY()+(soundObject.get(i).getHeight()/2)) - (objPos.getPosY()+(Math.sin(angle+Math.PI)*(P5Constants.COLLISION_AREA/2))));

			float minDistance = (float) ((Math.sqrt(Math.pow(soundObject.get(i).getWidth()/2, 2)+Math.pow(soundObject.get(i).getHeight()/2, 2))/4)+((collisionArea)/2));

			if(Math.sqrt(Math.pow(objectX, 2)+Math.pow(objectY, 2)) < minDistance) 
				ignoreState.add(true);
			else
				ignoreState.add(false);
		}
	}

	/**
	 * Draws and animates time line bar
	 */
	public void draw(){		
		switch(playbackType){
		case RADIAL:
			p5.strokeWeight(10);
			p5.stroke(255, 255, 255, 200);
			p5.fill(0, 0, 0, 0);
			p5.ellipse(objPos.getPosX(), objPos.getPosY(), objSize.getWidth(), objSize.getHeight());
			objSize.setSize(((int) (objSize.getWidth()+speed)), ((int) (objSize.getHeight()+speed)));
			break;
			
		case RADIAL2:
			p5.strokeWeight(10);
			p5.stroke(255, 25, 255, 200);
			p5.fill(0, 0, 0, 0);
			p5.ellipse(objPos.getPosX(), objPos.getPosY(), objSize.getWidth(), objSize.getHeight());
			objSize.setSize(((int) (objSize.getWidth()+speed)), ((int) (objSize.getHeight()+speed)));
			break;
			
		case BAR2:
			p5.pushMatrix();	
			p5.translate(objPos.getPosX(), objPos.getPosY());
			p5.rotate((float) (angle+P5Constants.NINETY));
			p5.rectMode(PConstants.CENTER);
			p5.noStroke();
			p5.fill(255, 255, 250, 200);
			p5.rect(0, 0, objSize.getWidth(), objSize.getHeight());
			p5.popMatrix();
			
			p5.pushMatrix(); //Used for collision detection
			p5.translate(initPos.getPosX()+(float)(Math.cos(angle+Math.PI)*(P5Constants.COLLISION_AREA/2)), initPos.getPosY()+(float) (Math.sin(angle+Math.PI)*(P5Constants.COLLISION_AREA/2)));	
			p5.noStroke();
			p5.noFill();
			p5.scale(collisionArea/P5Constants.COLLISION_AREA, collisionArea/P5Constants.COLLISION_AREA);
			p5.ellipse(0, 0, P5Constants.COLLISION_AREA, P5Constants.COLLISION_AREA);
			p5.popMatrix();
			objPos.setPosX((int) (objPos.getPosX()+speed*Math.cos(angle)));
			objPos.setPosY((int) (objPos.getPosY()+speed*Math.sin(angle)));
			collisionArea += speed*2;
			break;
			
		case BAR:
			p5.pushMatrix();	
			p5.translate(objPos.getPosX(), objPos.getPosY());
			p5.rotate((float) (angle+P5Constants.NINETY));
			p5.rectMode(PConstants.CENTER);
			p5.noStroke();
			p5.fill(255, 255, 255, 200);
			p5.rect(0, 0, objSize.getWidth(), objSize.getHeight());
			p5.popMatrix();
			
			p5.pushMatrix(); //Used for collision detection
			p5.translate(initPos.getPosX()+(float)(Math.cos(angle+Math.PI)*(P5Constants.COLLISION_AREA/2)), initPos.getPosY()+(float) (Math.sin(angle+Math.PI)*(P5Constants.COLLISION_AREA/2)));	
			p5.noStroke();
			p5.noFill();
			p5.scale(collisionArea/P5Constants.COLLISION_AREA, collisionArea/P5Constants.COLLISION_AREA);
			p5.ellipse(0, 0, P5Constants.COLLISION_AREA, P5Constants.COLLISION_AREA);
			p5.popMatrix();
			objPos.setPosX((int) (objPos.getPosX()+speed*Math.cos(angle)));
			objPos.setPosY((int) (objPos.getPosY()+speed*Math.sin(angle)));
			collisionArea += speed*2;
			
		}
	}

	/**
	 * Detects collision for Radial time line. When collision happens initiate action.
	 */
	public void collideCircle(){
		for (int i = numObject - 1; i >= 0; i--) {

			float objectX = (soundObject.get(i).getPosX()+(soundObject.get(i).getWidth()/2)) - objPos.getPosX();
			float objectY = (soundObject.get(i).getPosY()+(soundObject.get(i).getHeight()/2)) - objPos.getPosY();

			float minDistance = (float) ((Math.sqrt(Math.pow(soundObject.get(i).getWidth()/2, 2)+Math.pow(soundObject.get(i).getHeight()/2, 2))/4)+(objSize.getWidth()/2));

			if(!trigState.get(i)){
				if(Math.sqrt(Math.pow(objectX, 2)+Math.pow(objectY, 2)) < minDistance) {
					trigState.set(i, true);
					soundObject.get(i).play();
					soundObject.get(i).setCollide(true);
					soundObject.get(i).setGetFrame(true);
					soundObject.get(i).startTime = p5.millis();			
				}
				
			}
		}
	}
	
	/**
	 * Detects collision for time line bar. When collision happens initiate action.
	 */
	//TODO Akito come up with better collision algorithm
	public void collideBar(){
		for (int i = numObject - 1; i >= 0; i--) {

			float objectX = (float) ((soundObject.get(i).getPosX()+(soundObject.get(i).getWidth()/2)) - (objPos.getPosX()+(Math.cos(angle+Math.PI)*(P5Constants.COLLISION_AREA/2))));
			float objectY = (float) ((soundObject.get(i).getPosY()+(soundObject.get(i).getHeight()/2)) - (objPos.getPosY()+(Math.sin(angle+Math.PI)*(P5Constants.COLLISION_AREA/2))));

			float minDistance = (float) ((Math.sqrt(Math.pow(soundObject.get(i).getWidth()/2, 2)+Math.pow(soundObject.get(i).getHeight()/2, 2))/4)+((collisionArea-400)/2));

			if(!trigState.get(i) && !ignoreState.get(i)){
				if(Math.sqrt(Math.pow(objectX, 2)+Math.pow(objectY, 2)) < minDistance) {
					trigState.set(i, true);
					soundObject.get(i).play();
					soundObject.get(i).setCollide(true);
					soundObject.get(i).setGetFrame(true);
					soundObject.get(i).startTime = p5.millis();
				}
			}
		}
	}

	/*------------------- Getter/Setter ------------------*/
	public PlayBackType getPlaybackType() {
		return playbackType;
	}

	public void setPlaybackType(PlayBackType playbackType) {
		this.playbackType = playbackType;
	}
	
	public int getWidth() {
		return objSize.getWidth();
	}

	public void setWidth(int width) {
		this.objSize.setWidth(width);
	}
	
	public void setHeight(int height) {
		this.objSize.setHeight(height);
	}
	
	public void setSize(int width, int height) {
		this.objSize.setSize(width, height);
	}
	
	public int getPosX() {
		return objPos.getPosX();
	}

	public void setPosX(int x) {
		this.objPos.setPosX(x);
	}
	
	public int getPosY() {
		return objPos.getPosY();
	}

	public void setPosY(int posY) {
		this.objPos.setPosY(posY);
	}
	
	public int getNumObject() {
		return numObject;
	}

	public void setNumObject(int numObject) {
		this.numObject = numObject;
	}

	public int getInitY() {
		return initPos.getPosY();
	}

	public int getInitX() {
		return initPos.getPosX();
	}
	
	/**
	 * 
	 * @param i index of sound object
	 * @param b
	 */
	public void setTrigState(int i, Boolean b) {
		trigState.set(i, b);
	}
	
	public Boolean getTrigState(int i) {		
		return trigState.get(i);
	}
	
	public void setSoundObjectCollide(int index, boolean bool){
		soundObject.get(index).setCollide(bool);
	}
	
	public boolean getSoundObjectCollide(int index){
		return soundObject.get(index).isCollide();
	}
	
	public SoundObject getSoundObject(int index){
		return soundObject.get(index);
	}
	
	public LinkedList<SoundObject> getSoundObject() {
		return soundObject;
	}

	public void setSoundObject(LinkedList<SoundObject> soundObject) {
		this.soundObject = soundObject;
	}
}
