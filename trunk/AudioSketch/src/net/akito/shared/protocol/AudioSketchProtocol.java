package net.akito.shared.protocol;

public class AudioSketchProtocol {

	public static final String SPLITTER = " ";
	public static final String TERMINATOR = " \n";
	public static final int TERMINATOR_BYTE = 10;
	
	public enum MsgType{
		CHAT, INIT, QUIT, MOVE, BAR, NAME, BOX, ADD
	}
	
	/**
	 * Splits up token based on splitter
	 * @param data
	 * @return
	 */
	public static String[] createTokens(String data){
		return data.split(SPLITTER);
	}
}
