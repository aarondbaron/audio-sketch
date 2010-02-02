package net.gtcmt.audiosketch.client.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Welcome panel is shown when the application starts up.
 * It forces user to decide their user name
 * @author akito
 *
 */
public class WelcomePanel extends JFrame {

	private static final long serialVersionUID = -3481428634705195152L;
	private String userName="Anonymous";		//user name
	private JTextField text;					//User name field
	private boolean isNameEntered;


	public WelcomePanel(){

		isNameEntered = false;
		
		JPanel panel = new JPanel();
		
		Box vBox = Box.createVerticalBox();
		Box hBox = Box.createHorizontalBox();
		hBox.setPreferredSize(new Dimension(300, 30));
		
		JLabel label = new JLabel("Welcome to MCollab!!!");
		vBox.add(label);
		label = new JLabel("Type in your name to get started.");
		vBox.add(label);
		
		//initialize and add text box
		text = new JTextField(userName, 50);
		text.setPreferredSize(new Dimension(100, 30));
		text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action();
			}
		});
		hBox.add(text);
		
		//Initialize and add button
		JButton button = new JButton("Start");
		button.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				action();
			}
		});
		hBox.add(button);
		panel.add(hBox);
		
		//Add them all up in a frame
		vBox.add(panel);
		panel = new JPanel();
		panel.add(vBox);
		add(panel);
		
		//Configure Frame
		setSize(300, 100);
		setLocation(200, 200);
		setVisible(true);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public boolean isNameEntered() {
		return isNameEntered;
	}

	public void setNameEntered(boolean isNameEntered) {
		this.isNameEntered = isNameEntered;
	}
	
	/**
	 * Action commited when button or enter key is pressed
	 */
	private void action(){
		userName = text.getText();
		dispose();
		isNameEntered = true;
	}
	
	/**
	 * Test gui
	 * @param args
	 */
	public static void main(String[] args) {
		
		WelcomePanel panel = new WelcomePanel();
		
		panel.setSize(300, 100);
		panel.setLocation(200, 200);
		panel.setVisible(true);
		
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt){
				System.exit(0);
			}
		});
		panel.validate();
	}
}
