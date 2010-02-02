package net.gtcmt.audiosketch.network.data;

/**
 * Container for chat message
 * @author akito
 *
 */
public final class ChatData implements MessageData {

	private static final long serialVersionUID = 4600924766241358912L;
	private String chatMsg;
	
	public ChatData(String chatMsg){
		this.chatMsg = chatMsg;
	}

	public String getChatMsg() {
		return chatMsg;
	}

	public void setChatMsg(String chatMsg) {
		this.chatMsg = chatMsg;
	}
}
