package net.gtcmt.audiosketch.p5.object.playbar;

import java.util.LinkedList;

import net.gtcmt.audiosketch.p5.action.Collision;
import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.util.P5Color;
import net.gtcmt.audiosketch.p5.util.P5Constants;
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
		p5.strokeWeight(P5Constants.STROKE_WEIGHT);
		if(!highLight){
			p5.stroke(P5Color.BABY_BLUE[0],P5Color.BABY_BLUE[1],P5Color.BABY_BLUE[2], 100);
		}
		else{
			p5.stroke(P5Color.ORANGE[0],P5Color.ORANGE[1],P5Color.ORANGE[2], 100);
		}
		p5.noFill();
		p5.ellipse(playbarPos.getPosX(), playbarPos.getPosY(), playbarSize.getWidth(), playbarSize.getHeight());
		playbarSize.setSize(((int) (playbarSize.getWidth()+speed)), ((int) (playbarSize.getHeight()+speed)));
	}

	@Override
	public boolean checkState(LinkedList<SoundObject> soundObject, int index) {
		//Check for collision
		synchronized (soundObject) {
			for(SoundObject so : soundObject){
				if(so.getCollideState().size() > index){
					Collision.collideCircle(so, this, index);
				}
			}
			
			if(this.getWidth() > (p5.width>>2) &&this.getWidth() > (p5.height>>2)){
				//playBackBar.remove(i);
				//int x  = playBackBar.get(i).getInitX();
				//int y = playBackBar.get(i).getInitY();
				//playBackBar.get(i).setPosX(x);
				//playBackBar.get(i).setPosX(y);
				this.setSize(0, 0); // reset the circle
				
				//go to each sound object in play bar
				for(SoundObject so : soundObject){
					if(so.getCollideState().size() > index){
						if(so.getCollideState(index)){
							so.setCollideState(index, false);
						}
					}
				}
				
				//playBackBar.get(i).setWidth(0);
				//playBackBar.get(i).setHeight(0);
			}
		
			//TODO implement removing
			return false;
		}
	}

}
