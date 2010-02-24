package net.gtcmt.audiosketch.p5.object.playbar;

import java.util.LinkedList;

import net.gtcmt.audiosketch.p5.action.Collision;
import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import processing.core.PApplet;
import processing.core.PConstants;

public class Bar extends PlayBackBar{

	public Bar(P5Points2D objPos, float speed, float angle,
			PlayBackType pbType, PApplet p) {
		super(objPos, speed, angle, pbType, p);
	}

	@Override
	public void draw() {
		p5.pushMatrix();	
		p5.translate(playbarPos.getPosX(), playbarPos.getPosY());
		p5.rotate((float) (angle+P5Constants.NINETY));
		p5.rectMode(PConstants.CENTER);
		p5.noStroke();
		p5.fill(255, 255, 255, 200);
		p5.rect(0, 0, playbarSize.getWidth(), playbarSize.getHeight());
		p5.popMatrix();
		
		p5.pushMatrix(); //Used for collision detection
		p5.translate(initPlaybarPos.getPosX()+(float)(Math.cos(angle+Math.PI)*(P5Constants.COLLISION_AREA/2)), initPlaybarPos.getPosY()+(float) (Math.sin(angle+Math.PI)*(P5Constants.COLLISION_AREA/2)));	
		p5.noStroke();
		p5.noFill();
		p5.scale(collisionArea/P5Constants.COLLISION_AREA, collisionArea/P5Constants.COLLISION_AREA);
		p5.ellipse(0, 0, P5Constants.COLLISION_AREA, P5Constants.COLLISION_AREA);
		p5.popMatrix();
		playbarPos.setPosX((int) (playbarPos.getPosX()+speed*Math.cos(angle)));
		playbarPos.setPosY((int) (playbarPos.getPosY()+speed*Math.sin(angle)));
		collisionArea += speed*2;
		
	}

	@Override
	public boolean checkState(LinkedList<SoundObject> soundObject, int index) {
		//Check for collision
		for(int j=0;j<soundObject.size();j++){
			Collision.collideBar(soundObject.get(j), this, index);
		}
		
		if((this.getPosX() < 0 && this.getPosY() < 0) 
				|| (this.getPosX() < 0 && this.getPosY() > p5.height) 
				|| (this.getPosX() > p5.width && this.getPosY() < 0) 
				|| (this.getPosX() > p5.width && this.getPosY() > p5.height)
				|| (this.getPosX() < -(P5Constants.BAR_WIDTH)) || (this.getPosX() > p5.width+P5Constants.BAR_WIDTH)
				|| (this.getPosY() < -(P5Constants.BAR_WIDTH)) || (this.getPosY() > p5.height+P5Constants.BAR_WIDTH)) {
			for(int j=0;j<soundObject.size();j++){
				soundObject.get(j).removeCollideState(index);
			}
			return true;
		}
		return false;
	}

}
