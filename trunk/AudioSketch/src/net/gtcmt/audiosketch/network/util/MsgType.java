package net.gtcmt.audiosketch.network.util;

import java.util.Hashtable;

public enum MsgType {

	//Message type
	CHAT, LOGIN, QUIT, MOVE_OBJECT, PLAY_BAR, USER_NAME, EFFECT_BOX, ADD_OBJECT, REMOVE_OBJECT;
	
	private static Hashtable<String, MsgType> msgTypeTable = new Hashtable<String, MsgType>();
	
	public static void initMsgTypeTable(){
		for(int i=0;i<MsgType.values().length;i++){
			msgTypeTable.put(MsgType.values()[i].toString(), MsgType.values()[i]);
		}
	}
	
	/**
	 * Check if String is MsgType
	 * @param msgType
	 * @return
	 */
	public static boolean contains(String msgType){
		if(msgTypeTable.size() <= 0){
			initMsgTypeTable();
		}
		
		if(msgTypeTable.get(msgType) != null){
			return true;
		}
		else{
			return false;
		}
	}
}
