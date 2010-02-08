package net.gtcmt.audiosketch;

import java.awt.Cursor;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.io.IOException;
import java.net.UnknownHostException;

import net.gtcmt.audiosketch.gui.client.AudioSketchMainFrame;
import net.gtcmt.audiosketch.gui.util.GUIConstants;
import net.gtcmt.audiosketch.network.client.Client;
import net.gtcmt.audiosketch.network.server.ServerNetwork;
import net.gtcmt.audiosketch.util.LogMessage;

public class AudioSketchAudienceView extends AudioSketchMainFrame {

	private static final long serialVersionUID = 1L;
	
	public AudioSketchAudienceView(Client client) throws UnknownHostException, IOException, InterruptedException{
		super(client);
	}
	
	public static void main(String[] args) {

		Client client = new Client("localhost", ServerNetwork.DEFAULT_PORT);
		client.start();
		
		AudioSketchAudienceView audienceView = null;
		try {
			audienceView = new AudioSketchAudienceView(client);
		} catch (Throwable e) {
			LogMessage.javaErr(e);
		}
				
		//Reference to main frame is passed
		client.setMainFrame(audienceView);
		
		//Make cursor invisible
		int[] pixels = new int[16 * 16];
		Image image = Toolkit.getDefaultToolkit().createImage(
		        new MemoryImageSource(16, 16, pixels, 0, 16));
		Cursor transparentCursor =
		        Toolkit.getDefaultToolkit().createCustomCursor
		             (image, new Point(0, 0), "invisibleCursor");
		audienceView.setCursor(transparentCursor);
		
		GraphicsDevice dev = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(); 
		DisplayMode mode = new DisplayMode(GUIConstants.WINDOW_WIDTH, GUIConstants.WINDOW_HEIGHT, 32, DisplayMode.REFRESH_RATE_UNKNOWN);

		dev.setFullScreenWindow(audienceView);
		if(dev.isDisplayChangeSupported()) dev.setDisplayMode(mode);
	}
}
