package net.gtcmt.audiosketch.p5.object;

import net.gtcmt.audiosketch.gui.util.GUIConstants;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Size2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.ObjectColorType;
import net.gtcmt.audiosketch.p5.window.MusicalWindow;
import net.gtcmt.audiosketch.sound.util.SndConstants.SndType;
import oscP5.OscMessage;
import oscP5.OscP5;

public class TableMessageRouter {
	private OscP5 oscP5;
	private int id, xPos, yPos;
	private float angle;
	boolean objectExists;
	private final float[][] playSpeedMultipliers=new float[][] {	//used for specifying values of playback speed multiplication for pitch change
		{.8f,1f,1.2f},
		{.8f,1f,1.2f},
		{.8f,1f,1.2f},
		{.8f,1f,1.2f},
		{.8f,1f,1.2f},
		{.8f,1f,1.2f},
		{.8f,1f,1.2f},
		{.8f,1f,1.2f},
		{.8f,1f,1.2f},
		{.8f,1f,1.2f},
		{.8f,1f,1.2f},
		{.8f,1f,1.2f}
	};

	private MusicalWindow theMusicalWindow;
	private final int firstMarkerIndex=108;
	
	public static final int tableMessageInPort=2100;
	
	public TableMessageRouter(MusicalWindow theMusicalWindow) {
		this.oscP5 = new OscP5(this, TableMessageRouter.tableMessageInPort);		
		this.theMusicalWindow = theMusicalWindow;
		System.out.println("TableMessageRouter Initialized");
		objectExists = false;
		//
		//xPos=300;
		//yPos=300;
		//theMusicalWindow.addTableObject(3,5, ObjectColorType.WHITE, SndType.values()[id], new P5Points2D(xPos, yPos), new P5Size2D(70, 70), 64);
	}
	
	/* incoming osc message are forwarded to the oscEvent method. */
	
	public void oscEvent(OscMessage theOscMessage) {
		if (theOscMessage.addrPattern().equalsIgnoreCase("/addObject")) {
			id = offsetIndex(theOscMessage.get(0).intValue());
			for(SoundObject object : theMusicalWindow.getSoundObject()){
				if(object.getId() == id){
					objectExists = true;
					break;
				}
			}

			if(!objectExists){
				xPos = scalePos(theOscMessage.get(2).floatValue(),GUIConstants.WINDOW_WIDTH);
				yPos = scalePos(theOscMessage.get(1).floatValue(),GUIConstants.WINDOW_HEIGHT);
				
				angle=theOscMessage.get(3).floatValue();

				System.out.println("Add Object ID: " + id + " x: " + xPos + " y: " + yPos);
				theMusicalWindow.addTableObject(id,id, ObjectColorType.WHITE, SndType.values()[id], new P5Points2D(xPos, yPos),
						new P5Size2D(70, 70), angle, playSpeedMultipliers[id]);
				
			}
			else{
				objectExists = false;
			}		
		} else if (theOscMessage.addrPattern().equalsIgnoreCase("/removeObject")) {	
			id = offsetIndex(theOscMessage.get(0).intValue());	
			theMusicalWindow.removeTableObject(id);
		} else if (theOscMessage.addrPattern().equalsIgnoreCase("/updateObject")) {
			id = offsetIndex(theOscMessage.get(0).intValue());
			//getSoundobject is a linked list
			for(SoundObject object : theMusicalWindow.getSoundObject()){
				if(object.getId() == id) {
					xPos = scalePos(theOscMessage.get(2).floatValue(),GUIConstants.WINDOW_WIDTH);
					yPos = scalePos(theOscMessage.get(1).floatValue(),GUIConstants.WINDOW_HEIGHT);
					angle=theOscMessage.get(3).floatValue();
					theMusicalWindow.moveObject(object, xPos, yPos, angle);
					break;
				}
			}
		}
	}
	
	
//	public int scaleDim(int windowDim, float posFloat,boolean flipped) {
//		
//		if (!flipped) {
//			return (int)Float*xWindowDim;
//		}
//		else {
//			return xWindowDim-(int)(xFloat*xWindowDim);
//		}
//		
//	}
	
	/**
	 * @param index Actual index of fiducial marker.
	 * @return The object indices shifted to start at 0.
	 */
	public int offsetIndex(int index) {
		
		return index-this.firstMarkerIndex;
	}
	
	/**
	 * 
	 * @param floatPos position of the marker in float from 0-1.
	 * @param windowDim The dimension of the window in integer format.
	 * @return The scaled position.
	 */
	public int scalePos(float floatPos, int windowDim) {
		
		return (int)(floatPos*windowDim);
		
		
	}
}

