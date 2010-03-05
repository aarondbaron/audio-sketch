package net.gtcmt.audiosketch.wii;

import net.gtcmt.audiosketch.gui.util.GUIConstants;
import net.gtcmt.audiosketch.wii.util.WiiMoteConstant;
import processing.core.PApplet;

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
	
	public void draw(){
		irX = (int) ((listener.getIrX()/WiiMoteConstant.MAX_MOTE_IR_LENGTH)*GUIConstants.WINDOW_WIDTH);
		irY = (int) ((listener.getIrY()/WiiMoteConstant.MAX_MOTE_IR_LENGTH)*GUIConstants.WINDOW_HEIGHT);
		//System.out.println("irx: "+irX+" y "+irY);
		p5.fill(listener.getRed(),listener.getGreen(),listener.getBlue(),listener.getAlpha());
		p5.noStroke();
		p5.ellipse(irX, irY, POINTER_SIZE, POINTER_SIZE);
	}
}
