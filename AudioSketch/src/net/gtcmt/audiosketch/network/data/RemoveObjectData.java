package net.gtcmt.audiosketch.network.data;

//TODO implement remove data
public class RemoveObjectData implements MessageData {

	private static final long serialVersionUID = -1641188274106281254L;
	private int objIndex;	// TODO support for specific sound object removable

	public int getObjIndex() {
		return objIndex;
	}

	public void setObjIndex(int objIndex) {
		this.objIndex = objIndex;
	}
	
}
