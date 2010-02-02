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
	private String userName;			//Name of user who is sending this data
	
	/**
	 * Create audio sketch data that will be passed between client and server
	 * @param msgType
	 * @param msgData
	 * @param userName
	 */
	public AudioSketchData(MsgType msgType, MessageData msgData, String userName){
		this.msgType = msgType;
		this.msgData = msgData;
		this.userName = userName;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}	
}
