package net.akito.shared.server.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {

	BufferedReader inFromClient = null;
	DataOutputStream outToClient = null;
	private Socket clientSocket = null;       
	private ClientThread thread[]; 

	public ClientThread(Socket clientSocket, ClientThread[] t){
		this.clientSocket=clientSocket;
		this.thread=t;
	}

	public void run() 
	{
		String line;
		String name;
		
		try{
			inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			outToClient = new DataOutputStream(clientSocket.getOutputStream());
			
			System.out.println("welcomed");
			name = inFromClient.readLine();
			outToClient.writeBytes("chat welcome "+name+"!!!");
			System.out.println("welcomed 2");
			for(int i=0; i<=9; i++)
				if (thread[i]!=null && thread[i]!=this)  
					thread[i].outToClient.writeBytes("chat A new user "+name+" just entered!!!" );
			
			while (true) {
				line = inFromClient.readLine();
				System.out.println("readline");
				if(line.startsWith("quit")) break; 
				for(int i=0; i<=9; i++)
					if (thread[i]!=null) thread[i].outToClient.writeBytes(line+"\n"); 
			}
			for(int i=0; i<=9; i++)
				if (thread[i]!=null && thread[i]!=this)  
					thread[i].outToClient.writeBytes("chat "+name+" is leaving the chat room !!!" );

			outToClient.writeBytes("chat GoodBye "+name+"!!!"); 

			// Clean up:
			// Set to null the current thread variable such that other client could
			// be accepted by the server

			for(int i=0; i<=9; i++)
				if (thread[i]==this) thread[i]=null;

			inFromClient.close();	// close the output stream
			outToClient.close();	// close the input stream
			clientSocket.close();	// close the socket
		}
		catch(IOException e){};
	}
}

