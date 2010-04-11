package net.gtcmt.audiosketch.wii;

import motej.CalibrationDataReport;
import motej.Mote;
import motej.event.AccelerometerEvent;
import motej.event.AccelerometerListener;
import motej.event.CoreButtonEvent;
import motej.event.CoreButtonListener;
import motej.event.IrCameraEvent;
import motej.event.IrCameraListener;
import net.gtcmt.audiosketch.p5.util.P5Color;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import net.gtcmt.audiosketch.p5.window.MusicalWindow;
import net.gtcmt.audiosketch.util.LogMessage;
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
	private boolean fillBarMovesItself;
	
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
		fillBarMovesItself = false;
		
		int[] calData = WiiMoteConstant.wiiMoteCalData.get(address);
		//This is a cal data for accelerometer
		report = new CalibrationDataReport(calData[0],calData[1],calData[2],calData[3],calData[4],calData[5]);
		
		buttonListener = new CoreButtonListener() {

			public void buttonPressed(CoreButtonEvent evt) {
				if(evt.isButtonBPressed()){
					if(!fillBarMovesItself){
						fillBarMovesItself = true;
					}
				}
				else if(evt.isButtonMinusPressed()){
					if(mwp5.getPlayBarSize() > 0){
						if(!removePressed){
							removePressed = true;
							mwp5.removeLastPlayBar();
						}
					}
				}
				else if(evt.isButtonAPressed()){
					if(!lockPressed){
						lockPressed = true;
						lock = !lock;
					}
				}
				else if(evt.isDPadDownPressed()){
					if(!padDownPressed){
						padDownPressed = true;
						countBar = (countBar+1)%PlayBackType.values().length;
						barType = PlayBackType.values()[countBar];
						System.out.println("BARTYPE: "+barType);
						switch(barType){
						case RADIAL:		setRGBA(P5Color.ALICE_BLUE,125);		break;
						case RADIAL2:		setRGBA(P5Color.BABY_BLUE,125);			break;
						case CIRCLEFILLBAR:	setRGBA(P5Color.ELECTRIC_BLUE,125);		break;
						case SQUAREBAR:		setRGBA(P5Color.MOSS_GREEN,125);		break;
						case SQUAREBAR2:	setRGBA(P5Color.LIME, 125);				break;
						case SQUAREFILLBAR:	setRGBA(P5Color.ELECTRIC_GREEN,125);	break;
						case BAR:			setRGBA(124,252,0,125);					break;
						}
					}
				}
				else if(evt.isNoButtonPressed()){
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
				try {
					shake.onAccelChanged((float)(evt.getX()-report.getZeroX())/(float)report.getGravityX(),
							(float)(evt.getY()-report.getZeroY())/(float)report.getGravityY(),
							(float)(evt.getZ()-report.getZeroZ())/(float)report.getGravityZ());
				} catch (InterruptedException e) {
					LogMessage.javaErr(e);
				}
			}
		};
		
		irListener = new IrCameraListener() {
			public void irImageChanged(IrCameraEvent evt) {
				
				if(!lock){
					irX = WiiMoteConstant.MAX_MOTE_IR_WIDTH-evt.getIrPoint(0).getX();
					irY = evt.getIrPoint(0).getY();
				}

				realIrX = WiiMoteConstant.MAX_MOTE_IR_WIDTH-evt.getIrPoint(0).getX();
				realIrY = evt.getIrPoint(0).getY();
				
				//System.out.println("IrX "+realIrX+" IrY " +realIrY);

			}	
		};
	}

	public boolean isFillBarMovesItself() {
		return fillBarMovesItself;
	}

	public void setFillBarMovesItself(boolean fillBarMovesItself) {
		this.fillBarMovesItself = fillBarMovesItself;
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

	public void setRGBA(int[] color, int a){
		red = color[0];
		green = color[1];
		blue = color[2];
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
