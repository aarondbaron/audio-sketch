package net.gtcmt.audiosketch.network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

import net.gtcmt.audiosketch.network.data.AudioSketchData;
import net.gtcmt.audiosketch.network.data.LoginData;
import net.gtcmt.audiosketch.network.util.MsgType;
import net.gtcmt.audiosketch.util.LogMessage;

/**
 * Network server. Accepts connection from client and
 * keeps track of each client using hash table.
 * @author akito
 *
 */
public final class ServerNetwork extends Thread {

	private ServerSocket serverSock;
	private Hashtable<Socket, ClientOnServer> clientsByClientID; // clients keyed by socket
	private int threadNum = 0;
	private boolean isRunning = false;
	private static int DEFAULT_PORT = 36785;
	
	/**
	 * Constructor: Initializes socket and hash table
	 * Use start() to actually open the socket
	 * @param port
	 */
	public ServerNetwork(int port) {
		clientsByClientID = new Hashtable<Socket, ClientOnServer>();
		initServer(port);
	}

	private void initServer(int port) {
		try {
			serverSock = new ServerSocket(port);
			LogMessage.info("Server listening to port: "+port);
		} catch (IOException e) {
			LogMessage.err("Could not listen on port: " +port);
			LogMessage.err("Exiting application");
			System.exit(1);
		}
	}

	public void run() {
		LogMessage.info("Accepting client connection ...");
		isRunning = true;

		while (isRunning) {
			try {
				// Blocks until the connection is made
				Socket client = serverSock.accept(); 
				LogMessage.info("New connection established");
				clientsByClientID.put(client, new AudioSketchClient(this, client, ++threadNum));
				
				//broad cast to all clients that there are new client
				broadCastEvent(new AudioSketchData(MsgType.LOGIN, new LoginData(), "",0));
			} catch (IOException e) {
				//XXX throws error on closing socket because of accept method. For now I ignore this.
			}
		}
	}
	
	/**
	 * Get all clients
	 * 
	 * @return enumerated clients
	 */
	private Enumeration<ClientOnServer> getClients() {
		return clientsByClientID.elements();
	}

	/**
	 * Send message to everyone whose connected
	 * 
	 * @param msg
	 */
	public synchronized void broadCastEvent(AudioSketchData data) {
		synchronized (clientsByClientID) {
			for (Enumeration<ClientOnServer> e = getClients(); e.hasMoreElements();) {
				ClientOnServer client = (ClientOnServer) e.nextElement();
				try {
					client.getMessageWriter().writeObject(data);
					client.getMessageWriter().flush();
					client.getMessageWriter().reset();
				} catch (IOException e1) {
					//XXX Throws socket close error but ignored 
				}
			}
		}
	}

	/**
	 * Send event to individual client
	 * @param socket
	 * @param data
	 */
	public synchronized void sendEvent(Socket socket, AudioSketchData data) {
		ClientOnServer client = clientsByClientID.get(socket);
		try {
			client.getMessageWriter().writeObject(data);
			client.getMessageWriter().flush();
			client.getMessageWriter().reset();
		} catch (IOException e) {
			LogMessage.javaErr(e);
		}
	}

	/**
	 * Remove client connection
	 * 
	 * @param socket
	 */
	public synchronized void removeClient(Socket socket) {
		clientsByClientID.remove(socket);
		try {
			socket.close();
		} catch (IOException e) {
			LogMessage.javaErr(e);
		}
	}

	/**
	 * REmove all client connection
	 */
	public synchronized void removeAllClients(){
		for(Enumeration<ClientOnServer> e = getClients(); e.hasMoreElements();){
			ClientOnServer lc = e.nextElement();
			try {
				lc.getMessageWriter().close();
				lc.getSocket().close();
			} catch (IOException e1) {
				//XXX socket close execption thrown but ignored
			}
		}
		clientsByClientID.clear();
	}
	
	/**
	 * Shuts down server by stopping the thread and closing socket
	 */
	public synchronized void shutdown() {
		isRunning = false;
		interrupt();
		
		removeAllClients();
		
		try {
			serverSock.close();
		} catch (IOException e) {
			LogMessage.javaErr(e);
		}
		
		LogMessage.info("Server connection closed ...");
	}
	
	public static int getDefaultPortNum() {
		return DEFAULT_PORT;
	}

	public static void setDefaultPortNum(int defaultPortNum) {
		ServerNetwork.DEFAULT_PORT = defaultPortNum;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	/**
	 * Test server
	 * @param args
	 */
	public static void main(String args[]) {
		int port = 12345;
		ServerNetwork server = new ServerNetwork(port);
		server.start();
	}
}