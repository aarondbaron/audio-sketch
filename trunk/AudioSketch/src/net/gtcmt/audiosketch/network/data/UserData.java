package net.gtcmt.audiosketch.network.data;

import java.io.Serializable;

public class UserData implements Serializable {

	private static final long serialVersionUID = -368291529004866447L;
	private String userName;
	private int userID;
	
	public UserData(String userName, int userID){
		this.userName = userName;
		this.userID = userID;
	}

	public UserData(String userName){
		this.userID = 0;
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
}
