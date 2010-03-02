package net.gtcmt.audiosketch.wii;

import motej.Mote;
import motej.MoteFinder;
import motej.MoteFinderListener;
import motej.request.ReportModeRequest;
import net.gtcmt.audiosketch.p5.window.MusicalWindow;
import net.gtcmt.audiosketch.util.LogMessage;

public class MoteConnector implements MoteFinderListener {

	private MoteFinder finder;
	private Mote mote;
	private Object lock;
	
	public MoteConnector(MusicalWindow mwp5){
		lock = new Object();
		WiiMoteListener moteListener = new WiiMoteListener(mwp5);
		Mote mote = findMote();
		mote.addAccelerometerListener(moteListener.getAccelListener());		
		mote.addCoreButtonListener(moteListener.getButtonListener());
		mote.addIrCameraListener(moteListener.getIrListener());
		mote.enableIrCamera();
		mote.setReportMode(ReportModeRequest.DATA_REPORT_0x37);
	}
	
	public void moteFound(Mote mote) {
		this.mote = mote;
		LogMessage.info("Found a mote.");
		synchronized(lock) {
			lock.notifyAll();
		}
	}
	
	public Mote findMote() {
		if (finder == null) {
			finder = MoteFinder.getMoteFinder();
			finder.addMoteFinderListener(this);
		}
		finder.startDiscovery();
		try {
			synchronized(lock) {
				lock.wait();
			}
		} catch (InterruptedException ex) {
			LogMessage.err(ex.getMessage());
		}
		return mote;
	}
}
