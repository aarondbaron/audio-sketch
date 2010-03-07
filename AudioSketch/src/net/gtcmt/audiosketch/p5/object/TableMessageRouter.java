package net.gtcmt.audiosketch.p5.object;

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
	
	public static final int tableMessageInPort=2000;
	
	public TableMessageRouter(MusicalWindow theMusicalWindow) {
		this.oscP5 = new OscP5(this, TableMessageRouter.tableMessageInPort);		
		this.theMusicalWindow = theMusicalWindow;
//		System.out.println("TableMessageRouter Initialized");
	}
	
	/* incoming osc message are forwarded to the oscEvent method. */
	public void oscEvent(OscMessage theOscMessage) {
//		System.out.println(theOscMessage.toString());
		
		if (theOscMessage.addrPattern().equalsIgnoreCase("/addObject")) {
			id = theOscMessage.get(0).intValue();
			xPos = theOscMessage.get(1).intValue();
			yPos = theOscMessage.get(2).intValue();

//			System.out.println("Add Object ID: " + id + " x: " + xPos + " y: " + yPos);
			theMusicalWindow.addTableObject(id,id, ObjectColorType.ORANGE, SndType.BUZZ, new P5Points2D(xPos, yPos), new P5Size2D(30, 30), 64);

			
		} else if (theOscMessage.addrPattern().equalsIgnoreCase("/removeObject")) {
			id = theOscMessage.get(0).intValue();	
			
			theMusicalWindow.removeTableObject(id);
			
//			System.out.println("Remove Object ID: " + id);
			
		} else if (theOscMessage.addrPattern().equalsIgnoreCase("/updateObject")) {
			id = theOscMessage.get(0).intValue();
			xPos = theOscMessage.get(1).intValue();
			yPos = theOscMessage.get(2).intValue();
			
			theMusicalWindow.moveObject(id, xPos, yPos);

//			System.out.println("Position Object ID: " + id + " x: " + xPos + " y: " + yPos);
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
}

