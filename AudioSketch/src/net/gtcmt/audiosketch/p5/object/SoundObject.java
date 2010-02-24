package net.gtcmt.audiosketch.p5.object;

import java.util.Hashtable;

import net.gtcmt.audiosketch.p5.object.playbar.PlayBackBar;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Size2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.ObjectColorType;
//import net.gtcmt.audiosketch.p5.util.P5Constants.ObjectShapeType;
import net.gtcmt.audiosketch.sound.util.AudioControl;
import net.gtcmt.audiosketch.sound.util.SndConstants.SndType;
import net.gtcmt.audiosketch.util.Constants;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;

/**
 * Sound object drawn on musical window
 * @author akito
 *
 */
public class SoundObject {
	
	private P5Points2D objPos;
	private P5Size2D objSize;
	private float[] color;
	private String shape;
	private int midiNote;
	private SndType sndType;
	private PShape image;
	private Hashtable<PlayBackBar, Boolean> collideState;	//collide state for each playback
	private boolean isCollide;
	private boolean getFrame;
	private long frame=0;
	private float updateStep=(float)0.0;
	//private float updateStep2=(float)0.0;
	//private
	//sound name, pitch, filter type, cutoff, q, center freq, gain, effect type, effect on/off
	
	private static int MAX_DEGREE = 360;
	public long startTime = 0;

	/**
	 * SoundObject represents sound in Musical Window. It contains sound information.
	 * @param x Location on X-axis where the object is going to stay
	 * @param y Location on Y-axis where the object is going to stay
	 * @param size Size of the object given in points of vertex
	 * @param sndType Type of the sound this object contains
	 * @param colorSet Color of the object
	 * @param p
	 */
	public SoundObject(P5Points2D objPos, P5Size2D objSize, ObjectColorType color, int shape, int midiNote, SndType sndType, PApplet p){
		this.objPos = objPos;
		this.objSize = objSize;
		this.color = chooseColor(color);
		this.shape = P5Constants.SHAPE_NAME[shape];//P5Constants.SHAPE_NAME[shape.ordinal()];
		this.midiNote = midiNote;
		this.sndType = sndType;
		this.isCollide = false;
		this.getFrame = false;
		this.collideState = new Hashtable<PlayBackBar, Boolean>();
		this.image = p.loadShape(Constants.SOUND_OBJECT_PATH+this.shape);
		this.image.disableStyle();
	}

	/**
	 * Create color of sound object
	 * @param color
	 * @return
	 */
	public float[] chooseColor(ObjectColorType color){
		float[] colorSet = new float[3];
		switch (color) {
		case WHITE: colorSet[0] = 255f; colorSet[1] = 255f; colorSet[2] = 255f; break;
		case BLUE: colorSet[0] = 0; colorSet[1] = 0; colorSet[2] = 255f; break;
		case GREEN: colorSet[0] = 0; colorSet[1] = 255f; colorSet[2] = 0; break;
		case YELLOW: colorSet[0] = 255f; colorSet[1] = 255f; colorSet[2] = 0; break;
		case ORANGE: colorSet[0] = 255f; colorSet[1] = 125f; colorSet[2] = 0; break;
		}	
		return colorSet;
	}

	/**
	 * Play sound. It uses thread to do this
	 */
	public void play(){
		switch(this.sndType) {
		case BUZZ: AudioControl.getAudioCtrl().trigger(SndType.BUZZ.toString(), (float) Math.random()*2); break;
		case BANJO: AudioControl.getAudioCtrl().trigger(SndType.BANJO.toString(), (float) Math.random()*2); break;
		case CHING: AudioControl.getAudioCtrl().trigger(SndType.CHING.toString(), (float) Math.random()*2); break;
		case CLARINET: AudioControl.getAudioCtrl().trigger(SndType.CLARINET.toString(), (float) Math.random()*2); break;
		case POP: AudioControl.getAudioCtrl().trigger(SndType.POP.toString(), (float) Math.random()*2); break;
		case FEMALE: AudioControl.getAudioCtrl().trigger(SndType.FEMALE.toString(), (float) Math.random()*2); break;
		case GUITAR_CLASSIC: AudioControl.getAudioCtrl().trigger(SndType.GUITAR_CLASSIC.toString(), (float) Math.random()*2); break;
		case GUITAR_ELEC: AudioControl.getAudioCtrl().trigger(SndType.GUITAR_ELEC.toString(), (float) Math.random()*2); break;
		case SAX: AudioControl.getAudioCtrl().trigger(SndType.SAX.toString(), (float) Math.random()*2); break;
		case TOY_PIANO: AudioControl.getAudioCtrl().trigger(SndType.TOY_PIANO.toString(), (float) Math.random()*2); break;
		case VIOLA: AudioControl.getAudioCtrl().trigger(SndType.VIOLA.toString(), (float) Math.random()*2); break;
		case ZAP: AudioControl.getAudioCtrl().trigger(SndType.ZAP.toString(), (float) Math.random()*2); break;
		}
	}
	
	/**
	 * Draw sound object
	 * @param p5
	 */
	public void draw(PApplet p5){
		p5.pushMatrix();
		p5.translate(objPos.getPosX(), objPos.getPosY());
		//rotateAndJitter(p5);		

		
		p5.strokeWeight(5);
		p5.fill(0, 0, 0, 0);
		p5.stroke(color[0], color[1], color[2], 200);
		p5.shapeMode(PConstants.CENTER);
		p5.shape(image, 0, 0, objSize.getWidth(), objSize.getHeight());
		
		
		//setup for spawnMultiple
		float rotateRate = (float).007;
		float translateParam = (float) 5;
		float sfactorParam = (float) (1.0 + .01);
		int numShapes = 10;
		spawnMultiple(p5,numShapes,rotateRate, translateParam,sfactorParam);
		
		
		p5.popMatrix();
	}

	private void spawnMultiple(PApplet p5, float numShapes, float rotateRate, float translateParam, float sfactorParam) {
		// TODO Auto-generated method stub
		
		if(isCollide){
			if(getFrame){
				frame = p5.frameCount;
				getFrame=false;
			}
			//image.setVisible(false);
			
			//float theta = (p5.frameCount*2) % MAX_DEGREE;
			//float size = (float) (2.0 - Math.abs((double) (((p5.frameCount-frame)*2) % MAX_DEGREE) / (MAX_DEGREE/2)));
			//p5.rotate((float) Math.toRadians(theta));
			
			updateStep = updateStep + rotateRate;
			
			//updateStep2= updateStep2+ translateRate;
			
			
			for (int i=0; i<numShapes;i++){
				
				p5.rotate(updateStep);
				p5.translate((float) (translateParam*Math.cos(updateStep)),(float) (translateParam*Math.cos(updateStep)));
				p5.scale((float)(sfactorParam), (float)(sfactorParam));
				//p5.stroke(color[0] , color[1], color[2], 200);
				//p5.scale((float)(sfactorParam*Math.cos(updateStep)+.00000001), (float)(sfactorParam*Math.cos(updateStep)+.00000001));
				p5.shape(image, 0, 0, objSize.getWidth(), objSize.getHeight());

			}

			float timeOutMS=1000;
			timeOut(p5,timeOutMS);
		}
		
	}

	/**
	 * Rotate sound object
	 * @param p5
	 */
	private void rotateAndJitter(PApplet p5){
		//TODO isCollider no longer relevant?
		if(isCollide){
			if(getFrame){
				frame = p5.frameCount;
				getFrame=false;
			}
			float theta = (p5.frameCount*2) % MAX_DEGREE;
			//float size = (float) (2.0 - Math.abs((double) (((p5.frameCount-frame)*2) % MAX_DEGREE) / (MAX_DEGREE/2)));
			p5.rotate((float) Math.toRadians(theta));
			float sfactor=(float).1;
			p5.scale((float)(Math.random()*sfactor+1), (float)(Math.random()*sfactor+1));
			timeOut(p5, (float) (500) );
		}
	}
	
	
	
	/**
	 * Amount of time sound object will rotate
	 * @param p5
	 * @param timeOutMS 
	 */
	private void timeOut(PApplet p5, float timeOutMS){
		if(System.currentTimeMillis()  - startTime > timeOutMS){
			isCollide = false;
			//image.setVisible(true);
		}
	}

	/*------------------ Getter/Setter ----------------*/
	public int getPosX() {
		return objPos.getPosX();
	}

	public void setPosX(int posX) {
		this.objPos.setPosX(posX);
	}

	public int getPosY() {
		return objPos.getPosY();
	}

	public void setPosY(int posY) {
		this.objPos.setPosY(posY);
	}

	public void setPos(int posX, int posY) {
		objPos.setPosX(posX);
		objPos.setPosY(posY);
	}
	
	public int getWidth() {
		return objSize.getWidth();
	}

	public void setWidth(int width) {
		this.objSize.setWidth(width);
	}

	public int getHeight() {
		return objSize.getHeight();
	}

	public void setHeight(int height) {
		this.objSize.setHeight(height);
	}

	public synchronized boolean isCollide() {
		return isCollide;
	}

	public void setCollide(boolean collide) {
		this.isCollide = collide;
	}

	public boolean isGetFrame() {
		return getFrame;
	}

	public void setGetFrame(boolean getFrame) {
		this.getFrame = getFrame;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	public Hashtable<PlayBackBar, Boolean> getCollideState() {
		return collideState;
	}
	
	public void putCollideState(PlayBackBar playBar, boolean collideState){
		this.collideState.put(playBar, collideState);
	}
	
	public boolean getCollideState(PlayBackBar playBar){
		return this.collideState.get(playBar);
	}
	
	public void removeCollideState(PlayBackBar playBar) {
		this.collideState.remove(playBar);
	}

	public void setCollideState(PlayBackBar playBar, boolean bool) {
		this.collideState.remove(playBar);
		this.collideState.put(playBar, bool);
	}

	public SndType getSndType() {
		return sndType;
	}

	public void setSndType(SndType sndType) {
		this.sndType = sndType;
	}
	
	public int getMidiNote() {
		return midiNote;
	}

	public void setMidiNote(int midiNote) {
		this.midiNote = midiNote;
	}
}
