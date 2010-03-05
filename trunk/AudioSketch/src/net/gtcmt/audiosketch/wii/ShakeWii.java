package net.gtcmt.audiosketch.wii;

import net.gtcmt.audiosketch.gui.util.GUIConstants;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import net.gtcmt.audiosketch.p5.window.MusicalWindow;
import net.gtcmt.audiosketch.wii.util.WiiMoteConstant;

public class ShakeWii {

	private float prevX;
	private float prevY;
	private float prevZ;
	private float deltaX;
	private float deltaY;
	private float deltaZ;
	private static final float THRESHOLD = 0.2f;
	private static final long UPDATE_TIME = 200;
	private long curTime;
	private boolean trig;
	private MusicalWindow mwp5;
	private WiiMoteListener listener;
	private float dotProduct;
	private float angle;
	private long elapsedTime;
	private long PREV_TIME = 100;
	private double[] irArrX;
	private double[] irArrY;
	private static final int IR_ARR_SIZE = 10;
	
	public ShakeWii(MusicalWindow mwp5, WiiMoteListener listener){
		this.mwp5 = mwp5;
		this.listener = listener;
		prevX = prevY = prevZ = 0;
		deltaX = deltaY = deltaZ = 0;
		trig = false;
		irArrX = new double[IR_ARR_SIZE];
		irArrY = new double[IR_ARR_SIZE];
		elapsedTime = System.currentTimeMillis();
	}
		
	public void onAccelChanged(float accX, float accY, float accZ){
		
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
	
		dotProduct(accX-prevX,accY-prevY,accZ-prevZ);
		angle(listener.getRealIrX(),listener.getRealIrY());
		
		prevX = accX;
		prevY = accY;
		prevZ = accZ;
	
		//System.out.println(Math.asin(accX+0.000000001f)+" "+Math.asin(accY+0.000000001f));
		
		if(elapsedTime > PREV_TIME ){	
			elapsedTime = elapsedTime - System.currentTimeMillis();
		}
	}
	
	private void dotProduct(float curX, float curY, float curZ) { 
		dotProduct = (float) Math.sqrt(curX*curX+curY*curY+curZ*curZ);
//		dotProduct = (prevX * curX) + (prevY * curY) + (prevZ * curZ);
//		float prevMag = (float) Math.sqrt(prevX*prevX+prevY*prevY+prevZ*prevZ);
//		float mag = (float) Math.sqrt(curX*curX+curY*curY+curZ*curZ);
//		dotProduct /= (mag*prevMag);
//		dotProduct = (1 - dotProduct);
		//System.out.println("dot: "+dotProduct);
		if(dotProduct < 0.2f)
		{
			dotProduct = 1f;
		}
		else{
			dotProduct = 0.5f;
		}
	}
	
	private void angle(double curX, double curY){
		
		System.arraycopy(irArrX, 0, irArrX, 1, IR_ARR_SIZE-1);
		System.arraycopy(irArrY, 0, irArrY, 1, IR_ARR_SIZE-1);
		irArrX[0] = curX;
		irArrY[0] = curY;
		angle = (float) Math.atan2(irArrY[0]-irArrY[IR_ARR_SIZE-1]+0.000001f,
				irArrX[0]-irArrX[IR_ARR_SIZE-1]+0.000001f);
		
		//angle = (float) Math.atan2(curZ-prevZ, curX-prevX);
	}
	
	//TODO figure out how to get angle
	public void trigEvent(){		
		System.out.println("deltaX "+(irArrX[0]-irArrX[1])+" deltaY "+(irArrY[0]-irArrY[1]));
		System.out.println(" X "+irArrX[0]+" prevX "+irArrX[1]+" Y "+irArrY[0]+" prevY "+irArrX[1]);
		
		int irX = (int) ((listener.getIrX()/WiiMoteConstant.MAX_MOTE_IR_LENGTH)*GUIConstants.WINDOW_WIDTH);
		int irY = (int) ((listener.getIrY()/WiiMoteConstant.MAX_MOTE_IR_LENGTH)*(GUIConstants.WINDOW_HEIGHT));
		System.out.println("speed: "+dotProduct+" angle: "+angle);
		
		switch(listener.getBarType()){
		case RADIAL:
			mwp5.addPlayBackBar(PlayBackType.RADIAL, 
					new P5Points2D(irX,irY), dotProduct*P5Constants.MAX_SPEED, angle);
			listener.setRGBA(255, 255, 255, 125);
			break;
		case RADIAL2:
			mwp5.addPlayBackBar(PlayBackType.RADIAL2, 
					new P5Points2D(irX,irY), dotProduct*P5Constants.MAX_SPEED, angle);
			listener.setRGBA(100,149,237,125);
			break;
		case BAR:
			mwp5.addPlayBackBar(PlayBackType.BAR, 
					new P5Points2D(irX,irY), dotProduct*P5Constants.MAX_SPEED, angle);
			listener.setRGBA(124,252,0,125);
			break;
		case SQUAREBAR:
			mwp5.addPlayBackBar(PlayBackType.SQUAREBAR, 
					new P5Points2D(irX,irY), dotProduct*P5Constants.MAX_SPEED, angle);
			listener.setRGBA(255,255,0,125);
			break;
		case BAR2:
			mwp5.addPlayBackBar(PlayBackType.BAR2, 
					new P5Points2D(irX,irY), dotProduct*P5Constants.MAX_SPEED, angle);
			listener.setRGBA(178,34,34,125);
			break;
		case CIRCLEFILLBAR:
			mwp5.addPlayBackBar(PlayBackType.CIRCLEFILLBAR, 
					new P5Points2D(irX,irY), dotProduct*P5Constants.MAX_SPEED, angle);
			listener.setRGBA(255,192,203,125);
			break;
		case CLOCKBAR:
			mwp5.addPlayBackBar(PlayBackType.CLOCKBAR, 
					new P5Points2D(irX,irY), dotProduct*P5Constants.MAX_SPEED, angle);
			listener.setRGBA(255,165,0,125);
			break;
		}
		
		trig = true;
	}
	
	public boolean isTriggered() {
		return trig;
	}

	public void setTrigger(boolean trig) {
		this.trig = trig;
	}
}
