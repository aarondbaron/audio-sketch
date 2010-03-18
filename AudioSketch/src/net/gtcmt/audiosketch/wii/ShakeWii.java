package net.gtcmt.audiosketch.wii;

import net.gtcmt.audiosketch.event.AudioConstants;
import net.gtcmt.audiosketch.event.TempoClock;
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
	private static final float THRESHOLD = 0.3f;
	private static final long UPDATE_TIME = 200;
	private long curTime;
	private boolean trig;
	private MusicalWindow mwp5;
	private WiiMoteListener listener;
	private float shakeVelocity;
	private float angle;
	private long elapsedTime;
	private long PREV_TIME = 100;
	private double[] irArrX;
	private double[] irArrY;
	private static final int IR_ARR_SIZE = 10;
	private long trigTime;
	
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
		
	public void onAccelChanged(float accX, float accY, float accZ) throws InterruptedException{

		deltaX = prevX-accX;
		deltaY = prevY-accY;
		deltaZ = prevZ-accZ;

		if(((deltaX > THRESHOLD && deltaY > THRESHOLD) 
				|| (deltaX > THRESHOLD && deltaZ > THRESHOLD) 
				|| (deltaY > THRESHOLD && deltaZ > THRESHOLD))  
				&& (System.currentTimeMillis() - curTime > UPDATE_TIME))
		{
			//Wait until trig time
			trigTime = TempoClock.getTempoClock().getNextTrigTime(AudioConstants.QUARTER_NOTE);
			while(System.currentTimeMillis() < trigTime){
				//wait();
			}
			trigEvent();
			curTime = System.currentTimeMillis();
		}

		speed(accX-prevX,accY-prevY,accZ-prevZ);
		angle(listener.getRealIrX(),listener.getRealIrY());

		prevX = accX;
		prevY = accY;
		prevZ = accZ;

		if(elapsedTime > PREV_TIME ){	
			elapsedTime = elapsedTime - System.currentTimeMillis();
		}
	}
	
	private void speed(float curX, float curY, float curZ) { 
		shakeVelocity = (float) Math.sqrt(curX*curX+curY*curY+curZ*curZ)/3.0f;
	}
	
	private void angle(double curX, double curY){
		
		System.arraycopy(irArrX, 0, irArrX, 1, IR_ARR_SIZE-1);
		System.arraycopy(irArrY, 0, irArrY, 1, IR_ARR_SIZE-1);
		irArrX[0] = curX;
		irArrY[0] = curY;
		angle = (float) Math.atan2(irArrY[0]-irArrY[IR_ARR_SIZE-1]+0.000001f,
				irArrX[0]-irArrX[IR_ARR_SIZE-1]+0.000001f);
	}
	
	//TODO figure out how to get angle
	public void trigEvent(){		
		int irX = (int) ((listener.getIrX()/WiiMoteConstant.MAX_MOTE_IR_LENGTH)*GUIConstants.WINDOW_WIDTH);
		int irY = (int) ((listener.getIrY()/WiiMoteConstant.MAX_MOTE_IR_LENGTH)*(GUIConstants.WINDOW_HEIGHT));
		//System.out.println("speed: "+shakeVelocity+" angle: "+angle);
		//System.out.println("Quantized to: "+quantizedSpeed());
		
		switch(listener.getBarType()){
		case RADIAL:
			mwp5.addPlayBackBar(PlayBackType.RADIAL, 
					new P5Points2D(irX,irY), quantizedSpeed(), angle);
			listener.setRGBA(255, 255, 255, 125);
			break;
		case RADIAL2:
			mwp5.addPlayBackBar(PlayBackType.RADIAL2, 
					new P5Points2D(irX,irY), quantizedSpeed(), angle);
			listener.setRGBA(100,149,237,125);
			break;
		case BAR:
			mwp5.addPlayBackBar(PlayBackType.BAR, 
					new P5Points2D(irX,irY), quantizedSpeed(), angle);
			listener.setRGBA(124,252,0,125);
			break;
		case SQUAREBAR:
			mwp5.addPlayBackBar(PlayBackType.SQUAREBAR, 
					new P5Points2D(irX,irY),quantizedSpeed(), angle);
			listener.setRGBA(255,255,0,125);
			break;
		case BAR2:
			mwp5.addPlayBackBar(PlayBackType.BAR2, 
					new P5Points2D(irX,irY), quantizedSpeed(), angle);
			listener.setRGBA(178,34,34,125);
			break;
		case CIRCLEFILLBAR:
			mwp5.addPlayBackBar(PlayBackType.CIRCLEFILLBAR, 
					new P5Points2D(irX,irY), quantizedSpeed(), angle);
			listener.setRGBA(255,192,203,125);
			break;
		case CLOCKBAR:
			mwp5.addPlayBackBar(PlayBackType.CLOCKBAR, 
					new P5Points2D(irX,irY), quantizedSpeed(), angle);
			listener.setRGBA(255,165,0,125);
			break;
		}
		trig = true;
	}
	
	/**
	 * 
	 * @return
	 */
	//TODO experiment with quantize values
	private float quantizedSpeed() {
		int note=0;
		if(shakeVelocity > 0.5){
			note = AudioConstants.SIXTEENTH_NOTE;
		}
		else{
			note = AudioConstants.QUARTER_NOTE;
		}
	
		//TODO perhaps fix this equation
		return (P5Constants.MIN_TRAVEL_DISTANCE*note)/P5Constants.FRAME_RATE;
	}

	public boolean isTriggered() {
		return trig;
	}

	public void setTrigger(boolean trig) {
		this.trig = trig;
	}
}
