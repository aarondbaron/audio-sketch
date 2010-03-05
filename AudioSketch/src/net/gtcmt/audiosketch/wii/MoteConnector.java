package net.gtcmt.audiosketch.wii;

import motej.Mote;
import motej.request.ReportModeRequest;
import net.gtcmt.audiosketch.p5.window.MusicalWindow;

public class MoteConnector extends Thread {

	private Mote mote;
	private WiiMoteListener moteListener;
	private String address;
	
	public MoteConnector(String address, MusicalWindow mwp5){
		this.address = address;
		moteListener = new WiiMoteListener(mwp5, address);
	}
	
	/**
	 * Finds wiimote and add appropriate action to it. It continues to 
	 * listen while thread is running.
	 */
	public void run(){
		mote = new Mote(address);
		mote.setReportMode(ReportModeRequest.DATA_REPORT_0x31);
		mote.addAccelerometerListener(moteListener.getAccelListener());		
		mote.addCoreButtonListener(moteListener.getButtonListener());
		mote.addIrCameraListener(moteListener.getIrListener());
		mote.enableIrCamera();
		mote.setReportMode(ReportModeRequest.DATA_REPORT_0x37);
		System.out.println("found mote with address: "+address);
	}
	
	public void shutdown(){
		mote.setReportMode(ReportModeRequest.DATA_REPORT_0x30);
		mote.disconnect();
		this.interrupt();
	}
	public WiiMoteListener getMoteListener() {
		return moteListener;
	}
}
