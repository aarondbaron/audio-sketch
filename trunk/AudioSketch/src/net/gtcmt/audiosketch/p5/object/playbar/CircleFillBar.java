package net.gtcmt.audiosketch.p5.object.playbar;

import java.util.LinkedList;

import net.gtcmt.audiosketch.p5.action.Collision;
import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import processing.core.PApplet;

public class CircleFillBar extends PlayBackBar{
	
	long time1 = System.currentTimeMillis();;
	long time2=0;
	long timeOutMS=250;
	
	public boolean bang = false;

	public CircleFillBar(P5Points2D objPos, float speed, float angle,
			PlayBackType pbType, PApplet p) {
		super(objPos, speed, angle, pbType, p);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public boolean checkState(LinkedList<SoundObject> soundObject, int index) {
		//Check for collision
		for(int j=0;j<soundObject.size();j++){
			Collision.collideCircleFillBar(soundObject.get(j), this, index);
		}
		
		if(this.getWidth() > p5.width>>1 &&this.getWidth() > p5.height>>1){			
			//go to each sound object in play bar
			for(int j=0; j<soundObject.size();j++){
				if(soundObject.get(j).getCollideState(index)){
					soundObject.get(j).setCollideState(index, false);
				}
			}			
		}
	
		//TODO implement removing
		return false;
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
		p5.strokeWeight(10);
		p5.stroke(255, 255, 255, 200);
		
		if(System.currentTimeMillis()  - time1 > timeOutMS){
			p5.fill(255, 25, 255, 200);
			//image.setVisible(true);
			this.bang=true;
			
		}
		else{
			p5.fill(0, 0, 0, 0);
		}
		
	
		time2 = System.currentTimeMillis() - time1;
		if(time2>1000)
		{
			p5.fill(255, 25, 255, 200);
			this.bang=false;
			//System.out.println(time2);
			time1=System.currentTimeMillis();
		}
		else{
			time2=System.currentTimeMillis()-time1;
		}
		int w=p5.mouseX;
		int h=p5.mouseY;
		//float speed = (float) Math.sqrt(Math.pow(p5.mouseX-xPos, 2)+Math.pow(mouseY-yPos, 2))/P5Constants.MAX_TRIG_DISTANCE;
		p5.ellipse(playbarPos.getPosX(), playbarPos.getPosY(), w, h);
		this.setWidth(w);
		this.setWidth(h);
		
	}

}
