package net.gtcmt.audiosketch.p5.object.playbar;

import java.util.LinkedList;

import net.gtcmt.audiosketch.p5.action.Collision;
import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.util.P5Math;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import processing.core.PApplet;

public class RadialBar extends PlayBackBar {

	private float boundVal;
	public RadialBar(P5Points2D objPos, float speed, float angle,
			PlayBackType pbType, PApplet p) {
		super(objPos, speed, angle, pbType, p);
		boundVal = P5Math.compareDist(this.getInitX(), this.getInitY(), p5.width, p5.height);
	}

	@Override
	public void draw() {
		p5.strokeWeight(10);
		p5.stroke(255, 255, 255, 200);
		p5.fill(0, 0, 0, 0);
		p5.ellipse(playbarPos.getPosX(), playbarPos.getPosY(), playbarSize.getWidth(), playbarSize.getHeight());
		playbarSize.setSize(((int) (playbarSize.getWidth()+speed)), ((int) (playbarSize.getHeight()+speed)));
	}

	@Override
	public boolean checkState(LinkedList<SoundObject> soundObject, int index) {
		//Check for collision
		for(int j=0;j<soundObject.size();j++){
			Collision.collideCircle(soundObject.get(j), this, index);
		}
		
		// remove the radial playBar when it is out of the window
		if(getWidth()>>1 > boundVal + 100) {
			for(int j=0;j<soundObject.size();j++){
				soundObject.get(j).removeCollideState(index);
			}
			return true;
		}
		
		return false;
	}

}
