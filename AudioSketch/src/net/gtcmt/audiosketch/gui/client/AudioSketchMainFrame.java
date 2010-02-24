package net.gtcmt.audiosketch.gui.client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import net.gtcmt.audiosketch.gui.util.GUIConstants;
import net.gtcmt.audiosketch.p5.window.MusicalWindow;

/**
 * Put together all the gui elements
 * @author akito
 *
 */
public class AudioSketchMainFrame extends JFrame {

	private static final long serialVersionUID = -454422848366277633L;
	private String userName;
	private ActionPanel actionPanel;
	private MusicalWindow musicalWindow;
	private EditSoundObjectPanel  editPanel;
	
	/**
	 * Constructor for starting audience view
	 * @param client
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public AudioSketchMainFrame() {
		super("Audience View");
		
		this.userName = "Audience";
		
		initGUI();
		musicalWindow.init();
		editPanel.getObjectWindow().init();
		
		//Add action listener on application quit
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt){
				System.exit(0);
			}
		});
	
		add(musicalWindow);
		
		setUndecorated(true);
		setLocationRelativeTo(null);
		setSize(GUIConstants.WINDOW_WIDTH, GUIConstants.WINDOW_HEIGHT);
		setLocation(0, 0);
		setVisible(true);
		repaint();
		validate();
	}
	
	/**
	 * Constructor for starting main client gui panel
	 * @param app
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public AudioSketchMainFrame(String userName) {
		super("Audio Sketch");
		
		this.userName = userName;
		
		//set up gui
		setupGUI();
		
		//Initialize processing windows
		musicalWindow.init();
		editPanel.getObjectWindow().init();
		
		//Add action listener on application quit
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt){
				System.exit(0);
			}
		});
		
		//configure main frame 
		setSize(GUIConstants.FRAME_WIDTH, GUIConstants.FRAME_HEIGHT);
		setLocation(0, 0);
		setVisible(true);
		validate();
		repaint();
	}
	
	/**
	 * Sets up gui
	 * @param clientApp reference to client application
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void setupGUI() {
		Box hBox = Box.createHorizontalBox();
		Box vBox = Box.createVerticalBox();
		
		//Main Window
		musicalWindow = new MusicalWindow(this);
		JPanel panel = new JPanel();
		panel.add(musicalWindow);
		hBox.add(panel);
						
		//Vertical seperator
		hBox.add(new JSeparator(SwingConstants.VERTICAL));
		
		//Object Editing Panel
		editPanel = new EditSoundObjectPanel(this);
		vBox.add(editPanel);
		
		//Horizontal seperator
		vBox.add(new JSeparator(SwingConstants.HORIZONTAL));

		//Action Panel
		actionPanel = new ActionPanel(this);
		vBox.add(actionPanel);	

		//Add to frame
		hBox.add(vBox);		
		add(hBox);
	}
	
	
	private void initGUI() {		
		//Main Window
		musicalWindow = new MusicalWindow(this);		
		//Action Panel
		actionPanel = new ActionPanel(this);
		//Object Editing Panel
		editPanel = new EditSoundObjectPanel(this);
	}
	
	/*----------------- Getter/Setter ----------------*/
	public MusicalWindow getMusicalWindow() {
		return musicalWindow;
	}

	public void setMusicalWindow(MusicalWindow musicalWindow) {
		this.musicalWindow = musicalWindow;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ActionPanel getActionPanel() {
		return actionPanel;
	}

	public void setActionPanel(ActionPanel actionPanel) {
		this.actionPanel = actionPanel;
	}

	public EditSoundObjectPanel getEditPanel() {
		return editPanel;
	}

	public void setEditPanel(EditSoundObjectPanel editPanel) {
		this.editPanel = editPanel;
	}	
}
