package net.akito.shared.client.visual;

import ddf.minim.AudioEffect;
import ddf.minim.effects.BandPass;
import ddf.minim.effects.HighPassSP;
import ddf.minim.effects.LowPassFS;
import net.akito.shared.client.sound.effect.Delay;
import net.akito.shared.client.sound.effect.Distort;
import net.akito.shared.client.sound.effect.Reverb;
import net.akito.shared.client.sound.util.SndConstants.EffectType;
import net.akito.shared.client.util.SoundObject;
import processing.core.PConstants;
import processing.core.PFont;

/**
 * Effect box drawn on the musical window.
 * if the sound object exists with in this box, then
 * effect is applied to that sound object.
 * @author akito
 *
 */
public class EffectBox {

	private int xPos, yPos;
	private int width, height;
	private MusicalWindow m;
	private EffectType effType;
	private String effName;
	private PFont font;
	
	/**
	 * Constructor
	 * @param xPos
	 * @param yPos
	 * @param width
	 * @param height
	 * @param effType
	 * @param effName
	 * @param m
	 */
	public EffectBox(int xPos, int yPos, int width, int height, EffectType effType, String effName, MusicalWindow m){
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.effType = effType;
		this.effName = effName;
		this.m = m;
		font = m.createFont("skia", 14);
	}
	
	/**
	 * Draw effect box
	 */
	public void draw(){
		m.noFill();
		m.strokeWeight(2);
		m.rectMode(PConstants.CORNER);
		m.stroke(255, 0, 255, 200);
		m.rect(xPos, yPos, width, height);
		drawText();
	}
	
	/**
	 * Draw text box describing what type of effect box is
	 */
	private void drawText(){
		m.textFont(font);
		m.fill(255, 255, 255, 200);
		m.text(effName, xPos+5, yPos + height - 5);
	}
	
	/**
	 * Check boundary
	 * @param soundObject
	 * @return
	 */
	public boolean bound(SoundObject soundObject){
		if(soundObject.getPosX() > this.xPos && soundObject.getPosX() < this.xPos+this.width && soundObject.getPosY() > this.yPos && soundObject.getPosY() < this.yPos+this.height)
			return true;
		else
			return false;
	}
	
	/**
	 * return effect
	 * @return
	 */
	public AudioEffect effect(){
		switch(this.effType) {
			case REVERB: return new Reverb();
			case DISTORTION: return new Distort();
			case DELAY: return new Delay((float)(0.2+0.3*Math.random()), (float)(0.5+0.2*Math.random()), 44100);
			case LPF: return new LowPassFS((float) (60+500*Math.random()), 44100);
			case HPF: return new HighPassSP((float) (60+2000*Math.random()), 44100);
			case BPF: return new BandPass((float) (60+2000*Math.random()), (float) (0.01 + Math.random()), 44100);
		}
		return null;
	}

	/*------------- Getter/Setter -----------------*/
	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
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

	public EffectType getEffType() {
		return effType;
	}

	public void setEffType(EffectType effType) {
		this.effType = effType;
	}

	public String getEffName() {
		return effName;
	}

	public void setEffName(String effName) {
		this.effName = effName;
	}
	
	
}
