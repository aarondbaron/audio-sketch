package net.gtcmt.audiosketch.p5.action;

import java.util.LinkedList;

import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.window.MusicalWindow;

/**
 * Mostly handle mouse action on sound object
 * @author akito
 * @deprecated
 */
public class MouseAction {

	private static final int MOUSE_AREA = 20;
	private LinkedList<SoundObject> soundObject;
	private MusicalWindow mwp5;
	private LinkedList<Boolean> mouseOver;
	private LinkedList<Boolean> mousePressed;
	private LinkedList<Integer> moveX, moveY;		//Holds information on how much object moves
	
	/**
	 * Constructor for Object Action
	 * @param soundObject
	 * @param m
	 */
	public MouseAction(LinkedList<SoundObject> soundObject, MusicalWindow mw){
		this.soundObject = soundObject;
		this.mwp5 = mw;
		moveX = new LinkedList<Integer>();
		moveY = new LinkedList<Integer>();
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
	public void detectObjectArea() {
		int mouseX = mwp5.mouseX, mouseY = mwp5.mouseY;
		int leftX,topY,halfWidth,halfHeight; 
		for(int i=0; i<soundObject.size(); i++) {
			//Boundary condition as square inside circle
			halfWidth = (soundObject.get(i).getWidth() >> 1) - MOUSE_AREA;
			halfHeight = (soundObject.get(i).getHeight() >> 1) - MOUSE_AREA;
			
			leftX = soundObject.get(i).getPosX() - halfWidth;
			topY = soundObject.get(i).getPosY() - halfHeight;
			
			if(mouseX > leftX && mouseX < soundObject.get(i).getPosX() + halfWidth
					&& mouseY > topY && mouseY < soundObject.get(i).getPosY() + halfHeight) {
				mouseOver.set(i, true);	//Mouse is over the ball				
			}
			else{
				mouseOver.set(i, false);	//Mouse is not over the ball
			}
		}
	}
	
	/**
	 * Sends dragging information to server to move objects
	 */
	public void mouseDragged() {
		for(int i=0; i<soundObject.size(); i++) {
			if(mousePressed.get(i)) {
				if(moveX.get(i) > mwp5.width){
					moveX.set(i, mwp5.width);
				}
				else if(moveX.get(i) < 0){
					moveX.set(i,  0);
				}
				else{
					moveX.set(i, moveX.get(i) + (mwp5.mouseX - mwp5.pmouseX));
				}
				
				if(moveY.get(i) > mwp5.height){
					moveY.set(i, mwp5.height);
				}
				else if(moveY.get(i) < 0){
					moveY.set(i, 0);
				}
				else{
					moveY.set(i, moveY.get(i) + (mwp5.mouseY - mwp5.pmouseY));
				}
				
				mwp5.moveObject(i, moveX.get(i).intValue(), moveY.get(i).intValue(),0);
			}
		}
	}
	
	public void mouseReleased() {
		if (!mwp5.mousePressed)
			for(int i=0; i<soundObject.size(); i++)
				mousePressed.set(i, false);		
	}

	public void mousePressed() {
		for(int i=0; i<soundObject.size(); i++) {
			if(mouseOver.get(i) && mwp5.mousePressed) 
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
		moveX.remove(index);
		moveY.remove(index);
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

	public LinkedList<Integer> getMoveX() {
		return moveX;
	}

	public void setMoveX(LinkedList<Integer> moveX) {
		this.moveX = moveX;
	}

	public LinkedList<Integer> getMoveY() {
		return moveY;
	}

	public void setMoveY(LinkedList<Integer> moveY) {
		this.moveY = moveY;
	}
}
