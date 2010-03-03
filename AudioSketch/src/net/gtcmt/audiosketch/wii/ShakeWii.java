package net.gtcmt.audiosketch.wii;

import motej.event.CoreButtonEvent;
import net.gtcmt.audiosketch.gui.util.GUIConstants;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import net.gtcmt.audiosketch.p5.window.MusicalWindow;
import net.gtcmt.audiosketch.util.LogMessage;
import net.gtcmt.audiosketch.wii.util.WiiConstant;

public class ShakeWii {

	private int prevX;
	private int prevY;
	private int prevZ;
	private int deltaX;
	private int deltaY;
	private int deltaZ;
	private static final int THRESHOLD = 10;
	private static final long UPDATE_TIME = 400;
	private long curTime;
	private boolean trig;
	private MusicalWindow mwp5;
	private WiiMoteListener listener;
	private float dotProduct;
	
	public ShakeWii(MusicalWindow mwp5, WiiMoteListener listener){
		this.mwp5 = mwp5;
		this.listener = listener;
		prevX = prevY = prevZ = 0;
		deltaX = deltaY = deltaZ = 0;
		trig = false;
	}
		
	public void onAccelChanged(int accX, int accY, int accZ){
		
		deltaX = prevX-accX;
		deltaY = prevY-accY;
		deltaZ = prevZ-accZ;
		
		if(((deltaX > THRESHOLD && deltaY > THRESHOLD) 
				|| (deltaX > THRESHOLD && deltaZ > THRESHOLD) 
				|| (deltaY > THRESHOLD && deltaZ > THRESHOLD))  
				&& (System.currentTimeMillis() - curTime > UPDATE_TIME))
					{
						trigEvent();
						curTime = System.currentTimeMillis();
					}
	//	System.out.println(deltaX + " : " + deltaY + " : " + deltaZ);
		dotProduct(accX,accY,accZ);
		
		prevX = accX;
		prevY = accY;
		prevZ = accZ;
	}
	
	private void dotProduct(int curX, int curY, int curZ) 
	{ 
		dotProduct = (prevX * curX) + (prevY * curY) + (prevZ * curZ);
		float prevMag = (float) Math.abs(Math.sqrt(prevX*prevX+prevY*prevY+prevZ*prevZ));
		float mag = (float) Math.abs(Math.sqrt(curX*curX+curY*curY+curZ*curZ));
		dotProduct /= (prevMag*mag);
	}
	
	//TODO figure out how to get angle
	public void trigEvent(){		
		int irX = (int) ((listener.getIrX()/WiiConstant.MAX_MOTE_IR_LENGTH)*GUIConstants.WINDOW_WIDTH);
		int irY = (int) ((listener.getIrY()/WiiConstant.MAX_MOTE_IR_LENGTH)*GUIConstants.WINDOW_HEIGHT);
		System.out.println("speed: "+(dotProduct*P5Constants.MAX_SPEED));
		
		switch(listener.getButtonState()){
		case CoreButtonEvent.BUTTON_A:
			mwp5.addPlayBackBar(PlayBackType.BAR, 
					new P5Points2D(irX,irY), dotProduct*P5Constants.MAX_SPEED, (float)Math.random());
			break;
		case CoreButtonEvent.BUTTON_B:
			mwp5.addPlayBackBar(PlayBackType.RADIAL, 
					new P5Points2D(irX,irY), dotProduct*P5Constants.MAX_SPEED, (float)Math.random());
			break;
		case CoreButtonEvent.BUTTON_MINUS:
			mwp5.removeLastPlayBar();
			break;		
		case CoreButtonEvent.BUTTON_PLUS:
			mwp5.addPlayBackBar(PlayBackType.RADIAL2, 
					new P5Points2D(irX,irY), dotProduct*P5Constants.MAX_SPEED, (float)Math.random());
			break;
		case CoreButtonEvent.BUTTON_ONE:
			
			break;
		case CoreButtonEvent.BUTTON_TWO:
			
			break;
		case CoreButtonEvent.BUTTON_HOME:
			
			break;
		}
		
		LogMessage.info("Shake detected");
		trig = true;
	}
	
	public boolean isTriggered() {
		return trig;
	}

	public void setTrigger(boolean trig) {
		this.trig = trig;
	}
}
