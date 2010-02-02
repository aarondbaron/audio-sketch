package net.gtcmt.audiosketch.network.client;

import net.gtcmt.audiosketch.client.gui.AudioSketchMainFrame;
import net.gtcmt.audiosketch.network.util.AudioSketchProtocol;
import net.gtcmt.audiosketch.util.LogMessage;

/**
 * Client side representation of client. 
 * @author akito
 * 
 */
public class Client extends ClientNetwork {
	// Gets event from server and pushes into queue to be decoded by client
	protected ServerEventReader eventReader;
	// Protocol for passing message between client and server
	private AudioSketchProtocol asProtocol;

	/**
	 * Constructor: Creates a connection to server. 
	 * Runs ConsoleEventReader and ServerEventReader to start listening
	 * to both server input and user input.
	 * @param serverAddr typically ip address of server
	 * @param port port to make connection to
	 */
	public Client(String serverAddr, int port) {
		super(serverAddr, port);
		asProtocol = new AudioSketchProtocol();
		eventReader = new ServerEventReader(this, inQueue);
		eventReader.start();
	}

	/**
	 * Processes incoming message from server 
	 * when there is something in queue
	 */
	@Override
	protected void processIncomingMsg() {
		while (inQueue.size() > 0) {
			try {
				// Process message from server
				asProtocol.processServerInput(inQueue.pop(),this);
			} catch (InterruptedException e) {
				LogMessage.javaErr(e);
			}
		}
	}

	/**
	 * Writes message to server when there is something in queue
	 */
	@Override
	protected void writeOutgoingMsg() {
		while (outQueue.size() > 0) {
			try {
				//Send message to server
				writeData(outQueue.pop());
			} catch (InterruptedException e) {
				LogMessage.javaErr(e);
			}
		}
	}
	
	@Override
	public void shutdown() {
		eventReader.shutdown();
		super.shutdown();
	}
	
	/**
	 * pass reference to musical window for protocol
	 * @param musicalWindow
	 */
	public void setMainFrame(AudioSketchMainFrame mainFrame){
		asProtocol.setMainFrame(mainFrame);
	}
	
	/**
	 * Test network connection.
	 * @param args
	 */
	public static void main(String args[]) {
		String addr = "192.168.0.10";
		int port = 12345;
		Client client = new Client(addr, port);
		client.start();
	}
}
