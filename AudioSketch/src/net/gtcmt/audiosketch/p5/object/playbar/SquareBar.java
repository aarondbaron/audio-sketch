package net.gtcmt.audiosketch.p5.object.playbar;

import java.util.LinkedList;

import net.gtcmt.audiosketch.p5.action.Collision;
import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import processing.core.PApplet;

public class SquareBar extends PlayBackBar {
	
	int strokeParam=0;
	int strokeUpdate=1;

	public SquareBar(P5Points2D objPos, float speed, float angle,
			PlayBackType pbType, PApplet p) {
		super(objPos, speed, angle, pbType, p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		strokeParam=strokeParam+strokeUpdate;
		if(strokeParam==256)
		{
			strokeParam=255;
			strokeUpdate=-1;
		}
		if(strokeParam==-1)
		{
			strokeParam=0;
			strokeUpdate=1;
		}
		
		p5.strokeWeight(10);
		p5.stroke(255-strokeParam, strokeParam, 255, 200);
		p5.fill(0, 0, 0, 0);
		p5.rect(playbarPos.getPosX(), playbarPos.getPosY(), playbarSize.getWidth(), playbarSize.getHeight());
		playbarSize.setSize(((int) (playbarSize.getWidth()+speed)), ((int) (playbarSize.getHeight()+speed)));
	}

	@Override
	public boolean checkState(LinkedList<SoundObject> soundObject, int index) {
		//Check for collision
		//need a 
		for(int j=0;j<soundObject.size();j++){
			Collision.collideSquare(soundObject.get(j), this, index);
		}
		
		if(this.getWidth() > p5.width>>1 && this.getWidth() > p5.height>>1){
			//playBackBar.remove(i);
			//int x  = playBackBar.get(i).getInitX();
			//int y = playBackBar.get(i).getInitY();
			//playBackBar.get(i).setPosX(x);
			//playBackBar.get(i).setPosX(y);
			this.setSize(0, 0); // reset the circle
			
			//go to each sound object in play bar
			for(int j=0; j<soundObject.size();j++){
				if(soundObject.get(j).getCollideState(index)){
					soundObject.get(j).setCollideState(index, false);
				}
			}
			
			//playBackBar.get(i).setWidth(0);
			//playBackBar.get(i).setHeight(0);
		}
	
		return false;
		//TODO implement removing
	}

}
