package net.gtcmt.audiosketch.p5.object.playbar;

import java.util.LinkedList;

import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Size2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import processing.core.PApplet;

/**
 * Handles play back bar within musical window.
 * Acts as a trigger.
 * @author akito
 *
 */
public abstract class PlayBackBar {

	public static final int MAX_TRIG = 10;
	protected P5Points2D playbarPos;
	protected P5Points2D initPlaybarPos;
	protected P5Size2D playbarSize;
	protected float speed;
	protected float angle;
	protected PlayBackType playbackType;
	protected PApplet p5;
	protected float collisionArea;

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
	public PlayBackBar(P5Points2D objPos, float speed, float angle, PlayBackType pbType, PApplet p){
		this.initPlaybarPos = this.playbarPos = objPos;
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
		this.playbackType = pbType;
		
		switch(pbType){
		case BAR: case BAR2:
			this.playbarSize = new P5Size2D(P5Constants.BAR_WIDTH, P5Constants.STROKE_WEIGHT);
			break;
		case RADIAL:	 case RADIAL2: case SQUAREBAR: case SQUAREBAR2: default:
			this.playbarSize = new P5Size2D(0, 0);
			break;	
		case CLOCKBAR:
			this.playbarSize = new P5Size2D(p5.mouseX, p5.mouseY);
			break;
		}
		
		collisionArea = P5Constants.COLLISION_AREA;
	}

	/**
	 * Draws and animates time line bar
	 */
	public abstract void draw();
	
	/**
	 * plays playback bar
	 * @return true if playback is ready to be removed
	 */
	public abstract boolean checkState(LinkedList<SoundObject> soundObject, int index);

	/**
	 * Factory method for creating playbar
	 * @param barType
	 * @param mousePnt
	 * @param speed
	 * @param angle
	 * @param p5
	 * @return
	 */
	public static PlayBackBar createPlayBar(PlayBackType barType, P5Points2D mousePnt, float speed, float angle, PApplet p5){
		switch(barType)
		{
		case RADIAL:		return new RadialBar(mousePnt, speed, angle, barType, p5);
		case RADIAL2: 	return new Radial2Bar(mousePnt, speed, angle, barType, p5); 
		case CIRCLEFILLBAR: 	return new CircleFillBar(mousePnt, speed, angle, barType, p5); 
		case SQUAREFILLBAR: 	return new SquareFillBar(mousePnt, speed, angle, barType, p5); 	
		case SQUAREBAR: 	return new SquareBar(mousePnt, speed, angle, barType, p5);
		case SQUAREBAR2: 	return new SquareBar2(mousePnt, speed, angle, barType, p5);
		case CLOCKBAR:	return new ClockBar(mousePnt, speed, angle, barType, p5);	
		case BAR:		return new Bar(mousePnt, speed, angle, barType, p5);	
		case BAR2:		return new Bar2(mousePnt, speed, angle, barType, p5);
		default: 		return new RadialBar(mousePnt, speed, angle, barType, p5);
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
		return playbarSize.getWidth();
	}

	public void setWidth(int width) {
		this.playbarSize.setWidth(width);
	}
	
	public void setHeight(int height) {
		this.playbarSize.setHeight(height);
	}
	
	public void setSize(int width, int height) {
		this.playbarSize.setSize(width, height);
	}
	
	public int getPosX() {
		return playbarPos.getPosX();
	}

	public void setPosX(int x) {
		this.playbarPos.setPosX(x);
	}
	
	public int getPosY() {
		return playbarPos.getPosY();
	}

	public void setPosY(int posY) {
		this.playbarPos.setPosY(posY);
	}

	public int getInitY() {
		return initPlaybarPos.getPosY();
	}

	public int getInitX() {
		return initPlaybarPos.getPosX();
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	public float getCollisionArea() {
		return collisionArea;
	}

	public void setCollisionArea(float collisionArea) {
		this.collisionArea = collisionArea;
	}
}
