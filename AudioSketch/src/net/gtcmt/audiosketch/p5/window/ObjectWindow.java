package net.gtcmt.audiosketch.p5.window;

import java.io.IOException;
import java.net.UnknownHostException;

import net.gtcmt.audiosketch.gui.client.EditSoundObjectPanel;
import net.gtcmt.audiosketch.gui.util.GUIConstants;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.util.Constants;
import processing.core.PApplet;
import processing.core.PShape;

/**
 * 
 * @author akito
 * @deprecated
 */
public class ObjectWindow  extends PApplet {

	private static final long serialVersionUID = 2957684291479793914L;
	private EditSoundObjectPanel editPanel;
	public int objectWidth;
	public int objectHeight;
	private PShape[] shape;

	/**
	 * Constructor for object window
	 * @param editPanel
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public ObjectWindow(EditSoundObjectPanel editPanel) {
		this.editPanel = editPanel;
		
		/*//read all .svg files in the Sound object path
		File dir = new File(Constants.SOUND_OBJECT_PATH);
		//String[] children = dir.list();
		FilenameFilter filter = new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.endsWith(".svg");
		    }
		};
		String[] children = dir.list(filter);
		*/	

		//Create shapes
		shape = new PShape[P5Constants.NUM_SHAPE];
		for(int i=0;i<shape.length;i++){
			//shape[i] = this.loadShape(Constants.SOUND_OBJECT_PATH+children[i]);
			shape[i] = this.loadShape(Constants.SOUND_OBJECT_PATH+P5Constants.SHAPE_NAME[i]);
			shape[i].disableStyle();
			
		}
		

		

		/*if (children == null) {
		    // Either dir does not exist or is not a directory
		} else {
		    for (int j=0; j<children.length; j++) {
		        // Get filename of file or directory
		        String filename = children[j];
		        System.out.println(filename);
		    }
		}*/
		
		
		objectWidth = 100;
		objectHeight = 100;
	}

	/**
	 * Set up p5 canvas
	 */
	public void setup(){
		this.size(GUIConstants.EDITPANEL_WIDTH, GUIConstants.EDITPANEL_HEIGHT-GUIConstants.EDITAREA_HEIGHT);
		this.smooth();
	}

	public void draw(){
		this.background(0);
		colorPick();
		reSizeObject();
		
		this.shapeMode(PApplet.CORNER);
		this.shape(shape[editPanel.getObjectShape()], (width/2)-(objectWidth/2), 
				(height/2)-(objectHeight/2), objectWidth, objectHeight);
	}

	public void stop() {
		super.stop();
	}

	/**
	 * pick color for sound object
	 */
	private void colorPick(){
		switch(editPanel.getObjectColor()){
		case WHITE:	this.stroke(255, 255, 255, 200);	break;
		case BLUE:	this.stroke(0, 0, 255, 200);		break;
		case GREEN:	this.stroke(0, 255, 0, 200);		break;
		case YELLOW:	this.stroke(255, 255, 0, 200);	break;
		case ORANGE:	this.stroke(255, 125, 0, 200);	break;
		}				
		this.strokeWeight(5);
		this.fill(0, 0, 0, 0);
	}

	/**
	 * Resize sound object with mouse input
	 */
	//TODO work on better algorithm
	private void reSizeObject(){
		if(this.mousePressed){
			objectWidth = this.pmouseX + this.mouseX;
			objectHeight = this.pmouseY + this.mouseY;

			if(objectWidth <= 0)
				objectWidth = 1;
			if(objectHeight <= 0)
				objectHeight = 1;

			if(objectWidth > width)
				objectWidth = width;
			if(objectHeight > height)
				objectHeight = height;
		}
	}

	/*------------------ Getter/Setter -----------------*/
	public int getObjectWidth() {
		return objectWidth;
	}

	public void setObjectWidth(int objectWidth) {
		this.objectWidth = objectWidth;
	}

	public int getObjectHeight() {
		return objectHeight;
	}

	public void setObjectHeight(int objectHeight) {
		this.objectHeight = objectHeight;
	}
	
	
}
