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
	private MusicalWindow theMusicalWindow;
	private final int firstMarkerIndex=114;
	
	public static final int tableMessageInPort=2100;
	
	public TableMessageRouter(MusicalWindow theMusicalWindow) {
		this.oscP5 = new OscP5(this, TableMessageRouter.tableMessageInPort);		
		this.theMusicalWindow = theMusicalWindow;
		System.out.println("TableMessageRouter Initialized");
		
		//
		xPos=300;
		yPos=300;
		//theMusicalWindow.addTableObject(3,5, ObjectColorType.WHITE, SndType.values()[id], new P5Points2D(xPos, yPos), new P5Size2D(70, 70), 64);
	}
	
	/* incoming osc message are forwarded to the oscEvent method. */
	
	public void oscEvent(OscMessage theOscMessage) {
		//System.out.println(theOscMessage.toString());
		
		if (theOscMessage.addrPattern().equalsIgnoreCase("/addObject")) {
			id = offsetIndex(theOscMessage.get(0).intValue());
			xPos = GUIConstants.WINDOW_WIDTH-scalePos(theOscMessage.get(1).floatValue(),GUIConstants.WINDOW_WIDTH);
			yPos = scalePos(theOscMessage.get(2).floatValue(),GUIConstants.WINDOW_HEIGHT);

//			System.out.println("Add Object ID: " + id + " x: " + xPos + " y: " + yPos);
			theMusicalWindow.addTableObject(id,id, ObjectColorType.WHITE, SndType.values()[id], new P5Points2D(xPos, yPos), new P5Size2D(70, 70), 64);
			
			

			
		} else if (theOscMessage.addrPattern().equalsIgnoreCase("/removeObject")) {
			id = offsetIndex(theOscMessage.get(0).intValue());	
			
			theMusicalWindow.removeTableObject(id);
			
//			System.out.println("Remove Object ID: " + id);
			
		} else if (theOscMessage.addrPattern().equalsIgnoreCase("/updateObject")) {
			id = offsetIndex(theOscMessage.get(0).intValue());
			xPos = GUIConstants.WINDOW_WIDTH-scalePos(theOscMessage.get(1).floatValue(),GUIConstants.WINDOW_WIDTH);
			yPos = scalePos(theOscMessage.get(2).floatValue(),GUIConstants.WINDOW_HEIGHT);
			
			theMusicalWindow.moveObject(id, xPos, yPos);

			//System.out.println("Position Object ID: " + id + " x: " + xPos + " y: " + yPos);
		}
			
			
//		this.haveReceivedMsg=true;
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

