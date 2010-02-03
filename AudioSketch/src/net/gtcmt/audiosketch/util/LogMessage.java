package net.gtcmt.audiosketch.util;

import java.awt.Color;

import net.gtcmt.audiosketch.gui.server.LogWindow;

/**
 * Message logging class
 * @author akito
 *
 */
public class LogMessage {

	public static void javaErr(Throwable t){
		t.printStackTrace();
	}
	
	public static void err(String msg){
		System.err.println(msg);
	}
	
	public static void info(String msg){
		System.out.println(msg);
	}
	
	public static void serverInfo(String msg){
		LogWindow.getLogWindow().log(Color.GRAY, msg);
	}
	
	public static void serverErr(String msg){
		LogWindow.getLogWindow().log(Color.RED, msg);
	}
	
	public static void serverJavaError(Throwable t){
		LogWindow.getLogWindow().log(Color.GRAY, t.toString());
	}
}
