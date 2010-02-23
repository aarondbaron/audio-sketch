package net.gtcmt.audiosketch.gui.client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.gtcmt.audiosketch.network.client.Client;
import net.gtcmt.audiosketch.network.server.ServerNetwork;
import net.gtcmt.audiosketch.util.LogMessage;

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
	private JTextField ipAddress;
	private boolean isNameEntered;
	private Client client;

	public WelcomePanel(){

		isNameEntered = false;
		
		Box vBox = Box.createVerticalBox();
		
		JLabel label = new JLabel("Welcome to MCollab!!!");
		vBox.add(label);
		
		Box hBox = Box.createHorizontalBox();
		hBox.add(new JLabel("  User Name:  "));
		//initialize and add text box
		text = new JTextField(userName, 50);
		text.setPreferredSize(new Dimension(100, 30));
		text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action();
			}
		});
		
		hBox.add(text);
		vBox.add(hBox);
		
		hBox = Box.createHorizontalBox();
		hBox.add(new JLabel("  IP Address:  "));
		ipAddress = new JTextField("localhost", 50);
		ipAddress.setPreferredSize(new Dimension(100, 30));
		ipAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action();
			}
		});
		hBox.add(ipAddress );
		vBox.add(hBox);
		
		//Initialize and add button
		JButton button = new JButton("Start");
		button.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				action();
			}
		});
		
		hBox = Box.createHorizontalBox();
		hBox.add(Box.createHorizontalGlue());
		hBox.add(button);
		vBox.add(hBox);
		
		//Add them all up in a frame
		add(vBox);
		
		//Configure Frame
		setSize(300, 125);
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
		if(client == null || !client.isConnected()){
			if(ipAddress.getText() == "" || ipAddress.getText() == null){
				LogMessage.err("IP Address field is empty");
				return;
			}
			else if(!ipAddress.getText().matches("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$") 
					&& !ipAddress.getText().equalsIgnoreCase("localhost")){
				LogMessage.err("Invalid ip address");
				return;
			}
			if(text.getText() == "" || text.getText() == null){
				LogMessage.err("Name field is empty");
				return;
			}

			//Initialize client network
			client = new Client(ipAddress.getText(), ServerNetwork.DEFAULT_PORT);
			client.start();
			userName = text.getText();
			dispose();
			isNameEntered = true;
		}
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
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
