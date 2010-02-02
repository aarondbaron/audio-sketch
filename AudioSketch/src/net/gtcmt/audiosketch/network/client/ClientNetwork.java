package net.gtcmt.audiosketch.network.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import net.gtcmt.audiosketch.network.data.AudioSketchData;
import net.gtcmt.audiosketch.network.util.MessageQueue;
import net.gtcmt.audiosketch.util.LogMessage;

/**
 * Abstract representation of client network.
 * 
 * @author akito
 * 
 */
public abstract class ClientNetwork extends Thread {
	
	// Client socket
	protected Socket clientSocket;
	// Handles message output to server
	protected ObjectOutputStream out; 
	// Handles message input from server
	protected ObjectInputStream in;
	// Indicator for client running state
	protected boolean isRunning=false; 
	// Address of server
	protected String serverAddr; 
	// Queues messages to be sent to server
	protected MessageQueue outQueue; 
	// Queues messages sent from server
	protected MessageQueue inQueue; 		
	// connection check
	private boolean isConnected=false;
	//Default port number
	private static int DEFAULT_PORT = 36785;

	/**
	 * Constructor: connects to server.
	 * On success, it creates MessageQueue for 
	 * incoming and outgoing messages.
	 * 
	 * @param serverAddr host address
	 * @param port port to listen to
	 */
	public ClientNetwork(String serverAddr, int port) {
		this.serverAddr = serverAddr;
		
		if (!connect(serverAddr, port, "Connecting to server ...")) {
			LogMessage.err("Failed to connect to server");
		}
		else { //Connection to server is established
			isConnected = true;
			LogMessage.info("Connected to server");
		}

		try {
			//Only output stream is initialize because of ObjectStream's corky behavior
			out = new ObjectOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
		} catch (IOException e) {
			LogMessage.javaErr(e);
		}
		
		outQueue = new MessageQueue();
		inQueue = new MessageQueue();
	}

	/**
	 * Connect to server and initialize input and output
	 * 
	 * @param addr host address
	 * @param port port to listen to
	 * @return true if connection is made else false
	 */
	protected boolean connect(String addr, int port, String msg) {
		LogMessage.info(msg);
		try {
			clientSocket = new Socket(addr, port); // Connect to server
			return true;
		} catch (Throwable t) {
			LogMessage.javaErr(t);
			return false;
		}
	}

	/**
	 * Continuously read incoming messages and write outgoing messages
	 */
	public void run() {
		isRunning = true;

		while (isRunning) {
			processIncomingMsg();
			writeOutgoingMsg();
		}
	}

	/**
	 * Shuts down client by stopping the thread and closing socket
	 */
	public void shutdown() {
		isRunning = false;
		interrupt();
		try {
			clientSocket.close();
		} catch (IOException e) {
			LogMessage.javaErr(e);
		}
		isConnected = false;
	}

	/**
	 * Send event to server
	 * @param msg message that will be sent to server
	 */
	public void writeData(AudioSketchData data) {
		try {
			out.writeObject(data);
			out.flush();
			out.reset();
		} catch (IOException e) {
			//XXX socket closed exception happens but ignored
		}
	}

	/**
	 * Get data from server
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public AudioSketchData readData() throws IOException, ClassNotFoundException {
		if(in == null) {
			in = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));
		}
		return (AudioSketchData) in.readObject();
	}
	
	/*-------------- getter/setter methods -------------*/
	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public MessageQueue getInQueue() {
		return this.inQueue;
	}
	
	public MessageQueue getOutQueue() {
		return this.outQueue;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public static int getDefaultPortNum() {
		return DEFAULT_PORT;
	}

	public static void setDefaultPortNum(int defaultPortNum) {
		ClientNetwork.DEFAULT_PORT = defaultPortNum;
	}

	/*-------------- abstract methods -------------*/

	/**
	 * Handle incoming Events
	 */
	protected abstract void processIncomingMsg();

	/**
	 * Handle outgoing Events
	 * 
	 * @param stdIn
	 */
	protected abstract void writeOutgoingMsg();
}
