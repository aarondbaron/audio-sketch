package net.gtcmt.audiosketch.gui.client;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.MemoryImageSource;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedList;

import javax.swing.JFrame;

import net.gtcmt.audiosketch.gui.util.GUIConstants;
import net.gtcmt.audiosketch.p5.object.TableMessageRouter;
import net.gtcmt.audiosketch.p5.window.MusicalWindow;
import net.gtcmt.audiosketch.wii.MoteConnector;
import net.gtcmt.audiosketch.wii.util.WiiMoteConstant;

/**
 * Put together all the gui elements
 * @author akito
 *
 */
public class AudioSketchMainFrame extends JFrame {

	private static final long serialVersionUID = -454422848366277633L;
	private MusicalWindow musicalWindow;
	private LinkedList<MoteConnector> connector;	
	
	/**
	 * Constructor for starting main client gui panel
	 * @param app
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public AudioSketchMainFrame(String appName) {
		super(appName);
		
		//set up gui
		musicalWindow = new MusicalWindow(this);
		musicalWindow.init();
		add(musicalWindow);
		
		//Add action listener on application quit
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt){
				System.exit(0);
			}
		});
		
		setUndecorated(true);
		
		//configure main frame 
		setSize(GUIConstants.WINDOW_WIDTH, GUIConstants.WINDOW_HEIGHT);
		setLocation(0, 0);
		setVisible(true);
		
		//Make cursor invisible
		int[] pixels = new int[16 * 16];
		Image image = Toolkit.getDefaultToolkit().createImage(
				new MemoryImageSource(16, 16, pixels, 0, 16));
		Cursor transparentCursor =
			Toolkit.getDefaultToolkit().createCustomCursor
			(image, new Point(0, 0), "invisibleCursor");
		setCursor(transparentCursor);

//		GraphicsDevice dev = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(); 
//		DisplayMode mode = new DisplayMode(GUIConstants.WINDOW_WIDTH, GUIConstants.WINDOW_HEIGHT, 32, DisplayMode.REFRESH_RATE_UNKNOWN);
//
//		dev.setFullScreenWindow(this);
//		if(dev.isDisplayChangeSupported()) dev.setDisplayMode(mode);
		
		//reacTVision related stuff
		new TableMessageRouter(musicalWindow);

		//Connect to wiimote
		connector = new LinkedList<MoteConnector>();
		for(int i=0;i<WiiMoteConstant.MOTE_MAC_ADDR.length;i++){
			MoteConnector mc = new MoteConnector(WiiMoteConstant.MOTE_MAC_ADDR[i], musicalWindow);
			//mc.start();
			connector.add(mc);
		}
		//Add pointer to musical window
		musicalWindow.initPointer(connector);
	}

	/*----------------- Getter/Setter ----------------*/
	public MusicalWindow getMusicalWindow() {
		return musicalWindow;
	}

	public void setMusicalWindow(MusicalWindow musicalWindow) {
		this.musicalWindow = musicalWindow;
	}
}
