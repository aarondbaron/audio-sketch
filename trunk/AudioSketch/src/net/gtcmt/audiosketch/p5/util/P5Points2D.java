package net.gtcmt.audiosketch.p5.util;

import java.io.Serializable;

/**
 * Class to hold processing points in x and y axis
 * @author akito
 *
 */
//TODO implement method for getting vector
public class P5Points2D implements Serializable {

	private static final long serialVersionUID = -7015057556932705099L;
	private int posX;
	private int posY;
	
	/**
	 * Constructor for creating points
	 * @param posX
	 * @param posY
	 */
	public P5Points2D(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
	}
	
	public float getMagnitude() {
	    return (float) Math.sqrt(posX*posX+posY*posY);
	}
	
	/*------------------ Getter / Setter --------------*/
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
}
