package net.gtcmt.audiosketch.network.data;

import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Size2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.ObjectColorType;
import net.gtcmt.audiosketch.p5.util.P5Constants.ObjectShapeType;
import net.gtcmt.audiosketch.sound.util.SndConstants.SndType;

/**
 * Contains information about sound object data. This class is used to
 * communicate between client and server.
 * @author akito
 *
 */
public final class SoundObjectData implements MessageData {
	
	private static final long serialVersionUID = 7727588609909160530L;
	private P5Points2D objPos;
	private P5Size2D objSize;
	private ObjectColorType colorType;
	private ObjectShapeType shapeType;
	private SndType sndType;
	private int midiNote;
	
	/**
	 * Create sound object data for passing between client and server
	 * @param shapeType	Shape of sound object
	 * @param colorType	color of sound object
	 * @param sndType	sound type of sound object
	 * @param objPos		initial position of sound object
	 * @param objSize	size of sound object
	 * @param midiNote	pitch of sound object
	 */
	public SoundObjectData(ObjectShapeType shapeType, ObjectColorType colorType, 
			SndType sndType, P5Points2D objPos, P5Size2D objSize, int midiNote){
		this.shapeType = shapeType;
		this.colorType = colorType;
		this.sndType = sndType;
		this.objPos = objPos;
		this.objSize = objSize;
		this.midiNote = midiNote;
	}

	
	/*------------------- Getter/Setter ----------------*/
	public int getPosX(){
		return objPos.getPosX();
	}
	
	public int getPosY(){
		return objPos.getPosY();
	}
	
	public int getWidth(){
		return objSize.getWidth();
	}
	
	public int getHeight(){
		return objSize.getHeight();
	}
	
	public P5Points2D getObjPos() {
		return objPos;
	}

	public void setObjPos(P5Points2D objPos) {
		this.objPos = objPos;
	}

	public P5Size2D getObjSize() {
		return objSize;
	}

	public void setObjSize(P5Size2D objSize) {
		this.objSize = objSize;
	}

	public ObjectColorType getColorType() {
		return colorType;
	}

	public void setColorType(ObjectColorType colorType) {
		this.colorType = colorType;
	}

	public ObjectShapeType getShapeType() {
		return shapeType;
	}

	public void setShapeType(ObjectShapeType shapeType) {
		this.shapeType = shapeType;
	}

	public SndType getSndType() {
		return sndType;
	}

	public void setSndType(SndType sndType) {
		this.sndType = sndType;
	}

	public int getMidiNote() {
		return midiNote;
	}

	public void setMidiNote(int midiNote) {
		this.midiNote = midiNote;
	}
}
