package net.akito.shared.client.visual;

import java.util.LinkedList;
import processing.core.PApplet;
import processing.core.PConstants;
import net.akito.shared.client.util.SoundObject;
import net.akito.shared.client.visual.util.VisualConstants;
import net.akito.shared.client.visual.util.VisualConstants.PlayBackType;

/**
 * Handles play back bar within musical window.
 * @author akito
 *
 */
public class PlayBackBar {

	private float posX, posY;
	private float initX, initY;
	private float width,height;
	private float speed;
	private float angle;
	private LinkedList<SoundObject> soundObject;
	private PlayBackType playbackType;
	private PApplet p5;
	private LinkedList<Boolean> trigState;
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
	public PlayBackBar(float x, float y, float speed, float angle, int barMode, LinkedList<SoundObject> soundObject, PApplet p){
		this.posX = x;
		this.posY = y;
		this.initX = x;
		this.initY = y;
		this.p5 = p;
		
		if(speed > VisualConstants.MAX_SPEED)
			this.speed = VisualConstants.MAX_SPEED;
		else
			this.speed = speed;
		this.angle = angle;
		this.soundObject = soundObject;
		if(barMode == 0) {
			this.playbackType = PlayBackType.BAR;
			this.width=VisualConstants.BAR_WIDTH;
			this.height=12;
		}
		else{
			this.playbackType = PlayBackType.RADIAL;
			this.width=0;
			this.height=0;

		}
		
		collisionArea = VisualConstants.COLLISION_AREA;
		numObject = soundObject.size();

		trigState = new LinkedList<Boolean>();
		for(int i=0;i<soundObject.size();i++)
			trigState.add(false);
		
		ignoreState = new LinkedList<Boolean>();
		for(int i=0;i<soundObject.size();i++){
			float objectX = (float) ((soundObject.get(i).getPosX()+(soundObject.get(i).getWidth()/2)) - (x+(Math.cos(angle+Math.PI)*(VisualConstants.COLLISION_AREA/2))));
			float objectY = (float) ((soundObject.get(i).getPosY()+(soundObject.get(i).getHeight()/2)) - (y+(Math.sin(angle+Math.PI)*(VisualConstants.COLLISION_AREA/2))));

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
	public void play(){		
		switch(playbackType){
		case RADIAL:
			p5.strokeWeight(10);
			p5.stroke(255, 255, 255, 200);
			p5.fill(0, 0, 0, 0);
			p5.ellipse(posX, posY, width, width);
			width += speed;
			break;
		case BAR:
			p5.pushMatrix();	
			p5.translate(posX, posY);
			p5.rotate((float) (angle+VisualConstants.NINETY));
			p5.rectMode(PConstants.CENTER);
			p5.noStroke();
			p5.fill(255, 255, 255, 200);
			p5.rect(0, 0, width, height);
			p5.popMatrix();
			
			p5.pushMatrix(); //Used for collision detection
			p5.translate(initX+(float)(Math.cos(angle+Math.PI)*(VisualConstants.COLLISION_AREA/2)), initY+(float) (Math.sin(angle+Math.PI)*(VisualConstants.COLLISION_AREA/2)));	
			p5.noStroke();
			p5.noFill();
			p5.scale(collisionArea/VisualConstants.COLLISION_AREA, collisionArea/VisualConstants.COLLISION_AREA);
			p5.ellipse(0, 0, VisualConstants.COLLISION_AREA, VisualConstants.COLLISION_AREA);
			p5.popMatrix();
			posX += speed*Math.cos(angle);
			posY += speed*Math.sin(angle);
			collisionArea += speed*2;
		}
	}

	/**
	 * Detects collision for Radial time line. When collision happens initiate action.
	 */
	public void collideCircle(){
		for (int i = numObject - 1; i >= 0; i--) {

			float objectX = (soundObject.get(i).getPosX()+(soundObject.get(i).getWidth()/2)) - posX;
			float objectY = (soundObject.get(i).getPosY()+(soundObject.get(i).getHeight()/2)) - posY;

			float minDistance = (float) ((Math.sqrt(Math.pow(soundObject.get(i).getWidth()/2, 2)+Math.pow(soundObject.get(i).getHeight()/2, 2))/4)+(width/2));

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
	//TODO come up with better collision algorithm
	public void collideBar(){
		for (int i = numObject - 1; i >= 0; i--) {

			float objectX = (float) ((soundObject.get(i).getPosX()+(soundObject.get(i).getWidth()/2)) - (posX+(Math.cos(angle+Math.PI)*(VisualConstants.COLLISION_AREA/2))));
			float objectY = (float) ((soundObject.get(i).getPosY()+(soundObject.get(i).getHeight()/2)) - (posY+(Math.sin(angle+Math.PI)*(VisualConstants.COLLISION_AREA/2))));

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
	
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}
	
	public float getPosX() {
		return posX;
	}

	public void setPosX(float x) {
		this.posX = x;
	}
	
	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}
	
	public int getNumObject() {
		return numObject;
	}

	public void setNumObject(int numObject) {
		this.numObject = numObject;
	}

	public float getInitY() {
		return initY;
	}

	public void setInitY(float initY) {
		this.initY = initY;
	}

	public float getInitX() {
		return initX;
	}

	public void setInitX(float initX) {
		this.initX = initX;
	}
}
