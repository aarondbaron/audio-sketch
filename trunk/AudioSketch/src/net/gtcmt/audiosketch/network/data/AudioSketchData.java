package net.gtcmt.audiosketch.network.data;

import java.io.Serializable;

import net.gtcmt.audiosketch.network.util.MsgType;

/**
 * This class is what gets passed between client and server to 
 * exchange information.
 * @author akito
 *
 */
public class AudioSketchData implements Serializable {

	private static final long serialVersionUID = -4898161811400795150L;
	private MsgType msgType;			//Type of message to send. useful for switch or enumeration.
	private MessageData msgData;		//Contains the actual data. Varies depending on msgType
	private UserData userData;			//Name of user who is sending this data
	
	/**
	 * Create audio sketch data that will be passed between client and server
	 * @param msgType	type of message sending to server
	 * @param msgData	actual data
	 * @param userName	user name who is sending a message to server
	 * @param userID		set it to 0 if this does not matter
	 */
	public AudioSketchData(MsgType msgType, MessageData msgData, String userName, int userID){
		this.msgType = msgType;
		this.msgData = msgData;
		this.userData = new UserData(userName, userID);
	}

	/*---------------- Getter/Setter ------------------*/
	public MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}

	public MessageData getMsgData() {
		return msgData;
	}

	public void setMsgData(MessageData msgData) {
		this.msgData = msgData;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	
	public String getUserName(){
		return userData.getUserName();
	}
}
