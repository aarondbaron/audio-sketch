package net.gtcmt.audiosketch.p5.util;

public class P5Constants {

	//Playback bar 
	public enum PlayBackType{
		BAR, RADIAL, RADIAL2, BAR2, CLOCKBAR
	}
	
	public static final String[] BAR_TYPE = {"Bar", "Radial", "Radial2", "Bar2", "ClockBar"};
	public static final int MIN_SPEED=1;
	public static final int MAX_SPEED=11;
	public static final float MAX_TRIG_DISTANCE = 20;
	public static final double NINETY = 1.5707963267948966;
	public static final int BAR_WIDTH = 1400;
	public static final int COLLISION_AREA=80000;
	
	//Sound object
	public static final int NUM_SHAPE = 3;
	public static final String[] SHAPE_NAME = {"Gear.svg","Triangle.svg","Star.svg"};
	
	public enum ObjectShapeType{
		GEAR, TRIANGLE, STAR
	}
	public static final String[] SHAPE_LIST = { "Gear", "Triangle", "Star" }; 
	
	public enum ObjectColorType{
		WHITE, BLUE, GREEN, YELLOW, ORANGE
	}
	public static final String[] COLOR_LIST = { "White", "Blue", "Green", "Yellow", "Orange" };

}
