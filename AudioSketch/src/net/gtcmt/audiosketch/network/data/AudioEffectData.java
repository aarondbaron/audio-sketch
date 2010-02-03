package net.gtcmt.audiosketch.network.data;

import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Size2D;
import net.gtcmt.audiosketch.sound.util.SndConstants.EffectType;

/**
 * Data container for effect box drawn on p5 canvas
 * @author akito
 *
 */
public final class AudioEffectData implements MessageData {

	private static final long serialVersionUID = 9126397689844135479L;

	private P5Points2D boxPos;
	private P5Size2D boxSize;
	private EffectType effType;
	
	public AudioEffectData(EffectType effType, P5Points2D boxPos, P5Size2D boxSize){
		this.effType = effType;
		this.boxPos = boxPos;
		this.boxSize = boxSize;
	}

	public P5Points2D getBoxPos() {
		return boxPos;
	}

	public void setBoxPos(P5Points2D boxPos) {
		this.boxPos = boxPos;
	}

	public P5Size2D getBoxSize() {
		return boxSize;
	}

	public void setBoxSize(P5Size2D boxSize) {
		this.boxSize = boxSize;
	}

	public EffectType getEffType() {
		return effType;
	}

	public void setEffType(EffectType effType) {
		this.effType = effType;
	}
	
	
}
