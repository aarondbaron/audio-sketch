package net.gtcmt.audiosketch.network.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Client representation on server side.
 * 
 * @author akito
 * 
 */
public abstract class ClientOnServer {

	// Reference to server
	protected ServerNetwork server; 
	// Reference to client socket
	protected Socket socket;
	// Used for socket outlet
	protected ObjectOutputStream writer;
	// Used for socket inlet
	protected ObjectInputStream reader;
	
	public abstract String getID();

	public abstract void setID(String id);

	public abstract Socket getSocket();

	public abstract void setSocket(Socket socket);

	public abstract ClientMessageHandler getClientMessageHandler();

	public abstract void setClientMessageHandler(ClientMessageHandler clientThread);

	public abstract ObjectOutputStream getMessageWriter();

	public abstract void setMessageWriter(ObjectOutputStream writer);

	public abstract ServerNetwork getServer();

	public abstract void setServer(ServerNetwork server);

	public abstract ObjectInputStream getMessageReader();
}
