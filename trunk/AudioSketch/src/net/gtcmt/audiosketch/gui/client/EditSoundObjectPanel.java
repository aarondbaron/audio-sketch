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
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Size2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.ObjectColorType;
//import net.gtcmt.audiosketch.p5.util.P5Constants.ObjectShapeType;
import net.gtcmt.audiosketch.p5.window.ObjectWindow;
import net.gtcmt.audiosketch.sound.util.AudioControl;
import net.gtcmt.audiosketch.sound.util.SndConstants;
import net.gtcmt.audiosketch.sound.util.SndConstants.SndType;

/**
 * This panel allows you to edit shape of sound object.
 * @author akito
 * @deprecated
 */
public class EditSoundObjectPanel extends JPanel {

	private static final long serialVersionUID = 1070795152943741444L; 
	private JComboBox colorChooser;			// Choose color of sound object
	private JComboBox shapeChooser;			// Choose shape of sound object
	private JComboBox midiNoteChooser;		// Choose midi note of synth sound
	private JComboBox soundChooser;			// Choose synth sound
	private int objectShapeID;			// Sound object type  ObjectShapeType no more
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
	public EditSoundObjectPanel(AudioSketchMainFrame mainFrame) {
		this.mainFrame = mainFrame;
		objectColor = ObjectColorType.WHITE;
		objectShapeID = 0;//P5Constants.SHAPE_NAME[0];// ObjectShapeType.GEAR;
		sndType = SndType.BUZZ;
		
		Box vBox = Box.createVerticalBox();
		
		//Create object window
		objectWindow = new ObjectWindow(this);
		JPanel panel = new JPanel();
		panel.add(objectWindow);
		vBox.add(panel);

		vBox.add(createObjectStateChooser());		
		vBox.add(createSoundConfig());
		vBox.add(createButtons());
		
		//Add the whole thing to panel
		add(vBox);
	}
	
	/**
	 * Shape chooser is a drop down menu  that allows user to select 
	 * what kind of shape to use.
	 */
	private Box createObjectStateChooser(){
		Box hBox = Box.createHorizontalBox();
		
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
		
		hBox.add(Box.createHorizontalGlue());
		
		label = new JLabel("Shape:");
		hBox.add(label);
		
		shapeChooser = new JComboBox(P5Constants.SHAPE_NAME);
		shapeChooser.setSelectedIndex(0);
		shapeChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JComboBox box = (JComboBox) e.getSource();
					//objectShape = ObjectShapeType.values()[box.getSelectedIndex()];
					objectShapeID = box.getSelectedIndex();//P5Constants.SHAPE_NAME[box.getSelectedIndex()];
			}
		});
		hBox.add(shapeChooser);
		
		return hBox;
	}

	/**
	 * Labels for configuration panel
	 */
	private Box createSoundConfig(){
		Box hBox = Box.createHorizontalBox();
		JLabel label = new JLabel("Pitch:");
		hBox.add(label);
		
		String[] midiNote = new String[SndConstants.MAX_MIDI-SndConstants.MIN_MIDI+1];
		for(int i=SndConstants.MIN_MIDI; i<=SndConstants.MAX_MIDI;i++){
			midiNote[i-SndConstants.MIN_MIDI] = SndConstants.MIDI_NAME[(i-SndConstants.MIN_MIDI)%12] +"-"+ String.valueOf(i);
		}
		
		//Midi note combo box
		midiNoteChooser = new JComboBox(midiNote);
		midiNoteChooser.setSelectedIndex(57);
		midiNoteChooser.setPreferredSize(new Dimension(85, 25));
		midiNoteChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JComboBox box = (JComboBox) e.getSource();
					midiIndex = box.getSelectedIndex();
			}
		});		
		hBox.add(midiNoteChooser);
		
		hBox.add(Box.createRigidArea(new Dimension(10,0)));
		
		label = new JLabel("Instrument:");
		hBox.add(label);
		
		//Sound name combo box
		soundChooser = new JComboBox(SndConstants.SOUND_NAME);
		soundChooser.setPreferredSize(new Dimension(100, 25));
		soundChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JComboBox box = (JComboBox) e.getSource();
					sndType = SndType.values()[box.getSelectedIndex()];
			}
		});
		hBox.add(soundChooser);
		
		hBox.add(Box.createHorizontalGlue());
		
		return hBox;
	}
	
	/**
	 * Configuration panels. Things like midi note, sound name, play, and add button are created here
	 */
	private Box createButtons(){
		Box hBox = Box.createHorizontalBox();
		hBox.add(Box.createHorizontalGlue());
		
		//Preview sound
		playButton = new JButton("Play");
		playButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(sndType){
//				case BUZZ: AudioControl.getAudioCtrl().trigger(SndType.BUZZ.toString(), 1); break;
//				case BANJO: AudioControl.getAudioCtrl().trigger(SndType.BANJO.toString(), 1); break;
//				case CHING: AudioControl.getAudioCtrl().trigger(SndType.CHING.toString(), 1); break;
//				case CLARINET: AudioControl.getAudioCtrl().trigger(SndType.CLARINET.toString(), 1); break;
//				case POP: AudioControl.getAudioCtrl().trigger(SndType.POP.toString(), 1); break;
//				case FEMALE: AudioControl.getAudioCtrl().trigger(SndType.FEMALE.toString(), 1); break;
//				case GUITAR_CLASSIC: AudioControl.getAudioCtrl().trigger(SndType.GUITAR_CLASSIC.toString(), 1); break;
//				case GUITAR_ELEC: AudioControl.getAudioCtrl().trigger(SndType.GUITAR_ELEC.toString(), 1); break;
//				case SAX: AudioControl.getAudioCtrl().trigger(SndType.SAX.toString(), 1); break;
//				case TOY_PIANO: AudioControl.getAudioCtrl().trigger(SndType.TOY_PIANO.toString(), 1); break;
//				case VIOLA: AudioControl.getAudioCtrl().trigger(SndType.VIOLA.toString(), 1); break;
//				case ZAP: AudioControl.getAudioCtrl().trigger(SndType.ZAP.toString(), 1); break;
				}
			}
		});
	
		hBox.add(playButton);
		
		//Add button to add it to musical window
		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Send message to server
				synchronized (mainFrame.getMusicalWindow().getLockObject()) {
					mainFrame.getMusicalWindow().addSoundObject(objectShapeID, objectColor, sndType, 
							new P5Points2D((int) (Math.random()*GUIConstants.WINDOW_WIDTH), (int) (Math.random()*GUIConstants.WINDOW_HEIGHT)), 
							new P5Size2D(objectWindow.getObjectWidth(), objectWindow.getObjectHeight()), midiIndex);
				}
			}
		});
		hBox.add(addButton);
		
		return hBox;
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

	public int getObjectShape() {
		return objectShapeID;
	}

	public void setObjectShape(int objectShape) {
		this.objectShapeID = objectShape;
	}

	public ObjectColorType getObjectColor() {
		return objectColor;
	}

	public void setObjectColor(ObjectColorType objectColor) {
		this.objectColor = objectColor;
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
