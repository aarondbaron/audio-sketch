package net.gtcmt.audiosketch.gui.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import net.gtcmt.audiosketch.gui.util.GUIConstants;
import net.gtcmt.audiosketch.network.client.Client;
import net.gtcmt.audiosketch.network.data.AudioSketchData;
import net.gtcmt.audiosketch.network.data.ChatData;
import net.gtcmt.audiosketch.network.util.MsgType;

public class ChatWindow extends JPanel {

	private static final long serialVersionUID = -8842476654319423858L;
	private AudioSketchMainFrame mainFrame;
	private JScrollPane scrollPane;
	public JTextPane textArea;
	private JTextField textField;
	private JButton postButton;
	
	private String userName;
	
	/**
	 * Constructor for chat window
	 * @param width
	 * @param height
	 * @param editPanel
	 * @param userName
	 */
	public ChatWindow(AudioSketchMainFrame mainFrame){
		this.mainFrame = mainFrame;
		
		//post window for chat message
		setTextArea();
		scrollPane = new JScrollPane(textArea);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(GUIConstants.CHAT_WIDTH, GUIConstants.SCROLL_HEIGHT));
		Box box = Box.createVerticalBox();
		box.setPreferredSize(new Dimension(GUIConstants.EDITPANEL_WIDTH, GUIConstants.SCROLL_HEIGHT));
		
		box.add(scrollPane);
	
		//Text entry field and button for submission
		setTextField();
		setPostButton();
		Box hBox = Box.createHorizontalBox();
		hBox.add(textField);
		hBox.add(postButton);
		
		box.add(hBox);
		
		//Add to panel
		add(box);
	}
	
	/**
	 * create text area to display chat messages
	 */
	private void setTextArea(){
		textArea = new JTextPane();
		textArea.setEditable(true);
	}
	
	/**
	 * create chat message input box
	 */
	private void setTextField(){
		textField = new JTextField(500);
		textField.setPreferredSize(new Dimension(GUIConstants.EDITPANEL_WIDTH, GUIConstants.TEXT_HEIGHT));
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chatAction();
			}
		});
	}

	/**
	 * create post button
	 */
	private void setPostButton(){
		postButton = new JButton("Post");
		postButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chatAction();
			}
		});
	}
	
	/**
	 * Action performed when button or enter is pressed
	 */
	private void chatAction(){
		getClient().sendData(new AudioSketchData(MsgType.CHAT, new ChatData(textField.getText()), userName, 0));
		textField.setText("");
	}
	
	public void append(Color c, String s) { // better implementation--uses StyleContext
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

		int len = textArea.getDocument().getLength(); // same value as getText().length();
		textArea.setCaretPosition(len);  // place caret at the end (with no selection)
		textArea.setCharacterAttributes(aset, false);
		textArea.replaceSelection(s); // there is no selection, so inserts at caret
	}
	
	
	public Client getClient(){
		return mainFrame.getClient();
	}
	
	/**
	 * Test
	 * @param args
	 */
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		ChatWindow chat = new ChatWindow(null);
		
		frame.add(chat);
		frame.setSize(310, 350);
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
