package net.gtcmt.audiosketch.p5.object.playbar;

import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Size2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import processing.core.PApplet;
import processing.core.PConstants;

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
		case BAR:
			this.playbarSize = new P5Size2D(P5Constants.BAR_WIDTH,10);
			break;
		case BAR2:
			this.playbarSize = new P5Size2D(P5Constants.BAR_WIDTH,10);
			break;
		case RADIAL:
			this.playbarSize = new P5Size2D(0, 0);
			break;
		case RADIAL2:
			this.playbarSize = new P5Size2D(0, 0);
			break;
		default:
			this.playbarSize = new P5Size2D(0, 0);
		}
		
		collisionArea = P5Constants.COLLISION_AREA;
	}

	/**
	 * Draws and animates time line bar
	 */
	public abstract void draw();
/*	{		
		switch(playbackType){
		case RADIAL:
			p5.strokeWeight(10);
			p5.stroke(255, 255, 255, 200);
			p5.fill(0, 0, 0, 0);
			p5.ellipse(playbarPos.getPosX(), playbarPos.getPosY(), playbarSize.getWidth(), playbarSize.getHeight());
			playbarSize.setSize(((int) (playbarSize.getWidth()+speed)), ((int) (playbarSize.getHeight()+speed)));
			break;
			
		case RADIAL2:
			p5.strokeWeight(10);
			p5.stroke(255, 25, 255, 200);
			p5.fill(0, 0, 0, 0);
			p5.ellipse(playbarPos.getPosX(), playbarPos.getPosY(), playbarSize.getWidth(), playbarSize.getHeight());
			playbarSize.setSize(((int) (playbarSize.getWidth()+speed)), ((int) (playbarSize.getHeight()+speed)));
			break;
			
		case BAR2:
			p5.pushMatrix();	
			p5.translate(playbarPos.getPosX(), playbarPos.getPosY());
			p5.rotate((float) (angle+P5Constants.NINETY));
			p5.rectMode(PConstants.CENTER);
			p5.noStroke();
			p5.fill(255, 255, 250, 200);
			p5.rect(0, 0, playbarSize.getWidth(), playbarSize.getHeight());
			p5.popMatrix();
			
			p5.pushMatrix(); //Used for collision detection
			p5.translate(initPlaybarPos.getPosX()+(float)(Math.cos(angle+Math.PI)*(P5Constants.COLLISION_AREA/2)), initPlaybarPos.getPosY()+(float) (Math.sin(angle+Math.PI)*(P5Constants.COLLISION_AREA/2)));	
			p5.noStroke();
			p5.noFill();
			p5.scale(collisionArea/P5Constants.COLLISION_AREA, collisionArea/P5Constants.COLLISION_AREA);
			p5.ellipse(0, 0, P5Constants.COLLISION_AREA, P5Constants.COLLISION_AREA);
			p5.popMatrix();
			playbarPos.setPosX((int) (playbarPos.getPosX()+speed*Math.cos(angle)));
			playbarPos.setPosY((int) (playbarPos.getPosY()+speed*Math.sin(angle)));
			collisionArea += speed*2;
			break;
			
		case BAR:
			p5.pushMatrix();	
			p5.translate(playbarPos.getPosX(), playbarPos.getPosY());
			p5.rotate((float) (angle+P5Constants.NINETY));
			p5.rectMode(PConstants.CENTER);
			p5.noStroke();
			p5.fill(255, 255, 255, 200);
			p5.rect(0, 0, playbarSize.getWidth(), playbarSize.getHeight());
			p5.popMatrix();
			
			p5.pushMatrix(); //Used for collision detection
			p5.translate(initPlaybarPos.getPosX()+(float)(Math.cos(angle+Math.PI)*(P5Constants.COLLISION_AREA/2)), initPlaybarPos.getPosY()+(float) (Math.sin(angle+Math.PI)*(P5Constants.COLLISION_AREA/2)));	
			p5.noStroke();
			p5.noFill();
			p5.scale(collisionArea/P5Constants.COLLISION_AREA, collisionArea/P5Constants.COLLISION_AREA);
			p5.ellipse(0, 0, P5Constants.COLLISION_AREA, P5Constants.COLLISION_AREA);
			p5.popMatrix();
			playbarPos.setPosX((int) (playbarPos.getPosX()+speed*Math.cos(angle)));
			playbarPos.setPosY((int) (playbarPos.getPosY()+speed*Math.sin(angle)));
			collisionArea += speed*2;
			
		}
	}
*/
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
