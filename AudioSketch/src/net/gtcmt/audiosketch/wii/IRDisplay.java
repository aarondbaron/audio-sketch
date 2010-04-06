package net.gtcmt.audiosketch.wii;

import net.gtcmt.audiosketch.gui.util.GUIConstants;
import net.gtcmt.audiosketch.wii.util.WiiMoteConstant;
import processing.core.PApplet;
import processing.core.PConstants;

public class IRDisplay {

	private PApplet p5;
	private WiiMoteListener listener;
	private static final float POINTER_SIZE = 10;
	private int irX;
	private int irY;
	
	public IRDisplay(PApplet p5, WiiMoteListener listener){
		this.p5 = p5;
		this.listener = listener;
	}
	
	public void draw(int index){
		irX = (int) ((listener.getIrX()/WiiMoteConstant.MAX_MOTE_IR_WIDTH)*GUIConstants.WINDOW_WIDTH);
		irY = (int) ((listener.getIrY()/WiiMoteConstant.MAX_MOTE_IR_HEIGHT)*GUIConstants.WINDOW_HEIGHT);
		//System.out.println("irx: "+irX+" y "+irY);
		p5.fill(listener.getRed(),listener.getGreen(),listener.getBlue(),listener.getAlpha());
		p5.noStroke();
		if(index == 0){
			p5.ellipse(irX, irY, POINTER_SIZE, POINTER_SIZE);
		}
		else{
			p5.rectMode(PConstants.CENTER);
			p5.rect(irX, irY, POINTER_SIZE, POINTER_SIZE);
		}
	}
}
