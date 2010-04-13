package net.gtcmt.audiosketch.p5.object.playbar;

import java.util.LinkedList;

import net.gtcmt.audiosketch.gui.util.GUIConstants;
import net.gtcmt.audiosketch.p5.action.Collision;
import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.util.P5Color;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.PlayBackType;
import net.gtcmt.audiosketch.wii.ShakeWii;
import net.gtcmt.audiosketch.wii.util.WiiMoteConstant;
import processing.core.PApplet;
import processing.core.PConstants;

public class SquareFillBar extends PlayBackBar{
	
	long time1 = System.currentTimeMillis();;
	long time2=0;
	long timeOutMS=1000;
	long startTime;
	public boolean bang = false;
	int vx=0;
	int vy=0;
	private ShakeWii shakeWii;
	private boolean isFillBarMovesItself;
	
	public int getVx() {
		return vx;
	}
	public void setVx(int vx) {
		this.vx = vx;
	}
	public int getVy() {
		return vy;
	}
	public void setVy(int vy) {
		this.vy = vy;
	}
	

	public SquareFillBar(P5Points2D objPos, float speed, float angle,
			PlayBackType pbType, PApplet p, ShakeWii shakeWii) {
		super(objPos, speed, angle, pbType, p);
		startTime = System.currentTimeMillis();
		this.shakeWii = shakeWii;
		this.isFillBarMovesItself = false;
		
		
		int w=150;
		int h=150;
		
		this.setWidth(w);
		this.setWidth(h);	
	}
	

	@Override
	public boolean checkState(LinkedList<SoundObject> soundObject, int index) {
		synchronized (soundObject) {
			//Check for collision
			for(SoundObject so : soundObject){
				if(so.getCollideState().size() > index){
					Collision.collideSquareFillBar(so, this, index);
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
		
		updatePos();
		p5.strokeWeight(P5Constants.STROKE_WEIGHT);
		if(!highLight){
			p5.stroke(P5Color.PERSIAN_GREEN[0],P5Color.PERSIAN_GREEN[1],P5Color.PERSIAN_GREEN[2], 100);
		}
		else{
			p5.stroke(P5Color.ORANGE[0],P5Color.ORANGE[1],P5Color.ORANGE[2], 200);
		}
		//p5.noStroke();
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
			p5.fill(P5Color.PERSIAN_GREEN[0],P5Color.PERSIAN_GREEN[1],P5Color.PERSIAN_GREEN[2], 100);
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
		
		//float speed = (float) Math.sqrt(Math.pow(p5.mouseX-xPos, 2)+Math.pow(mouseY-yPos, 2))/P5Constants.MAX_TRIG_DISTANCE;
		p5.rectMode(PConstants.CENTER);
		p5.rect(playbarPos.getPosX(), playbarPos.getPosY(), this.getWidth(), this.getWidth());	
	}


	private void updatePos() {
		if(isFillBarMovesItself){
			playbarPos.setPosX(playbarPos.getPosX()+vx);
			playbarPos.setPosY(playbarPos.getPosY()+vy);

			if(playbarPos.getPosX()<0+75)
			{
				vx=-vx;
			}

			if(playbarPos.getPosY()<0+75)
			{
				vy=-vy;
			}
			if(playbarPos.getPosX()>p5.width-75)
			{
				vx=-vx;
			}

			if(playbarPos.getPosY()>p5.height-75)
			{
				vy=-vy;
			}
		}
		else{ //Fill bar is controled by ir position of wii mote
			if(shakeWii.getListener().isFillBarMovesItself() && !isFillBarMovesItself){
				isFillBarMovesItself = true;
				vx = (int) ((shakeWii.getIrArrX()[0] - shakeWii.getIrArrX()[9])*0.1f);
				vy = (int) ((shakeWii.getIrArrY()[0] - shakeWii.getIrArrY()[9])*0.1f);
				
				//System.out.println("VX "+vx+" VY "+vy);
			}
			playbarPos.setPosX((int) (GUIConstants.WINDOW_WIDTH*(shakeWii.getListener().getIrX()/WiiMoteConstant.MAX_MOTE_IR_WIDTH)));
			playbarPos.setPosY((int) (GUIConstants.WINDOW_HEIGHT*(shakeWii.getListener().getIrY()/WiiMoteConstant.MAX_MOTE_IR_HEIGHT)));
		}
	}
}
