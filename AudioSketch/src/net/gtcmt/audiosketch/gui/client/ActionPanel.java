package net.gtcmt.audiosketch.gui.client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.gtcmt.audiosketch.p5.util.P5Constants;

public class ActionPanel extends JPanel {

	private static final long serialVersionUID = -7169118764234746765L;
	public JCheckBox playButton;
	public JCheckBox editButton;
	public JCheckBox effectButton;
	public JComboBox barMode;
	private AudioSketchMainFrame mainFrame;
	
	public ActionPanel(AudioSketchMainFrame mainFrame) {
		this.mainFrame = mainFrame;
		Box vBox = Box.createVerticalBox();
		Box hBox = Box.createHorizontalBox();
		
		vBox.add(createModeButtons());
		
		hBox.add(createBarAction());
		hBox.add(createRemoveButton());
		vBox.add(hBox);
		
		add(vBox);
	}

	/**
	 * Create remove sound object button.
	 */
	private JButton createRemoveButton() {
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized (mainFrame.getMusicalWindow().getLockObject()) {
					if(playButton.isSelected() || editButton.isSelected()){
						mainFrame.getMusicalWindow().remove();
					}
					else if(effectButton.isSelected()){
						mainFrame.getMusicalWindow().removeEffectBox();
					}
				}
			}
		});
		return removeButton;
	}

	/**
	 * Create drop down menu to choose playback bar type
	 * @return
	 */
	private Box createBarAction() {
		Box box = Box.createHorizontalBox();

		barMode = new JComboBox(P5Constants.BAR_TYPE);
		barMode.setSelectedIndex(0);
		barMode.setPreferredSize(new Dimension(100, 30));
		barMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO what to put in?
				//	JComboBox box = (JComboBox) e.getSource();
			}
		});

		box.add(barMode);
		return box;
	}

	private Box createModeButtons() {
		Box box = Box.createHorizontalBox();

		playButton = new JCheckBox();
		playButton.setSelected(true);
		playButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(playButton.isSelected()){
					editButton.setSelected(false);
					effectButton.setSelected(false);
				}
			}
		});
		box.add(playButton);
		JLabel label = new JLabel("Play");
		box.add(label);

		editButton = new JCheckBox();
		editButton.setSelected(false);
		editButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(editButton.isSelected()){
					playButton.setSelected(false);
					effectButton.setSelected(false);
				}
			}
		});
		box.add(editButton);
		label = new JLabel("Edit");
		box.add(label);

		effectButton = new JCheckBox();
		effectButton.setSelected(false);
		effectButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(effectButton.isSelected()){
					playButton.setSelected(false);
					editButton.setSelected(false);
				}
			}
		});
		
		box.add(effectButton);
		label = new JLabel("Effect");
		box.add(label);
		
		return box;
	}
	
	/**
	 * Test this panel
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame();
		ActionPanel panel = new ActionPanel(null);
		
		frame.add(panel);
		frame.setSize(500, 100);
		frame.setLocation(0, 0);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt){
				System.exit(0);
			}
		});
	}

	/*------------------- Getter/Setter ----------------*/

	public JCheckBox getPlayButton() {
		return playButton;
	}

	public void setPlayButton(JCheckBox playButton) {
		this.playButton = playButton;
	}

	public JCheckBox getEditButton() {
		return editButton;
	}

	public void setEditButton(JCheckBox editButton) {
		this.editButton = editButton;
	}

	public JCheckBox getEffectButton() {
		return effectButton;
	}

	public void setEffectButton(JCheckBox effectButton) {
		this.effectButton = effectButton;
	}

	public JComboBox getBarMode() {
		return barMode;
	}

	public void setBarMode(JComboBox barMode) {
		this.barMode = barMode;
	}

	public AudioSketchMainFrame getMainFrame() {
		return mainFrame;
	}
}
