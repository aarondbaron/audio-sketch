package net.gtcmt.audiosketch.p5.action;

import net.gtcmt.audiosketch.p5.object.PlayBackBar;
import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.util.P5Constants;

public class Collision {

	/**
	 * Detects collision for Radial time line. When collision happens initiate action.
	 */
	public static void collideCircle(SoundObject soundObject, PlayBackBar playBar){
		if(!soundObject.getCollideState(playBar)) {
			float objectX = (soundObject.getPosX()+(soundObject.getWidth()/2)) - playBar.getPosX();
			float objectY = (soundObject.getPosY()+(soundObject.getHeight()/2)) - playBar.getPosY();

			float minDistance = (float) ((Math.sqrt(Math.pow(soundObject.getWidth()/2, 2)
					+Math.pow(soundObject.getHeight()/2, 2))/4)+(playBar.getWidth()/2));

			if(Math.sqrt(objectX*objectX+objectY*objectY) < minDistance) {
				soundObject.play();
				soundObject.setCollideState(playBar, true);
				soundObject.setCollide(true);
				soundObject.setGetFrame(true);
				soundObject.setStartTime(System.currentTimeMillis());		
			}
		}
	}
	
	/**
	 * Detects collision for time line bar. When collision happens initiate action.
	 */
	//TODO Akito come up with better collision algorithm
	public static void collideBar(SoundObject soundObject, PlayBackBar playBar){
		if(!soundObject.getCollideState(playBar)){	//TODO this will not work
			float objectX = (float) ((soundObject.getPosX()+(soundObject.getWidth()/2)) 
					- (playBar.getPosX()+(Math.cos(playBar.getAngle()+Math.PI)*(P5Constants.COLLISION_AREA/2))));
			float objectY = (float) ((soundObject.getPosY()+(soundObject.getHeight()/2)) 
					- (playBar.getPosY()+(Math.sin(playBar.getAngle()+Math.PI)*(P5Constants.COLLISION_AREA/2))));

			float minDistance = (float) ((Math.sqrt(Math.pow(soundObject.getWidth()/2, 2)
					+Math.pow(soundObject.getHeight()/2, 2))/4)+((playBar.getCollisionArea()-400)/2));

			if(Math.sqrt(objectX*objectX+objectY*objectY) < minDistance) {
				soundObject.play();
				soundObject.setCollideState(playBar, true);
				soundObject.setCollide(true);
				soundObject.setGetFrame(true);
				soundObject.setStartTime(System.currentTimeMillis());
			}
		}
	}
}
