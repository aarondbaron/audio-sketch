package net.gtcmt.audiosketch.util;


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
}
