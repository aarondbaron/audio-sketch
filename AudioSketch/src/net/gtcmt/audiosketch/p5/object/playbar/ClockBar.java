package net.gtcmt.audiosketch.p5.object.playbar;

import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class ClockBar extends PlayBackBar{
	
	float clockAngle;

	public ClockBar(P5Points2D objPos, float speed, float angle,
			PlayBackType pbType, PApplet p) {
		super(objPos, speed, angle, pbType, p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

		
		int initx=this.getInitX();
		int inity=this.getInitY();
		
		int fposx= this.getPosX();
		int fposy = this.getPosY();
		//System.out.println("intix: " + initx + " inity: " +inity + "fposx:" +fposx + " fposy:" + fposy);
		
		clockAngle=clockAngle+(float).02;
		//DisplayAngleMouse();
		float ox = (float)Math.sin(clockAngle);
		float oy= (float) Math.cos(clockAngle);

		PVector v = new PVector(ox, oy);
		PVector dum = new PVector(100000,0); //the x axis
		float rawAngle = PVector.angleBetween(v, dum);
		float clockAngle = rawAngle;
		if (v.y < 0)
		{
			clockAngle = PConstants.TWO_PI - clockAngle;
		}

		p5.pushMatrix();
		p5.translate(playbarPos.getPosX(), playbarPos.getPosY());
		//p5.translate(initx, inity);
		//p5.translate(fposx, fposy);
		p5.rotate(clockAngle);

		p5.stroke(250, 200, 25, 100);
		p5.strokeWeight(5);
		//p5.line(0, 0, 100, 0);
		int len=50;
		p5.line(0,0,len, len);
		//p5.fill(255, 255, 250, 200);
		//p5.ellipse(170, 0, 7, 7);
		//p5.ellipse(playbarPos.getPosX(), playbarPos.getPosY(), playbarSize.getWidth(), playbarSize.getHeight());
		
		p5.popMatrix();
		
		PVector point = new PVector((int) (len*Math.cos(clockAngle)),(int) (len*Math.sin(clockAngle)));
		
		//System.out.println(clockAngle);


	}
	
	
	

}
