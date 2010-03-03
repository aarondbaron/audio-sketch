package net.gtcmt.audiosketch.p5.util;

import java.io.File;
import java.io.FilenameFilter;

import net.gtcmt.audiosketch.util.Constants;

public class P5Constants {

	//Playback bar 
	public enum PlayBackType{
		BAR, RADIAL, RADIAL2, BAR2, CLOCKBAR, SQUAREBAR, CIRCLEFILLBAR
	}
	
	public static final String[] BAR_TYPE = {"Bar", "Radial", "Radial2" ,"Bar2", "ClockBar","SquareBar","CircleFillBar"};
	public static final int MIN_SPEED=1;
	public static final int MAX_SPEED=11;
	public static final float MAX_TRIG_DISTANCE = 20;
	public static final double NINETY = 1.5707963267948966;
	public static final int BAR_WIDTH = 1400;
	public static final int COLLISION_AREA=80000;
	
	//Sound object
	
	//read all .svg files in the Sound object path
	public static File dir = new File(Constants.SOUND_OBJECT_PATH);
	public static FilenameFilter filter = new FilenameFilter() {
	    public boolean accept(File dir, String name) {
	        return name.endsWith(".svg");
	    }
	};
	public static String[] children = dir.list(filter);		
	public static String[] SHAPE_NAME = children;
	public static int NUM_SHAPE = children.length;

	
	
	
	
	
	public enum ObjectColorType{
		WHITE, BLUE, GREEN, YELLOW, ORANGE
	}
	public static final String[] COLOR_LIST = { "White", "Blue", "Green", "Yellow", "Orange" };

}
