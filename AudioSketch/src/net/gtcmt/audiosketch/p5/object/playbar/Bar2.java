package net.gtcmt.audiosketch.p5.object.playbar;

import java.util.LinkedList;

import net.gtcmt.audiosketch.p5.action.Collision;
import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import processing.core.PApplet;
import processing.core.PConstants;

public class Bar2 extends PlayBackBar{

	public Bar2(P5Points2D objPos, float speed, float angle,
			PlayBackType pbType, PApplet p) {
		super(objPos, speed, angle, pbType, p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		p5.pushMatrix();	
		p5.translate(playbarPos.getPosX(), playbarPos.getPosY());
		p5.rotate((float) (angle+P5Constants.NINETY));
		p5.rectMode(PConstants.CENTER);
		p5.noStroke();
		p5.fill(255, 255, 250, 200);
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
		synchronized (soundObject) {
			//Check for collision
			for(SoundObject so : soundObject){
				if(so.getCollideState().size() > index){
					Collision.collideBar(so, this, index);
				}
			}

			if((this.getPosX() < 0 && this.getPosY() < 0) 
					|| (this.getPosX() < 0 && this.getPosY() > p5.height) 
					|| (this.getPosX() > p5.width && this.getPosY() < 0) 
					|| (this.getPosX() > p5.width && this.getPosY() > p5.height)
					|| (this.getPosX() < -(P5Constants.BAR_WIDTH)) || (this.getPosX() > p5.width+P5Constants.BAR_WIDTH)
					|| (this.getPosY() < -(P5Constants.BAR_WIDTH)) || (this.getPosY() > p5.height+P5Constants.BAR_WIDTH)) {
				//playBackBar.remove(i);
				int x = this.getInitX();
				int y = this.getInitY();
				this.setPosX(0);
				this.setPosY(0);
				
				//Boolean b = false;
				//playBackBar.get(i).setTrigState(i, b);// i put this here so it will change the trigger state object, when it resets in the previous line
				//playBackBar.get(i).setSize(0, 0);
			}

			//TODO implement removing
			return false;
		}
	}

}
