package net.gtcmt.audiosketch.p5.object.playbar;

import java.util.LinkedList;

import net.gtcmt.audiosketch.p5.action.Collision;
import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import processing.core.PApplet;

public class Radial2Bar extends PlayBackBar{

	public Radial2Bar(P5Points2D objPos, float speed, float angle,
			PlayBackType pbType, PApplet p) {
		super(objPos, speed, angle, pbType, p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub	
		p5.strokeWeight(10);
		p5.stroke(255, 25, 255, 200);
		p5.fill(0, 0, 0, 0);
		p5.ellipse(playbarPos.getPosX(), playbarPos.getPosY(), playbarSize.getWidth(), playbarSize.getHeight());
		playbarSize.setSize(((int) (playbarSize.getWidth()+speed)), ((int) (playbarSize.getHeight()+speed)));
	}

	@Override
	public boolean checkState(LinkedList<SoundObject> soundObject) {
		//Check for collision
		for(int j=0;j<soundObject.size();j++){
			Collision.collideCircle(soundObject.get(j), this);
		}
		
		if(this.getWidth() > p5.width>>1 &&this.getWidth() > p5.height>>1){
			//playBackBar.remove(i);
			//int x  = playBackBar.get(i).getInitX();
			//int y = playBackBar.get(i).getInitY();
			//playBackBar.get(i).setPosX(x);
			//playBackBar.get(i).setPosX(y);
			this.setSize(0, 0); // reset the circle
			
			//go to each sound object in play bar
			for(int j=0; j<soundObject.size();j++){
				if(soundObject.get(j).getCollideState(this)){
					soundObject.get(j).setCollideState(this, false);
				}
			}
			
			//playBackBar.get(i).setWidth(0);
			//playBackBar.get(i).setHeight(0);
		}
	
		//TODO implement removing
		return false;
	
	}

}
