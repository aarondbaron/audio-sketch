package net.gtcmt.audiosketch.wii;

import motej.CalibrationDataReport;
import motej.Mote;
import motej.event.AccelerometerEvent;
import motej.event.AccelerometerListener;
import motej.event.CoreButtonEvent;
import motej.event.CoreButtonListener;
import motej.event.IrCameraEvent;
import motej.event.IrCameraListener;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import net.gtcmt.audiosketch.p5.window.MusicalWindow;
import net.gtcmt.audiosketch.wii.util.WiiMoteConstant;

public class WiiMoteListener {

	private AccelerometerListener<Mote> accelListener;
	private CoreButtonListener buttonListener;
	private IrCameraListener irListener;
	private double irX;
	private double irY;
	private ShakeWii shake;
	private boolean lock;
	private PlayBackType barType;
	private int countBar;
	private int red;
	private int green;
	private int blue;
	private int alpha;
	private boolean padDownPressed;
	private boolean lockPressed;
	private boolean removePressed;
	private CalibrationDataReport report;
	private double realIrX;
	private double realIrY;
	
	public WiiMoteListener(final MusicalWindow mwp5, String address){
		lock = false;
		red = 255;
		green = 0;
		blue = 255;
		alpha = 125;
		countBar = 0;
		barType = PlayBackType.BAR;
		lockPressed = false;
		padDownPressed = false;
		removePressed = false;
		
		int[] calData = WiiMoteConstant.wiiMoteCalData.get(address);
		//This is a cal data for accelerometer
		report = new CalibrationDataReport(calData[0],calData[1],calData[2],calData[3],calData[4],calData[5]);
		
		buttonListener = new CoreButtonListener() {

			public void buttonPressed(CoreButtonEvent evt) {		
				if(evt.isButtonMinusPressed()){
					if(mwp5.getPlayBarSize() > 0){
						if(!removePressed){
							removePressed = true;
							mwp5.removeLastPlayBar();
						}
					}
				}
				if(evt.isButtonAPressed()){
					if(!lockPressed){
						lockPressed = true;
						lock = !lock;
					}
				}
				if(evt.isDPadDownPressed()){
					if(!padDownPressed){
						padDownPressed = true;
						barType = PlayBackType.values()[countBar++%PlayBackType.values().length];
						switch(barType){
						case RADIAL:
							setRGBA(255, 255, 255, 125);
							break;
						case RADIAL2:
							setRGBA(100,149,237,125);
							break;
						case BAR:
							setRGBA(124,252,0,125);
							break;
						case SQUAREBAR:
							setRGBA(255,255,0,125);
							break;
						case BAR2:
							setRGBA(178,34,34,125);
							break;
						case CIRCLEFILLBAR:
							setRGBA(255,192,203,125);
							break;
						case CLOCKBAR:
							setRGBA(255,165,0,125);
							break;
						}
					}
				}
				if(evt.isNoButtonPressed()){
					if(padDownPressed){
						padDownPressed = false;
					}
					if(lockPressed){
						lockPressed = false;
					}
					if(removePressed){
						removePressed = false;
					}
				}
			}		
		};
		
		shake = new ShakeWii(mwp5, this);
		accelListener = new AccelerometerListener<Mote>() {
			public void accelerometerChanged(AccelerometerEvent<Mote> evt) {
				//System.out.println(evt.getX() + " : " + evt.getY() + " : " + evt.getZ());
				shake.onAccelChanged((float)(evt.getX()-report.getZeroX())/(float)report.getGravityX(),
						(float)(evt.getY()-report.getZeroY())/(float)report.getGravityY(),
						(float)(evt.getZ()-report.getZeroZ())/(float)report.getGravityZ());
			}
		};
		
		irListener = new IrCameraListener() {
			public void irImageChanged(IrCameraEvent evt) {
				if(!lock){
					irX = WiiMoteConstant.MAX_MOTE_IR_LENGTH-evt.getIrPoint(0).getX();
					irY =evt.getIrPoint(0).getY();
				}

				realIrX = WiiMoteConstant.MAX_MOTE_IR_LENGTH-evt.getIrPoint(0).getX();
				realIrY = evt.getIrPoint(0).getY();
			}	
		};
	}

	public void resetTrigger(){
		shake.setTrigger(false);
	}
	
	/*------------------ Getter/Setter ---------------------*/
	public AccelerometerListener<Mote> getAccelListener() {
		return accelListener;
	}
	
	public CoreButtonListener getButtonListener() {
		return buttonListener;
	}

	public IrCameraListener getIrListener() {
		return irListener;
	}

	public double getIrX() {
		return irX;
	}

	public double getIrY() {
		return irY;
	}
	
	public ShakeWii getShake() {
		return shake;
	}
	
	public boolean isTriggered(){
		return shake.isTriggered();
	}

	public PlayBackType getBarType() {
		return barType;
	}

	public int getRed() {
		return red;
	}

	public void setRGBA(int r, int g, int b, int a){
		red = r;
		green = g;
		blue = b;
		alpha = a;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public int getAlpha() {
		return alpha;
	}

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	public double getRealIrX() {
		return realIrX;
	}

	public double getRealIrY() {
		return realIrY;
	}
}
