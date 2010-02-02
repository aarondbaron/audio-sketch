package net.gtcmt.audiosketch.client.visual;

import java.awt.Color;
import java.io.IOException;
import java.net.UnknownHostException;

import net.gtcmt.audiosketch.client.gui.EditSoundObjectPanel;
import net.gtcmt.audiosketch.client.gui.util.GUIConstants;
import net.gtcmt.audiosketch.client.visual.util.VisualConstants;
import net.gtcmt.audiosketch.network.util.AudioSketchProtocol;
import net.gtcmt.audiosketch.network.util.MsgType;
import net.gtcmt.audiosketch.util.Constants;
import net.gtcmt.audiosketch.util.LogMessage;
import processing.core.PApplet;
import processing.core.PShape;
import ddf.minim.AudioOutput;
import ddf.minim.Minim;

public class ObjectWindow  extends PApplet {

	private static final long serialVersionUID = 2957684291479793914L;
	private EditSoundObjectPanel editPanel;
	private String input;
	public String[] data;
	public int objectWidth=100;
	public int objectHeight=100;
	public Minim minim;
	public AudioOutput audioOut;
	private PShape[] shape;

	/**
	 * Constructor for object window
	 * @param editPanel
	 * @throws UnknownHostException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public ObjectWindow(EditSoundObjectPanel editPanel) throws UnknownHostException, IOException, InterruptedException{
		this.editPanel = editPanel;

		//Create shapes
		shape = new PShape[VisualConstants.NUM_SHAPE];
		for(int i=0;i<shape.length;i++){
			shape[i] = this.loadShape(Constants.OBJECT_PATH+VisualConstants.SHAPE_NAME[i]);
			shape[i].disableStyle();
		}
	}

	/**
	 * Set up p5 canvas
	 */
	public void setup(){
		this.size(GUIConstants.EDITPANEL_WIDTH, GUIConstants.EDITPANEL_HEIGHT-50);
		this.smooth();
		minim = new Minim(this);
	}

	public void draw(){
		this.background(0);
		read();
		colorPick();
		reSizeObject();
		
		this.shape(shape[editPanel.getObjectShape().ordinal()], (width/2)-(objectWidth/2), 
				(height/2)-(objectHeight/2), objectWidth, objectHeight);
	}

	public void stop() {
		minim.stop();
		super.stop();
	}

	private void read() {
		if (editPanel.getClient() != null && editPanel.getClient().available() > 0) {
			input = editPanel.getClient().readStringUntil(AudioSketchProtocol.TERMINATOR_BYTE);
			data = AudioSketchProtocol.createTokens(input);
		
			if(MsgType.contains(data[0])){
				switch(MsgType.valueOf(data[0])){
				case CHAT:
					// display a message
					editPanel.getMainFrame().getChatWindow().append(Color.BLUE, data[1]+" ");
					for(int i=2;i<data.length;i++){
						editPanel.getMainFrame().getChatWindow().append(Color.DARK_GRAY, data[i]+" ");
					}
					//add new line
					editPanel.getMainFrame().getChatWindow().append(Color.DARK_GRAY, "\n");
					break;
				case LOGIN:
					//Display message on chat window
					editPanel.getMainFrame().getChatWindow().append(Color.DARK_GRAY, "Welcome "+data[1]+" ");
					for(int i=2;i<data.length;i++){
						editPanel.getMainFrame().getChatWindow().append(Color.DARK_GRAY, data[i]+" ");
					}
					editPanel.getMainFrame().getChatWindow().append(Color.DARK_GRAY, "\n");
					break;
				case QUIT:
					editPanel.getMainFrame().getChatWindow().append(Color.DARK_GRAY, "GoodBye "+data[1]+" ");
					for(int i=2;i<data.length;i++){
						editPanel.getMainFrame().getChatWindow().append(Color.DARK_GRAY, data[i]+" ");
					}
					editPanel.getMainFrame().getChatWindow().append(Color.DARK_GRAY, "\n");
					break;
				default:

				}
			}
			else{
				LogMessage.err("MsgType does not contain "+data[0]+"!");
			}
		}
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
