package net.gtcmt.audiosketch.client.util;

import net.gtcmt.audiosketch.client.sound.synth.Blip;
import net.gtcmt.audiosketch.client.sound.synth.Buzz;
import net.gtcmt.audiosketch.client.sound.synth.InharmonicBell;
import net.gtcmt.audiosketch.client.sound.synth.RandomSig;
import net.gtcmt.audiosketch.client.sound.synth.Ring;
import net.gtcmt.audiosketch.client.sound.synth.Shir;
import net.gtcmt.audiosketch.client.sound.util.SndConstants.SndType;
import net.gtcmt.audiosketch.client.visual.MusicalWindow;
import net.gtcmt.audiosketch.client.visual.util.VisualConstants;
import net.gtcmt.audiosketch.client.visual.util.VisualConstants.ObjectColorType;
import net.gtcmt.audiosketch.client.visual.util.VisualConstants.ObjectShapeType;
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
	
	private float posX, posY;
	private int width, height;
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
	public SoundObject(float x, float y, int width, int height, ObjectColorType color, ObjectShapeType shape, int midiNote, SndType sndType, Minim minim, PApplet p){
		this.posX = x;
		this.posY = y;
		this.width = width;
		this.height = height;
		this.color = chooseColor(color);
		this.shape = VisualConstants.SHAPE_NAME[shape.ordinal()];
		this.midiNote = midiNote;
		this.sndType = sndType;
		this.collide = false;
		this.getFrame = false;
		image = p.loadShape(this.shape);
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
		m.translate(posX, posY);
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
		m.shape(image, 0, 0, width, height);
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
	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isCollide() {
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
