package net.gtcmt.audiosketch.network.server;

import java.io.IOException;
import java.net.Socket;

import net.gtcmt.audiosketch.network.data.AudioSketchData;
import net.gtcmt.audiosketch.network.util.AudioSketchProtocol;
import net.gtcmt.audiosketch.util.LogMessage;

/**
 * Thread per client architecture with blocking i/o.
 * Server uses this class.
 * @author akito
 * 
 */
// TODO consider using queue for this class if we encounter a communication problem.
public final class ClientMessageHandler extends Thread {

	private ClientOnServer client; 							// Reference to client user
	private AudioSketchProtocol 	audioSketchProtocol;		// Protocol for handling message
	boolean isRunning = false; 							// ServerController running indicator
	
	/**
	 * Constructor:
	 * 
	 * @param client
	 * @param clientSocket
	 * @param threadNum
	 */
	public ClientMessageHandler(ClientOnServer client, int threadNum) {
		super("ClientMessageHandler" + threadNum);
		this.client = client;
		audioSketchProtocol = new AudioSketchProtocol();
	}

	/**
	 * It waits for client input and either broadcast to all clients or echos
	 * back to client depending on message type.
	 */
	public void run() {
		isRunning = true;
		AudioSketchData data = null;
		while (isRunning) {
			try {
				// Blocks until it receives something
				data = (AudioSketchData) client.getMessageReader().readObject();
			} catch (Throwable e) {
				//XXX throws error when client disconnects connection
				LogMessage.serverJavaError(e);
				shutdownClientSocket();
			} 

			if (data != null && data instanceof AudioSketchData) {
				audioSketchProtocol.processClientInput(data,this);
			}
			else {
				if(data != null) {
					if(data.getUserName() != null) {
						LogMessage.serverErr("Client connection on clientID "+data.getUserName()+" is gone. Closing server socket.");
					}
					else {
						LogMessage.serverErr("Client connection on unknown clientID is gone. Closing server socket.");
					}
					shutdownClientSocket();
				}
			}
		}
	}

	/**
	 * stops the thread 
	 */
	public void halt() {
		isRunning = false;
		interrupt();
	}
	
	/**
	 * Shuts down ClientMessageHandler by turning off thread and closing socket.
	 */
	public void shutdownClientSocket() {
		try {
			isRunning = false;
			interrupt();
			client.getSocket().close();
		} catch (IOException e1) {
			LogMessage.serverErr("Could not disconnect client socket.");
			LogMessage.serverJavaError(e1);
		}	
	}
	
	public ServerNetwork getServer(){
		return client.getServer();
	}
	
	public Socket getCoderSocket(){
		return client.getSocket();
	}
}
