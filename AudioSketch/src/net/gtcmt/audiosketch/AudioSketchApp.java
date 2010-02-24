package net.gtcmt.audiosketch;

import net.gtcmt.audiosketch.gui.client.AudioSketchMainFrame;

public class AudioSketchApp extends AudioSketchMainFrame {

	private static final long serialVersionUID = -6059710804291109079L;

	public AudioSketchApp() {
		super("Audio Sketch");
	}
	
	public static void main(String[] args) {

		/*AudioSketchApp audioSketch =*/ new AudioSketchApp();
		
		//Make cursor invisible
/*		int[] pixels = new int[16 * 16];
		Image image = Toolkit.getDefaultToolkit().createImage(
		        new MemoryImageSource(16, 16, pixels, 0, 16));
		Cursor transparentCursor =
		        Toolkit.getDefaultToolkit().createCustomCursor
		             (image, new Point(0, 0), "invisibleCursor");
		audioSketch.setCursor(transparentCursor);
		
		GraphicsDevice dev = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(); 
		DisplayMode mode = new DisplayMode(GUIConstants.WINDOW_WIDTH, GUIConstants.WINDOW_HEIGHT, 32, DisplayMode.REFRESH_RATE_UNKNOWN);

		dev.setFullScreenWindow(audioSketch);
		if(dev.isDisplayChangeSupported()) dev.setDisplayMode(mode);
	*/
	}
}
