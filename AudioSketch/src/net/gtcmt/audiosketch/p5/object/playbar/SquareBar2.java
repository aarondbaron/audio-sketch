package net.gtcmt.audiosketch.p5.object.playbar;

import java.util.LinkedList;

import net.gtcmt.audiosketch.p5.action.Collision;
import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.util.P5Color;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import processing.core.PApplet;

public class SquareBar2 extends PlayBackBar {

	private float boundVal;
	public SquareBar2(P5Points2D objPos, float speed, float angle,
			PlayBackType pbType, PApplet p) {
		super(objPos, speed, angle, pbType, p);
		boundVal = (p.width>>4)+200;//P5Math.compareDist(this.getInitX(), this.getInitY(), p5.width, p5.height);
	}

	@Override
	public void draw() {
		p5.strokeWeight(P5Constants.STROKE_WEIGHT);
		if(!highLight){
			p5.stroke(P5Color.CAMOUFLAGE_GREEN[0],P5Color.CAMOUFLAGE_GREEN[1],P5Color.CAMOUFLAGE_GREEN[2], 100);
		}
		else{
			p5.stroke(P5Color.ORANGE[0],P5Color.ORANGE[1],P5Color.ORANGE[2], 100);
		}
		p5.noFill();
		p5.rect(playbarPos.getPosX(), playbarPos.getPosY(), playbarSize.getWidth(), playbarSize.getHeight());
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
			
			// remove the radial playBar when it is out of the window
			if(getWidth() > boundVal) {
				for(SoundObject so : soundObject){
					if(so.getCollideState().size() > index){
						so.removeCollideState(index);
					}
				}
				return true;
			}
			
			return false;
		}
	}

}
