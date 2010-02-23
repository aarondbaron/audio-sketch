package net.gtcmt.audiosketch.network.client;

import net.gtcmt.audiosketch.network.data.AudioSketchData;
import net.gtcmt.audiosketch.network.util.MessageQueue;
import net.gtcmt.audiosketch.util.LogMessage;

/**
 * Responsible for reading server input
 * 
 * @author akito
 * 
 */
public class ServerEventReader extends Thread {
	private Client client; 				// Reference to socket
	private boolean isRunning = false; 	// Indicator for running state
	private MessageQueue inQueue; 		// Queues incoming message from server

	/**
	 * Constructor:
	 * 
	 * @param socket
	 */
	public ServerEventReader(Client client, MessageQueue inQueue) {
		this.client = client;
		this.inQueue = inQueue;
	}

	/**
	 * Reads message from server and adds to queue for further process
	 */
	public void run() {
		isRunning = true;
		AudioSketchData data = null;
		while (isRunning) {
			try {
				data = client.readData();
			} catch (Throwable e) {
				// XXX Throws error when client closes socket
				LogMessage.javaErr(e);
			} 
			if (data != null && data instanceof AudioSketchData){
				// Push in data to queue so that client network can process it
				inQueue.push(data);
			}
			else {
				LogMessage.err("Server connection is gone. Closing connection.");
				shutdown();
			}
		}
	}

	/**
	 * Shuts down ServerEventReader by turning off the thread
	 */
	public void shutdown() {
		isRunning = false;
		interrupt();
	}
}
