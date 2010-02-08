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
import net.gtcmt.audiosketch.network.client.Client;
import net.gtcmt.audiosketch.network.data.AudioSketchData;
import net.gtcmt.audiosketch.network.data.QuitData;
import net.gtcmt.audiosketch.network.util.MsgType;
import net.gtcmt.audiosketch.p5.window.MusicalWindow;
import net.gtcmt.audiosketch.util.LogMessage;

/**
 * Put together all the gui elements
 * @author akito
 *
 */
public class AudioSketchMainFrame extends JFrame {

	private static final long serialVersionUID = -454422848366277633L;
	private String userName;
	private ChatWindow chat;
	private ActionPanel actionPanel;
	private MusicalWindow musicalWindow;
	private EditSoundObjectPanel  editPanel;
	protected Client client;
	
	public AudioSketchMainFrame(Client client) throws UnknownHostException, IOException, InterruptedException{
		super("Audience View");
		
		this.client = client;
		this.userName = "Audience";
		
		initGUI();
		musicalWindow.init();
		editPanel.getObjectWindow().init();
		
		//Add action listener on application quit
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt){
				try {
					close();
				} catch (IOException e) {
					LogMessage.javaErr(e);
				}
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
	 * Constructor for starting main gui panel
	 * @param app
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public AudioSketchMainFrame(Client client, String userName) throws UnknownHostException, IOException, InterruptedException{
		super("Audio Sketch");
		
		this.client = client;
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
				try {
					close();
				} catch (IOException e) {
					LogMessage.javaErr(e);
				}
				System.exit(0);
			}
		});
		
		//configure main frame 
		setSize(GUIConstants.FRAME_WIDTH, GUIConstants.FRAME_HEIGHT);
		setLocation(0, 0);
		setVisible(true);
		repaint();
		validate();
	}
	
	/**
	 * Sets up gui
	 * @param clientApp reference to client application
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void setupGUI() throws UnknownHostException, IOException, InterruptedException {
		JPanel mainPanel = new JPanel();
		Box hBox = Box.createHorizontalBox();
		Box vBox = Box.createVerticalBox();
		
		//Main Window
		musicalWindow = new MusicalWindow(this);
		JPanel panel = new JPanel();
		panel.add(musicalWindow);
		vBox.add(panel);
		
		//Horizontal separator
		vBox.add(new JSeparator(SwingConstants.HORIZONTAL));
		
		//Action Panel
		actionPanel = new ActionPanel(this);
		panel = new JPanel();
		panel.add(actionPanel);
		vBox.add(panel);	
		hBox.add(vBox);
		
		//Vertical seperator
		hBox.add(new JSeparator(SwingConstants.VERTICAL));
		
		//Object Editing Panel
		editPanel = new EditSoundObjectPanel(this);
		panel = new JPanel();
		panel.add(editPanel);
		vBox = Box.createVerticalBox();
		vBox.add(panel);
		
		//Horizontal seperator
		vBox.add(new JSeparator(SwingConstants.HORIZONTAL));
		
		//Chat Panel
		chat = new ChatWindow(this);
		panel = new JPanel();
		panel.add(chat);
		vBox.add(panel);

		//Add to frame
		hBox.add(vBox);		
		mainPanel.add(hBox);
		add(mainPanel);
	}
	
	
	private void initGUI() throws UnknownHostException, IOException, InterruptedException {		
		//Main Window
		musicalWindow = new MusicalWindow(this);
		
		//Action Panel
		actionPanel = new ActionPanel(this);
		
		//Object Editing Panel
		editPanel = new EditSoundObjectPanel(this);
		
		//Chat Panel
		chat = new ChatWindow(this);

	}
	
	private void close() throws IOException{
		client.sendData(new AudioSketchData(MsgType.QUIT, new QuitData(), userName,0));
		client.shutdown();
	}

	/*----------------- Getter/Setter ----------------*/
	public MusicalWindow getMusicalWindow() {
		return musicalWindow;
	}

	public void setMusicalWindow(MusicalWindow musicalWindow) {
		this.musicalWindow = musicalWindow;
	}
	
	public ChatWindow getChatWindow() {
		return chat;
	}

	public void setChatWindow(ChatWindow chat) {
		this.chat = chat;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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
