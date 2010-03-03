package net.gtcmt.audiosketch.wii;

import net.gtcmt.audiosketch.gui.util.GUIConstants;
import net.gtcmt.audiosketch.wii.util.WiiConstant;
import processing.core.PApplet;

public class IRDisplay {

	private PApplet p5;
	private WiiMoteListener listener;
	private static final float POINTER_SIZE = 5;
	private int irX;
	private int irY;
	
	public IRDisplay(PApplet p5, WiiMoteListener listener){
		this.p5 = p5;
		this.listener = listener;
	}
	
	public void draw(){
		irX = (int) ((listener.getIrX()/WiiConstant.MAX_MOTE_IR_LENGTH)*GUIConstants.WINDOW_WIDTH);
		irY = (int) ((listener.getIrY()/WiiConstant.MAX_MOTE_IR_LENGTH)*GUIConstants.WINDOW_HEIGHT);
		p5.fill(255,0,255);
		p5.noStroke();
		p5.ellipse(irX, irY, POINTER_SIZE, POINTER_SIZE);
	}
}