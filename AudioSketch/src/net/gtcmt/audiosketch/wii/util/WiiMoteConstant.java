package net.gtcmt.audiosketch.wii.util;

import java.util.Hashtable;



public class WiiMoteConstant {

	public static final float MAX_MOTE_IR_LENGTH = 1000f;
	public static final String[] MOTE_MAC_ADDR = {"001BEA04CFBE", "001A19C0F036"};//,"00191D5367E7"};
	
	public static java.util.Hashtable<String, int[]> wiiMoteCalData;
	//Wii mote with address 00191D5367E7 
	public static final int[] eSevenCalibration = {126,127,122,25,26,27};
	
	public static void initCalData(){
		wiiMoteCalData =  new Hashtable<String, int[]>();
		wiiMoteCalData.put(MOTE_MAC_ADDR[0], eSevenCalibration);
		wiiMoteCalData.put(MOTE_MAC_ADDR[1], eSevenCalibration);
	}
}
