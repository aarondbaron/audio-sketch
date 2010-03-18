package net.gtcmt.audiosketch.wii;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import motej.Mote;
import motej.request.ReportModeRequest;
import net.gtcmt.audiosketch.p5.window.MusicalWindow;

public class MoteConnector extends Thread {

	private Mote mote;
	private WiiMoteListener moteListener;
	private String address;
	
	public MoteConnector(String address, MusicalWindow mwp5) {
		this.address = address;
		mote = null;
		while(mote == null){
			System.out.println("Connecting wii remote: "+address);
			mote = new Mote(address);
			if(mote == null){
				System.out.println("Failed to connect: "+address+". Trying again.");
			}
		}
		
		moteListener = new WiiMoteListener(mwp5, address);
		mote.setReportMode(ReportModeRequest.DATA_REPORT_0x31);
		mote.addAccelerometerListener(moteListener.getAccelListener());		
		mote.addCoreButtonListener(moteListener.getButtonListener());
		mote.addIrCameraListener(moteListener.getIrListener());
		mote.enableIrCamera();
		mote.setReportMode(ReportModeRequest.DATA_REPORT_0x37);
		System.out.println("found new wii mote: "+mote.getBluetoothAddress());
		//JOptionPane.showMessageDialog(new JFrame(),"found new wii mote: "+mote.getBluetoothAddress(), "MoteJ", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Finds wiimote and add appropriate action to it. It continues to 
	 * listen while thread is running.
	 */
	public void run(){
		//Currently not used for testing purpoes
		mote = new Mote(address);
		mote.setReportMode(ReportModeRequest.DATA_REPORT_0x31);
		mote.addAccelerometerListener(moteListener.getAccelListener());		
		mote.addCoreButtonListener(moteListener.getButtonListener());
		mote.addIrCameraListener(moteListener.getIrListener());
		mote.enableIrCamera();
		mote.setReportMode(ReportModeRequest.DATA_REPORT_0x37);
		System.out.println("found mote with address: "+address+" "+mote.getStatusInformationReport());
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
