package net.gtcmt.audiosketch.wiimote;

import wiiremotej.WiiRemote;
import wiiremotej.WiiRemoteJ;

public class TestWii {

	public TestWii(){
		
	}
	
	public static void main(String[] args){
		System.setProperty("bluecove.jsr82.psm_minimum_off", "true");
		//Find and connect to a Wii Remote
        WiiRemote remote = null;
        
        while (remote == null) {
            try {
                remote = WiiRemoteJ.connectToRemote("001A19C0F036");
            }
            catch(Exception e) {
                remote = null;
                e.printStackTrace();
                System.out.println("Failed to connect remote. Trying again.");
            }
        }
        
        System.out.println("connected to remote: "+remote.getBluetoothAddress());
        WiiRemote remote2 = null;
        while (remote == null) {
            try {
                remote2 = WiiRemoteJ.connectToRemote("001BEA04CFBE");
            }
            catch(Exception e) {
                remote2 = null;
                e.printStackTrace(); 
                System.out.println("Failed to connect remote2. Trying again.");
            }
        }
        
        System.out.println("connected to remote: "+remote2.getBluetoothAddress());
        
        try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
