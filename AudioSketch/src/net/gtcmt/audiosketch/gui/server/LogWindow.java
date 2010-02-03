package net.gtcmt.audiosketch.gui.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import net.gtcmt.audiosketch.network.server.ServerNetwork;

/**
 * Log window is singleton class in order to log
 * stuff from possibly every class on the same window.
 * @author akito
 *
 */
public class LogWindow extends JFrame{

	private static final long serialVersionUID = -8758766770127693165L;

	private static LogWindow logWindow = null;
	//Text area where log message are displayed
	private JTextPane textArea;
	private ServerNetwork serverNetwork;
	
	public LogWindow() {
	
		super("Console");
		//Create scroll-able text area
		textArea = new JTextPane();
		textArea.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		textArea.setFont(new Font("Helvetica", Font.PLAIN, 14));
		textArea.setEditable(true);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		//Create a content pane, set layout, and add component
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(scrollPane);

		//Set window content and menu
		this.setContentPane(contentPanel);
		
		addWindowListener(new WindowListener() { 	
			public void windowOpened(WindowEvent e) {}
			
			public void windowIconified(WindowEvent e) {}
			
			public void windowDeiconified(WindowEvent e) {}
			
			public void windowDeactivated(WindowEvent e) {}
			
			public void windowClosing(WindowEvent e) {
				serverNetwork.shutdown();
			}
			
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
			
			public void windowActivated(WindowEvent e) {}
		});
		
		//Set other window characteristics
		this.setSize(400,200);
		this.setLocation(0,0);
	}
	
	public JTextPane getTextArea() {
		return textArea;
	}
	
	/**
	 * Logs message to text window. This needs to be called after the important code execution,
	 * because as the log message gets larger it will take longer time to display stuff.
	 * @param msg
	 */
	public void log(Color c, String msg){
		append(c, msg);
		append(Color.BLACK, "\n");
	}
	
	public void append(Color c, String s) { // better implementation--uses StyleContext
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

		int len = textArea.getDocument().getLength(); // same value as getText().length();
		textArea.setCaretPosition(len);  // place caret at the end (with no selection)
		textArea.setCharacterAttributes(aset, false);
		textArea.replaceSelection(s); // there is no selection, so inserts at caret
	}
	
	public static LogWindow getLogWindow(){
		if(logWindow == null){
			logWindow = new LogWindow();
		}
		return logWindow;
	}
	
	public void setServerNetwork(ServerNetwork serverNetwork){
		this.serverNetwork = serverNetwork;
	}
	
	public static void main(String args[]){
		(new LogWindow()).setVisible(true);
	}
}
