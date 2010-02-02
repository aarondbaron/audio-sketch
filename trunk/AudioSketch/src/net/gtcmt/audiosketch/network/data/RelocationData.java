package net.gtcmt.audiosketch.network.data;

import net.gtcmt.audiosketch.client.util.P5Points2D;

public class RelocationData implements MessageData {

	private static final long serialVersionUID = 3973597120930685948L;
	private int objectIndex;
	private P5Points2D objPos;
	
	public RelocationData(int objectIndex, P5Points2D objPos){
		this.objectIndex = objectIndex;
		this.objPos = objPos;
	}
	
	public int getPosX(){
		return objPos.getPosX();
	}
	public int getPosY(){
		return objPos.getPosY();
	}
	public P5Points2D getObjPos() {
		return objPos;
	}
	public void setObjPos(P5Points2D objPos) {
		this.objPos = objPos;
	}
	public int getObjectIndex() {
		return objectIndex;
	}
	public void setObjectIndex(int objectIndex) {
		this.objectIndex = objectIndex;
	}
}
