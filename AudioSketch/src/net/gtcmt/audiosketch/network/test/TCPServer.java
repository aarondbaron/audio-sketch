package net.gtcmt.audiosketch.network.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	private static ServerSocket serverSocket;
	private static Socket clientSocket = null;
	private static ClientThread thread[] = new ClientThread[10];   //10 clients' connections
	private static int PORT = 6789;

	public static void main(String argv[]) throws Exception {

		serverSocket = new ServerSocket(PORT);	

		while(true) {
			try {
				clientSocket = serverSocket.accept();
				
				for(int i=0;i<thread.length;i++)
					if(thread[i] == null){
						(thread[i] = new ClientThread(clientSocket, thread)).start();
						break;
					}
//				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
