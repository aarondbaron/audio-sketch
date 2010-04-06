package net.gtcmt.audiosketch.p5.object.playbar;

import java.util.LinkedList;

import net.gtcmt.audiosketch.p5.action.Collision;
import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class ClockBar extends PlayBackBar{
	
	float oldClockAngle;
	float someAngle;
	
	
	public float getSomeAngle() {
		return someAngle;
	}


	public void setSomeAngle(float someAngle) {
		this.someAngle = someAngle;
	}


	public float getClockAngle() {
		return oldClockAngle;
	}


	public void setClockAngle(float clockAngle) {
		this.oldClockAngle = clockAngle;
	}


	int len=200;//this is the bar length...needs to be a parameter

	public int getLen() {
		return len;
	}


	public void setLen(int len) {
		this.len = len;
	}


	public ClockBar(P5Points2D objPos, float speed, float angle,
			PlayBackType pbType, PApplet p) {
		super(objPos, speed, angle, pbType, p);
		// TODO Auto-generated constructor stub
		oldClockAngle=0;
		//len=100;
	}
	

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
		
		int initx=this.getInitX();
		int inity=this.getInitY();
		
		int fposx= this.getPosX();
		int fposy = this.getPosY();
		//System.out.println("intix: " + initx + " inity: " +inity + "fposx:" +fposx + " fposy:" + fposy);
		

		
		oldClockAngle=oldClockAngle+(float).01;
		/*if(clockAngle>PConstants.TWO_PI){
			clockAngle=0;
		}*/
		//DisplayAngleMouse();
		float ox = (float)Math.sin(oldClockAngle);
		float oy= (float) Math.cos(oldClockAngle);

		PVector v = new PVector(ox, oy);
		PVector dum = new PVector(1,0); //the x axis
		float rawAngle = PVector.angleBetween(v, dum);
		someAngle = rawAngle;
		if (v.y < 0)
		{
			someAngle = PConstants.TWO_PI - someAngle;
		}
		//System.out.println("someAngle: " + someAngle + " oldClockAngle: " + oldClockAngle);
		p5.pushMatrix();
		p5.translate(playbarPos.getPosX(), playbarPos.getPosY());
		//p5.translate(initx, inity);
		//p5.translate(fposx, fposy);
		p5.rotate(someAngle);

		p5.stroke(250, 200, 25, 100);
		p5.strokeWeight(5);
		//p5.line(0, 0, 100, 0);

		p5.line(0, len, 0,0);
		//p5.fill(255, 255, 250, 200);
		//p5.ellipse(170, 0, 7, 7);
		//p5.ellipse(playbarPos.getPosX(), playbarPos.getPosY(), playbarSize.getWidth(), playbarSize.getHeight());
		
		p5.popMatrix();
		
		//this.setPosX(fposx+1);
		//this.setPosY(fposy+1);
		//System.out.println(clockAngle);


	}

	@Override
	public boolean checkState(LinkedList<SoundObject> soundObject, int index) {
		synchronized (soundObject) {
			//Check for collision
			for(SoundObject so : soundObject){
				if(so.getCollideState().size() > index){
					Collision.collideClockBar(so, this, index);
				}
			}
			
			//go to each sound object in play bar
			for(SoundObject so : soundObject){
				if(so.getCollideState().size() > index){
					if(so.getCollideState(index)){
						so.setCollideState(index, false);
					}
				}
			}
			return false;
		}	
	}
}
