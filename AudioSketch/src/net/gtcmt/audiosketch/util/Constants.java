package net.gtcmt.audiosketch.util;

import java.io.File;
import java.io.FilenameFilter;

public class Constants {

	public static final String SOUND_OBJECT_PATH = "lib/image/";
	public static final String SOUND_PATH = "lib/max_patches/";
	
	
	//Sound files
	public static File soundDir = new File(Constants.SOUND_OBJECT_PATH);
	public static FilenameFilter filter2 = new FilenameFilter() {
	    public boolean accept(File soundDir, String name) {
	        return name.endsWith(".wav");
	    }
	};
	public static String[] children2 = soundDir.list(filter2);		
	public static String[] SOUND_NAME = children2;
	public static int NUM_SOUNDS = children2.length;
	
}
