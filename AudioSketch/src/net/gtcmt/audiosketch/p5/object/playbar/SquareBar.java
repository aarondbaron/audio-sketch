package net.gtcmt.audiosketch.p5.object.playbar;

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
		p5.stroke(255, strokeParam, 255, 200);
		p5.fill(0, 0, 0, 0);
		p5.rect(playbarPos.getPosX(), playbarPos.getPosY(), playbarSize.getWidth(), playbarSize.getHeight());
		playbarSize.setSize(((int) (playbarSize.getWidth()+speed)), ((int) (playbarSize.getHeight()+speed)));
	}

}
