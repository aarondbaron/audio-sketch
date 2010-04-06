package net.gtcmt.audiosketch;

import java.awt.Cursor;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;

import net.gtcmt.audiosketch.gui.client.AudioSketchMainFrame;
import net.gtcmt.audiosketch.gui.util.GUIConstants;
import net.gtcmt.audiosketch.wii.util.WiiMoteConstant;

import com.intel.bluetooth.BlueCoveConfigProperties;

public class AudioSketchApp extends AudioSketchMainFrame {

	private static final long serialVersionUID = -6059710804291109079L;

	public AudioSketchApp() {
		super("Audio Sketch");
	}

	public static void main(String[] args) {

		//System.setProperty("java.library.path", System.getProperty("user.dir")+"/lib/");
		//System.out.println("java.library.path "+System.getProperty("java.library.path"));
		
		//Magic code to get motej recognize wiimote right away!!
		//System.setProperty("bluecove.jsr82.psm_minimum_off", "true");
		System.setProperty(BlueCoveConfigProperties.PROPERTY_JSR_82_PSM_MINIMUM_OFF, "true");
		//You can also do this. "java -Dbluecove.jsr82.psm_minimum_off=true  yourClass" as java argument

		//Initialize mote cal data
		WiiMoteConstant.initCalData();
		
		AudioSketchApp audioSketch = new AudioSketchApp();	
	}
}
