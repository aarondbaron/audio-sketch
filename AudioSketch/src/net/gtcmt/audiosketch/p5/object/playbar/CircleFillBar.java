package net.gtcmt.audiosketch.p5.object.playbar;

import java.util.LinkedList;

import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import processing.core.PApplet;

public class CircleFillBar extends PlayBackBar{
	
	long time1 = System.currentTimeMillis();;
	long time2=0;
	long timeOutMS=250;

	public CircleFillBar(P5Points2D objPos, float speed, float angle,
			PlayBackType pbType, PApplet p) {
		super(objPos, speed, angle, pbType, p);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public boolean checkState(LinkedList<SoundObject> soundObject, int index) {
		// TODO Auto-generated method stub
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
			
		}
		else{
			p5.fill(0, 0, 0, 0);
		}
		
		
		   // ... the code being measured ...
		time2 = System.currentTimeMillis() - time1;
		if(time2>1000)
		{
			p5.fill(255, 25, 255, 200);
			//System.out.println(time2);
			time1=System.currentTimeMillis();
		}
		else{
			time2=System.currentTimeMillis()-time1;
		}
		
		//float speed = (float) Math.sqrt(Math.pow(p5.mouseX-xPos, 2)+Math.pow(mouseY-yPos, 2))/P5Constants.MAX_TRIG_DISTANCE;
		p5.ellipse(playbarPos.getPosX(), playbarPos.getPosY(), p5.mouseX, p5.mouseY);
		
	}

}
