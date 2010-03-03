package net.gtcmt.audiosketch.wii;

import motej.Mote;
import motej.event.AccelerometerEvent;
import motej.event.AccelerometerListener;
import motej.event.CoreButtonEvent;
import motej.event.CoreButtonListener;
import motej.event.IrCameraEvent;
import motej.event.IrCameraListener;
import net.gtcmt.audiosketch.p5.window.MusicalWindow;
import net.gtcmt.audiosketch.wii.util.WiiConstant;

public class WiiMoteListener {

	private AccelerometerListener<Mote> accelListener;
	private CoreButtonListener buttonListener;
	private int buttonState;
	private IrCameraListener irListener;
	private double irX;
	private double irY;
	private ShakeWii shake;

	public WiiMoteListener(MusicalWindow mwp5){
		buttonListener = new CoreButtonListener() {
			public void buttonPressed(CoreButtonEvent evt) {
				buttonState = evt.getButton();
			}		
		};
		
		shake = new ShakeWii(mwp5,this);
		accelListener = new AccelerometerListener<Mote>() {
			public void accelerometerChanged(AccelerometerEvent<Mote> evt) {
				//System.out.println(evt.getX() + " : " + evt.getY() + " : " + evt.getZ());
				shake.onAccelChanged(evt.getX(),evt.getY(),evt.getZ());
			}
		};
		
		irListener = new IrCameraListener() {
			public void irImageChanged(IrCameraEvent evt) {
				irX = WiiConstant.MAX_MOTE_IR_LENGTH-((evt.getIrPoint(0).getX()-evt.getIrPoint(1).getX())/2.0f);
				irY = WiiConstant.MAX_MOTE_IR_LENGTH-((evt.getIrPoint(0).getY()-evt.getIrPoint(1).getY())/2.0f);
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

	public int getButtonState() {
		return buttonState;
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
}
