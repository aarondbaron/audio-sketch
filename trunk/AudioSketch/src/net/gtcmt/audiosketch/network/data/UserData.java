package net.gtcmt.audiosketch.network.data;

public class UserData {

	private String userName;
	private int userID;
	
	public UserData(String userName, int userID){
		this.userName = userName;
		this.userID = userID;
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
