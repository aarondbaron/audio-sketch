package net.gtcmt.audiosketch.p5.util;

import net.gtcmt.audiosketch.p5.util.P5Constants.ObjectColorType;
import net.gtcmt.audiosketch.p5.util.P5Constants.ObjectShapeType;
import net.gtcmt.audiosketch.p5.window.MusicalWindow;
import net.gtcmt.audiosketch.sound.synth.Blip;
import net.gtcmt.audiosketch.sound.synth.Buzz;
import net.gtcmt.audiosketch.sound.synth.InharmonicBell;
import net.gtcmt.audiosketch.sound.synth.RandomSig;
import net.gtcmt.audiosketch.sound.synth.Ring;
import net.gtcmt.audiosketch.sound.synth.Shir;
import net.gtcmt.audiosketch.sound.util.SndConstants.SndType;
import net.gtcmt.audiosketch.util.Constants;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;
import ddf.minim.AudioOutput;
import ddf.minim.Minim;

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
	private AudioOutput audioOut;
	private boolean collide;	
	private boolean getFrame;
	private long frame=0;
	
	private static int MAX_DEGREE = 360;
	public int startTime=0;
		
	/**
	 * SoundObject represents sound in Musical Window. It contains sound information.
	 * @param x Location on X-axis where the object is going to stay
	 * @param y Location on Y-axis where the object is going to stay
	 * @param size Size of the object given in points of vertex
	 * @param sndType Type of the sound this object contains
	 * @param colorSet Color of the object
	 * @param p
	 */
	public SoundObject(P5Points2D objPos, P5Size2D objSize, ObjectColorType color, ObjectShapeType shape, int midiNote, SndType sndType, Minim minim, PApplet p){
		this.objPos = objPos;
		this.objSize = objSize;
		this.color = chooseColor(color);
		this.shape = P5Constants.SHAPE_NAME[shape.ordinal()];
		this.midiNote = midiNote;
		this.sndType = sndType;
		this.collide = false;
		this.getFrame = false;
		image = p.loadShape(Constants.SOUND_OBJECT_PATH+this.shape);
		image.disableStyle();
		audioOut = minim.getLineOut(Minim.STEREO, 1024);
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
		if(audioOut != null && audioOut.signalCount() > 0)
			audioOut.removeSignal(0);
		switch(this.sndType){
		case BUZZ: new Thread(new Buzz(audioOut, this.midiNote)).start(); break;
		case RANDOM: new Thread(new RandomSig(audioOut, this.midiNote)).start(); break;
		case INHARMONIC_BELL: new Thread(new InharmonicBell(audioOut, this.midiNote)).start(); break;
		case RING: new Thread(new Ring(audioOut, this.midiNote)).start(); break;
		case BLIP: new Thread(new Blip(audioOut, this.midiNote)).start(); break;
		case SHIR: new Thread(new Shir(audioOut, this.midiNote)).start(); break;
		}
	}
	
	/**
	 * Draw sound object
	 * @param m
	 */
	public void draw(MusicalWindow m){
		m.pushMatrix();
		m.translate(objPos.getPosX(), objPos.getPosY());
		if(collide){
			if(getFrame){
				frame = m.frameCount;
				getFrame=false;
			}
			float theta = (m.frameCount*2) % MAX_DEGREE;
			float size = (float) (2.0 - Math.abs((double) (((m.frameCount-frame)*2) % MAX_DEGREE) / (MAX_DEGREE/2)));
			m.rotate((float) Math.toRadians(theta));
			m.scale(size, size);
			timeOutRotate(m);
		}
		m.strokeWeight(5);
		m.fill(0, 0, 0, 0);
		m.stroke(color[0], color[1], color[2], 200);
		m.shapeMode(PConstants.CENTER);
		m.shape(image, 0, 0, objSize.getWidth(), objSize.getHeight());
		m.popMatrix();
	}
	
	/**
	 * Amount of time sound object will rotate
	 * @param m
	 */
	private void timeOutRotate(MusicalWindow m){
		if(m.millis()  - startTime > 1500)
			collide = false;
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
		return collide;
	}

	public void setCollide(boolean collide) {
		this.collide = collide;
	}

	public boolean isGetFrame() {
		return getFrame;
	}

	public void setGetFrame(boolean getFrame) {
		this.getFrame = getFrame;
	}

	public AudioOutput getAudioOut() {
		return audioOut;
	}

	public void setAudioOut(AudioOutput audioOut) {
		this.audioOut = audioOut;
	}
	
}
