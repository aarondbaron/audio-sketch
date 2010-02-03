package net.gtcmt.audiosketch.p5.object;

import ddf.minim.AudioEffect;
import ddf.minim.effects.BandPass;
import ddf.minim.effects.HighPassSP;
import ddf.minim.effects.LowPassFS;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Size2D;
import net.gtcmt.audiosketch.p5.util.SoundObject;
import net.gtcmt.audiosketch.p5.window.MusicalWindow;
import net.gtcmt.audiosketch.sound.effect.Delay;
import net.gtcmt.audiosketch.sound.effect.Distort;
import net.gtcmt.audiosketch.sound.effect.Reverb;
import net.gtcmt.audiosketch.sound.util.SndConstants.EffectType;
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

	private P5Points2D objPos;
	private P5Size2D objSize;
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
	public EffectBox(P5Points2D objPos, P5Size2D objSize, EffectType effType, MusicalWindow m){
		this.objPos = objPos;
		this.objSize = objSize;
		this.effType = effType;
		this.effName = effType.toString();
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
		m.rect(objPos.getPosX(), objPos.getPosY(), objSize.getWidth(), objSize.getHeight());
		drawText();
	}
	
	/**
	 * Draw text box describing what type of effect box is
	 */
	private void drawText(){
		m.textFont(font);
		m.fill(255, 255, 255, 200);
		m.text(effName, objPos.getPosX()+5, objPos.getPosY()+ objSize.getHeight() - 5);
	}
	
	/**
	 * Check boundary
	 * @param soundObject
	 * @return
	 */
	public boolean bound(SoundObject soundObject){
		if(soundObject.getPosX() > objPos.getPosX() && soundObject.getPosX() < objPos.getPosX() + objSize.getWidth() && soundObject.getPosY() > objPos.getPosY() && soundObject.getPosY() < objPos.getPosY() + objSize.getHeight())
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
	public int getPosX() {
		return objPos.getPosX();
	}

	public void setPoxX(int posX) {
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
