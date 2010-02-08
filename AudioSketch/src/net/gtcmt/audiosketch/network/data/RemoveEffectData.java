package net.gtcmt.audiosketch.network.data;

public class RemoveEffectData implements MessageData {

	private static final long serialVersionUID = 8605254573612448482L;
	private int effectIndex;	//TODO support for indexed removing
	
	
	public int getEffectIndex() {
		return effectIndex;
	}
	public void setEffectIndex(int effectIndex) {
		this.effectIndex = effectIndex;
	}
	
	
}
