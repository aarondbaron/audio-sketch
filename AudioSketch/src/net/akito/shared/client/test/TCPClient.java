package net.akito.shared.client.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import net.akito.shared.client.gui.EditSoundObjectPanel;
import net.akito.shared.client.visual.MusicalWindow;

public class TCPClient extends Thread {

	private Socket clientSocket;
	private DataOutputStream outToServer;
	private BufferedReader inFromServer;
	private String serverData=null;
	private String clientData=null;
	private static int PORT = 6789;
	private MusicalWindow m;
	private EditSoundObjectPanel panel;
	
	public TCPClient(MusicalWindow m, EditSoundObjectPanel panel) throws UnknownHostException, IOException{
		this.m = m;
		this.panel = panel;
		clientSocket = new Socket("localhost", PORT);
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
		inFromServer =new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}

	public void run() {	
		String[] data=null;
		try {
			while(true){
				if(clientData != null)
					checkWriteData(); 			
				read(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void checkWriteData() throws IOException{
		String[] data;
		if(clientData.charAt(clientData.length()-1) != '\n')
			clientData = clientData + "\n";
		clientData = clientData.substring(0, clientData.indexOf("\n")); // Only up to the newline
		data = clientData.split(" ");
//		if(data[0].equalsIgnoreCase("add") || !m.isAdding){
//			outToServer.writeBytes(clientData);
//			m.isAdding = true;
//		}
//		if(data[0].equalsIgnoreCase("bar") && !m.trigBar){
//			outToServer.writeBytes(clientData);
//			m.trigBar = true;
//		}
//		if(data[0].equalsIgnoreCase("move") && !m.isMoving){
//			outToServer.writeBytes(clientData);
//			m.isMoving = true;
//		}
//		if(data[0].equalsIgnoreCase("name") && !m.getName){
//			outToServer.writeBytes(clientData);
//			m.getName = true;
//		}
//		if(data[0].equalsIgnoreCase("box") && !m.addBox){
//			outToServer.writeBytes(clientData);
//			m.addBox = true;
//		}
//		if(data[0].equalsIgnoreCase("chat") && !panel.window.isChatting){
//			outToServer.writeBytes(clientData);
//			panel.window.isChatting = true;
//		}	
//		if(data[0].equalsIgnoreCase("init") && !panel.window.isChatting){
//			outToServer.writeBytes(data[1]+"\n");
////			System.out.println("here");
//		}	
	}
	
	private void read(String[] data) throws IOException{
		if(serverData != null){
			serverData = inFromServer.readLine();
			if(serverData.charAt(serverData.length()-1) != '\n')
				serverData = serverData + "\n";
			serverData = serverData.substring(0, serverData.indexOf("\n")); // Only up to the newline
			data = serverData.split(" ");
			checkReadData(data);
		}
	}
	
	private void checkReadData(String[] data){
//		if(data[0].equalsIgnoreCase("add") || m.isAdding){
//			m.data = data;
//			m.isAdding = false;
//		}
//		if(data[0].equalsIgnoreCase("bar") && m.trigBar){
//			m.data = data;
//			m.trigBar = false;
//		}
//		if(data[0].equalsIgnoreCase("move") && m.isMoving){
//			m.data = data;
//			m.isMoving = false;
//		}
//		if(data[0].equalsIgnoreCase("name") && m.getName){
//			m.data = data;
//			m.getName = false;
//		}
//		if(data[0].equalsIgnoreCase("box") && m.addBox){
//			m.data = data;
//			m.addBox = false;
//		}
//		if(data[0].equalsIgnoreCase("chat") && panel.window.isChatting){
//			panel.window.data = data;
//			panel.window.isChatting = false;
//		}
	}
	
	public void write(String arg){
		clientData = arg;
	}

	public void close() throws IOException{
		clientSocket.close();
	}
//	public static void main(String[] args){
//		TCPClient client = null;
//
//		try {
//			client = new TCPClient(null, null);
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		while(isRunning){
//			client.write("Hello\n");
//			String h = client.readLine();	
//			System.out.println("client "+h);
//		}
//		
//	}
}


