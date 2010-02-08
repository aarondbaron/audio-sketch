package net.gtcmt.audiosketch.p5.util;

public class P5Math {

	/**
	 * Finds the furthest corner of the window from the position where mouse is clicked and returns the distance
	 * between these two points
	 * 
	 * @param clickPositionX
	 * @param clickPositionY
	 * @param windowWidth
	 * @param windowHeight
	 * @return distance to the furthest corner of the window
	 */
	public static float compareDist(int clickPositionX, int clickPositionY, int windowWidth, int windowHeight) {
		int xCorner = 0;
		int yCorner = 0;
		
		// grab the corner with least distance in x axis
		if (clickPositionX > (windowWidth - clickPositionX)) {
			xCorner = 0;
		} else {
			xCorner = windowWidth;
		}
		
		// grab the corner with least distance in y axis
		if (clickPositionY > (windowHeight - clickPositionY)) {
			yCorner = 0;
		} else {
			yCorner = windowHeight;
		}
		
		// return the distance to the furthest corner
		return (float) Math.sqrt(Math.pow((clickPositionX-xCorner),2)+Math.pow((clickPositionY-yCorner), 2));
		
	}
}
