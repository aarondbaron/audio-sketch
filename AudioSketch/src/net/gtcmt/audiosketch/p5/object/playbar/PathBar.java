package net.gtcmt.audiosketch.p5.object.playbar;

import java.util.LinkedList;

import net.gtcmt.audiosketch.p5.action.Collision;
import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import net.gtcmt.audiosketch.util.Constants;
import processing.core.PApplet;
import processing.core.PShape;

public class PathBar extends PlayBackBar{
	
	long time1 = System.currentTimeMillis();;
	long time2=0;
	long timeOutMS=1000;
	long startTime;
	public boolean bang = false;
	PShape image;
	PShape children[]= new PShape[10];

	public PathBar(P5Points2D objPos, float speed, float angle,
			PlayBackType pbType, PApplet p) {
		super(objPos, speed, angle, pbType, p);
		startTime = System.currentTimeMillis();
		this.image = p.loadShape("lib/PlayBarImages/path.svg");
		this.angle=angle;
		
		//some of these children will be null if there aren't that many actual children, but you can handle that.
		for (int i=0;i<children.length;i++){
			   
			   children[i]=this.image.getChild(Integer.toString(i+1));
			    
		}
		
		
		
		
	}
	

	@Override
	public boolean checkState(LinkedList<SoundObject> soundObject, int index) {
		//Check for collision
		synchronized (soundObject) {
			for(SoundObject so : soundObject){
				if(so.getCollideState().size() > index){
					Collision.collideCircleFillBar(so, this, index);
				}
			}
			
			if(this.getWidth() > p5.width>>1 &&this.getWidth() > p5.height>>1){			
				//go to each sound object in play bar
				for(SoundObject so : soundObject){
					if(so.getCollideState().size() > index){
						if(so.getCollideState(index)){
							so.setCollideState(index, false);
						}
					}
				}			
			}
		
			//TODO implement removing
			return false;
		}
	}

	@Override
	public void draw() {
		
		p5.strokeWeight(P5Constants.STROKE_WEIGHT-3);
		p5.stroke(255);
		//p5.stroke(255, 255, 255, 100);
		
		//time2 = System.currentTimeMillis() - time1;
		
		//Draws fill until the time out. set bang to true on first time entered.
		if((System.currentTimeMillis()-startTime) % timeOutMS > (timeOutMS >> 1)){
			p5.noFill();
			if(this.bang){
				this.bang = false;
			}
		}
		else {
			p5.fill(255, 25, 255, 100);
			//image.setVisible(true);
			if(!bang){
				this.bang=true;
			}	
		}
		

//		if(time2>1000)
//		{
//			p5.fill(255, 25, 255, 200);
//			this.bang=false;
//			System.out.println("time2");
//			time1=System.currentTimeMillis();
//		}
//		else{
//			time2=System.currentTimeMillis()-time1;
//		}
		
		int w=150;
		int h=150;
		//float speed = (float) Math.sqrt(Math.pow(p5.mouseX-xPos, 2)+Math.pow(mouseY-yPos, 2))/P5Constants.MAX_TRIG_DISTANCE;
		p5.ellipse(playbarPos.getPosX(), playbarPos.getPosY(), w, h);
		this.setWidth(w);
		this.setWidth(h);		
	}
}
