package net.gtcmt.audiosketch.p5.object.playbar;

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

}
