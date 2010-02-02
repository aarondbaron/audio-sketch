package net.gtcmt.audiosketch.client.visual;

import java.util.LinkedList;

import net.gtcmt.audiosketch.client.util.SoundObject;
import net.gtcmt.audiosketch.protocol.AudioSketchProtocol;
import net.gtcmt.audiosketch.protocol.AudioSketchProtocol.MsgType;

/**
 * Mostly handle mouse action on sound object
 * @author akito
 *
 */
public class ObjectAction {

	private LinkedList<SoundObject> soundObject;
	private MusicalWindow musicalWindow;
	private LinkedList<Boolean> mouseOver;
	private LinkedList<Boolean> mousePressed;
	private LinkedList<Float> moveX, moveY;
	
	/**
	 * Constructor for Object Action
	 * @param soundObject
	 * @param m
	 */
	public ObjectAction(LinkedList<SoundObject> soundObject, MusicalWindow m){
		this.soundObject = soundObject;
		this.musicalWindow = m;
		moveX = new LinkedList<Float>();
		moveY = new LinkedList<Float>();
		for(int i=0;i<soundObject.size();i++){
			moveX.add(soundObject.get(i).getPosX());
			moveY.add(soundObject.get(i).getPosY());
		}
			
		mouseOver = new LinkedList<Boolean>();
		mousePressed = new LinkedList<Boolean>();
		
		for(int i=0; i<soundObject.size(); i++) {
			mouseOver.add(false);
			mousePressed.add(false);
		}
	}

	/**
	 * Detects if the mouse is over the sound object
	 */
	//TODO come up with better algorithm
	public void detectObjectArea() {
		for(int i=0; i<soundObject.size(); i++) {
			//Boundary condition as square inside circle
			if(musicalWindow.mouseX > soundObject.get(i).getPosX() && musicalWindow.mouseX < soundObject.get(i).getPosX() + soundObject.get(i).getWidth() 
					&& musicalWindow.mouseY > soundObject.get(i).getPosY() && musicalWindow.mouseY < soundObject.get(i).getPosY() + soundObject.get(i).getHeight()) {
				
				mouseOver.set(i, true);	//Mouse is over the ball				
			}
			else
				mouseOver.set(i, false);	//Mouse is not over the ball
		}
	}
	
	/**
	 * Sends dragging information to server to move objects
	 */
	public void mouseDragged() {
		for(int i=0; i<soundObject.size(); i++) {
			if(mousePressed.get(i)) {
				if(moveX.get(i) > musicalWindow.width){
					moveX.set(i, (float) musicalWindow.width);
				}
				else if(moveX.get(i) < 0){
					moveX.set(i,  0f);
				}
				else{
					moveX.set(i, moveX.get(i) + (musicalWindow.mouseX - musicalWindow.pmouseX));
				}
				
				if(moveY.get(i) > musicalWindow.height){
					moveY.set(i, (float) musicalWindow.height);
				}
				else if(moveY.get(i) < 0){
					moveY.set(i, 0f);
				}
				else{
					moveY.set(i, moveY.get(i) + (musicalWindow.mouseY - musicalWindow.pmouseY));
				}
				//Broad cast action
				musicalWindow.getClient().write(MsgType.MOVE.toString()+AudioSketchProtocol.SPLITTER+i+AudioSketchProtocol.SPLITTER+moveX.get(i)+AudioSketchProtocol.SPLITTER+moveY.get(i)+AudioSketchProtocol.TERMINATOR);
			}
		}
	}
	
	public void mouseReleased() {
		if (!musicalWindow.mousePressed)
			for(int i=0; i<soundObject.size(); i++)
				mousePressed.set(i, false);		
	}

	public void mousePressed() {
		for(int i=0; i<soundObject.size(); i++) {
			if(mouseOver.get(i) && musicalWindow.mousePressed) 
				mousePressed.set(i, true);
		}
	}

	public void addActionObject(SoundObject soundObject) {
		mouseOver.add(false);
		mousePressed.add(false);
		moveX.add(soundObject.getPosX());
		moveY.add(soundObject.getPosY());
	}
	
	public void removeMouseEvent(int index) {
		mouseOver.remove(index);
		mousePressed.remove(index);
	}

	/*------------------ Getter/Setter ---------------*/
	public LinkedList<Boolean> getMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(LinkedList<Boolean> mouseOver) {
		this.mouseOver = mouseOver;
	}

	public LinkedList<Boolean> getMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(LinkedList<Boolean> mousePressed) {
		this.mousePressed = mousePressed;
	}

	public LinkedList<Float> getMoveX() {
		return moveX;
	}

	public void setMoveX(LinkedList<Float> moveX) {
		this.moveX = moveX;
	}

	public LinkedList<Float> getMoveY() {
		return moveY;
	}

	public void setMoveY(LinkedList<Float> moveY) {
		this.moveY = moveY;
	}
}
