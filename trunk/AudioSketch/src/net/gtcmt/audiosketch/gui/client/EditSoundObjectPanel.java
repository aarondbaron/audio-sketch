package net.gtcmt.audiosketch.gui.client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.gtcmt.audiosketch.gui.util.GUIConstants;
import net.gtcmt.audiosketch.network.client.Client;
import net.gtcmt.audiosketch.network.data.AudioSketchData;
import net.gtcmt.audiosketch.network.data.SoundObjectData;
import net.gtcmt.audiosketch.network.util.MsgType;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Size2D;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Constants.ObjectColorType;
import net.gtcmt.audiosketch.p5.util.P5Constants.ObjectShapeType;
import net.gtcmt.audiosketch.p5.window.ObjectWindow;
import net.gtcmt.audiosketch.sound.synth.Blip;
import net.gtcmt.audiosketch.sound.synth.Buzz;
import net.gtcmt.audiosketch.sound.synth.InharmonicBell;
import net.gtcmt.audiosketch.sound.synth.RandomSig;
import net.gtcmt.audiosketch.sound.synth.Ring;
import net.gtcmt.audiosketch.sound.synth.Shir;
import net.gtcmt.audiosketch.sound.util.SndConstants;
import net.gtcmt.audiosketch.sound.util.SndConstants.SndType;
import ddf.minim.Minim;

/**
 * This panel allows you to edit shape of sound object.
 * @author akito
 *
 */
public class EditSoundObjectPanel extends JPanel {

	private static final long serialVersionUID = 1070795152943741444L; 
	private JComboBox colorChooser;			// Choose color of sound object
	private JComboBox shapeChooser;			// Choose shape of sound object
	private JComboBox midiNoteChooser;		// Choose midi note of synth sound
	private JComboBox soundChooser;			// Choose synth sound
	private ObjectShapeType objectShape;			// Sound object type
	private ObjectColorType objectColor;			// Sound object color
	private int midiIndex;					// midi note index obtained from combo box
	private SndType sndType;					// sound type Index obtained from combo box
	private ObjectWindow objectWindow;		// actual window where sound object is displayed
	private JButton playButton;
	private JButton addButton;
	private AudioSketchMainFrame mainFrame;	// Reference to main frame

	/**
	 * Constructor for editing panel. 
	 * @param mainFrame
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public EditSoundObjectPanel(AudioSketchMainFrame mainFrame) throws UnknownHostException, IOException, InterruptedException {
		this.mainFrame = mainFrame;
		objectColor = ObjectColorType.WHITE;
		objectShape = ObjectShapeType.GEAR;
		sndType = SndType.BUZZ;
		
		Box vBox = Box.createVerticalBox();
		Box hBox = Box.createHorizontalBox();

		//Shape and color combo box are created and added
		createObjectStateChooser(hBox);
		hBox.setPreferredSize(new Dimension(GUIConstants.EDITPANEL_WIDTH, 25));
		vBox.add(hBox);
		
		//Create object window
		objectWindow = new ObjectWindow(this);
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(GUIConstants.EDITPANEL_WIDTH, GUIConstants.EDITPANEL_HEIGHT-50));
		panel.add(objectWindow);
		vBox.add(panel);
		
		//Rigid area
		vBox.add(Box.createRigidArea(new Dimension(0, 5)));

		//Create and add config panel
		hBox = Box.createHorizontalBox();
		createConfigLabel(hBox);
		hBox.setPreferredSize(new Dimension(GUIConstants.EDITPANEL_WIDTH, 20));
		vBox.add(hBox);
		
		hBox = Box.createHorizontalBox();
		createConfig(hBox);
		hBox.setPreferredSize(new Dimension(GUIConstants.EDITPANEL_WIDTH, 25));
		vBox.add(hBox);
		
		//Add the whole thing to panel
		add(vBox);
		repaint();
		validate();
	}
	
	/**
	 * Shape chooser is a drop down menu  that allows user to select 
	 * what kind of shape to use.
	 */
	private void createObjectStateChooser(Box hBox){
		JLabel label = new JLabel("Color:");
		hBox.add(label);
		
		colorChooser = new JComboBox(P5Constants.COLOR_LIST);
		colorChooser.setSelectedIndex(0);
		colorChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JComboBox box = (JComboBox) e.getSource();
					objectColor = ObjectColorType.values()[box.getSelectedIndex()];
			}
		});
		hBox.add(colorChooser);
		
		label = new JLabel("Shape:");
		hBox.add(label);
		
		shapeChooser = new JComboBox(P5Constants.SHAPE_LIST);
		shapeChooser.setSelectedIndex(0);
		shapeChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JComboBox box = (JComboBox) e.getSource();
					objectShape = ObjectShapeType.values()[box.getSelectedIndex()];
			}
		});
		hBox.add(shapeChooser);
	}

	/**
	 * Labels for configuration panel
	 */
	private void createConfigLabel(Box hBox){
		JLabel label = new JLabel("Midi Note:");
		hBox.add(label);
		hBox.add(Box.createRigidArea(new Dimension(10, 20)));
		label = new JLabel("Instrument:");
		hBox.add(label);
		hBox.add(Box.createRigidArea(new Dimension(160, 20)));
	}
	
	/**
	 * Configuration panels. Things like midi note, sound name, play, and add button are created here
	 */
	private void createConfig(Box hBox){
		String[] midiNote = new String[SndConstants.MAX_MIDI-SndConstants.MIN_MIDI+1];
		for(int i=SndConstants.MIN_MIDI; i<=SndConstants.MAX_MIDI;i++){
			midiNote[i-SndConstants.MIN_MIDI] = SndConstants.MIDI_NAME[(i-SndConstants.MIN_MIDI)%12] +" "+ String.valueOf(i);
		}
		
		//Midi note combo box
		midiNoteChooser = new JComboBox(midiNote);
		midiNoteChooser.setSelectedIndex(57);
		midiNoteChooser.setPreferredSize(new Dimension(60, 25));
		midiNoteChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JComboBox box = (JComboBox) e.getSource();
					midiIndex = box.getSelectedIndex();
			}
		});		
		hBox.add(midiNoteChooser);
		
		//Sound name combo box
		soundChooser = new JComboBox(SndConstants.SOUND_NAME);
		soundChooser.setPreferredSize(new Dimension(70, 25));
		soundChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JComboBox box = (JComboBox) e.getSource();
					sndType = SndType.values()[box.getSelectedIndex()];
			}
		});
		hBox.add(soundChooser);
		
		//Preview sound
		playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getObjectWindow().audioOut != null && getObjectWindow().audioOut.signalCount() > 0)
					getObjectWindow().audioOut.removeSignal(0);
				getObjectWindow().audioOut = getObjectWindow().minim.getLineOut(Minim.STEREO, 1024);
				switch(sndType){
				case BUZZ: new Thread(new Buzz(getObjectWindow().audioOut, midiNoteChooser.getSelectedIndex()+SndConstants.MIN_MIDI)).start(); break;
				case RANDOM: new Thread(new RandomSig(getObjectWindow().audioOut, midiNoteChooser.getSelectedIndex()+SndConstants.MIN_MIDI)).start(); break;
				case INHARMONIC_BELL: new Thread(new InharmonicBell(getObjectWindow().audioOut, midiNoteChooser.getSelectedIndex()+SndConstants.MIN_MIDI)).start(); break;
				case RING: new Thread(new Ring(getObjectWindow().audioOut, midiNoteChooser.getSelectedIndex()+SndConstants.MIN_MIDI)).start(); break;
				case BLIP: new Thread(new Blip(getObjectWindow().audioOut, midiNoteChooser.getSelectedIndex()+SndConstants.MIN_MIDI)).start(); break;
				case SHIR: new Thread(new Shir(getObjectWindow().audioOut, midiNoteChooser.getSelectedIndex()+SndConstants.MIN_MIDI)).start(); break;
				}
			}
		});
		hBox.add(playButton);
		
		//Add button to add it to musical window
		addButton = new JButton("Add");
		hBox.setPreferredSize(new Dimension(GUIConstants.EDITPANEL_WIDTH, 30));
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Send message to server
				getClient().sendData(new AudioSketchData(MsgType.ADD_OBJECT, 
						new SoundObjectData(objectShape, objectColor, sndType, 
						new P5Points2D((int) (Math.random()*500), (int) (Math.random()*500)), 
						new P5Size2D(objectWindow.getObjectWidth(), objectWindow.getObjectHeight()), midiIndex), 
						mainFrame.getUserName(), 0));
			}
		});
		hBox.add(addButton);
	}
	
	/*---------------------- Getter/Setter -------------------*/
	public AudioSketchMainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(AudioSketchMainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	public ObjectWindow getObjectWindow() {
		return objectWindow;
	}

	public ObjectShapeType getObjectShape() {
		return objectShape;
	}

	public void setObjectShape(ObjectShapeType objectShape) {
		this.objectShape = objectShape;
	}

	public ObjectColorType getObjectColor() {
		return objectColor;
	}

	public void setObjectColor(ObjectColorType objectColor) {
		this.objectColor = objectColor;
	}

	public Client getClient() {
		return mainFrame.getClient();
	}

	/**
	 * Test editing panel
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		
		JFrame frame = new JFrame();
		EditSoundObjectPanel panel = new EditSoundObjectPanel(null);
		panel.getObjectWindow().init();
		frame.add(panel);
		frame.setSize(310, 360);
		frame.setLocation(0, 0);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt){
				System.exit(0);
			}
		});
	}
}
