package net.gtcmt.audiosketch.network.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import net.gtcmt.audiosketch.util.LogMessage;

/**
 * client representation on server side.
 * 
 * @author akito
 *
 */
public final class AudioSketchClient extends ClientOnServer {

	// Listens and responds to client
	private ClientMessageHandler clientThread;
	//client ID
	private String id = null;

	/**
	 * Constructor: Upon instantiation it will create a ObjectOutputStream 
	 * for output message to client(s) and creates ClientMessageHandler
	 * for to start new thread for this particular client.
	 * 
	 * @param server reference to server
	 * @param client reference to client
	 * @param threadNum number of ClientMessageHandler thread that is running so far
	 */
	public AudioSketchClient(ServerNetwork server, Socket client, int threadNum) {
		try {
			this.server = server;
			this.socket = client;
			writer = new ObjectOutputStream(new BufferedOutputStream(client.getOutputStream()));
			reader = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
			clientThread = new ClientMessageHandler(this, threadNum);
			clientThread.start();
		} catch (IOException e) {
			// Could not establish input or output connection with client
			LogMessage.javaErr(e);
		}
	}
	
	/*--------------- Getter/Setter methods ----------------*/
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ClientMessageHandler getClientMessageHandler() {
		return clientThread;
	}

	public void setClientMessageHandler(ClientMessageHandler clientThread) {
		this.clientThread = clientThread;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public ObjectOutputStream getMessageWriter() {
		return writer;
	}

	public void setMessageWriter(ObjectOutputStream writer) {
		this.writer = writer;
	}

	public ServerNetwork getServer() {
		return server;
	}

	public void setServer(ServerNetwork server) {
		this.server = server;
	}
	
	public ObjectInputStream getMessageReader() {
		return reader;
	}
}
