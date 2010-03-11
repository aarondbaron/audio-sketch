package net.gtcmt.audiosketch.p5.object;

import java.util.LinkedList;

import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.p5.util.P5Points2D;
import net.gtcmt.audiosketch.p5.util.P5Size2D;
import net.gtcmt.audiosketch.p5.util.P5Constants.ObjectColorType;
import net.gtcmt.audiosketch.sound.util.SndConstants.SndType;
import net.gtcmt.audiosketch.util.Constants;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;

/**
 * Sound object drawn on musical window
 * @author akito
 *
 */
public class SoundObject {
	
	private P5Points2D objPos;
	private P5Size2D objSize;
	private float[] color;
	private String shape;
	private int midiNote;
	private SndType sndType;
	private PShape image;
	private PShape[] children = new PShape[20];//20 is an arbitrary limit
	private LinkedList<Boolean> collideState;	//collide state for each playback
	private boolean isCollide;
	private boolean getFrame;
	private long frame=0;
	private float updateStep=0.0f;
	private float updateStep2=0.0f;
	private int id = -1;

	int numberOfShapes=7;
	float[] vinity;
	float[] vinitx;
	
	//private float updateStep2=(float)0.0;
	//private
	//sound name, pitch, filter type, cutoff, q, center freq, gain, effect type, effect on/off
	
	private static int MAX_DEGREE = 360;
	public long startTime = 0;

	/**
	 * SoundObject represents sound in Musical Window. It contains sound information.
	 * @param x Location on X-axis where the object is going to stay
	 * @param y Location on Y-axis where the object is going to stay
	 * @param size Size of the object given in points of vertex
	 * @param sndType Type of the sound this object contains
	 * @param colorSet Color of the object
	 * @param p
	 */
	public SoundObject(int id, P5Points2D objPos, P5Size2D objSize, ObjectColorType color, int shape, int midiNote, SndType sndType, PApplet p){
		this.objPos = objPos;
		this.objSize = objSize;
		this.color = chooseColor(color);
		this.shape = P5Constants.SHAPE_NAME[shape];//P5Constants.SHAPE_NAME[shape.ordinal()];
		this.midiNote = midiNote;
		this.sndType = sndType;
		this.isCollide = false;
		this.getFrame = false;
		this.collideState = new LinkedList<Boolean>();
		this.image = p.loadShape(Constants.SOUND_OBJECT_PATH+this.shape);
		
		//some of these children will be null if there aren't that many actual children, but you can handle that.
		for (int i=0;i<children.length;i++){
			   
			   children[i]=this.image.getChild(Integer.toString(i+1));
			    
		}
		
		this.image.disableStyle();
		this.id=id;
		
		vinity = new float[numberOfShapes];
		vinitx = new float[numberOfShapes];
		float s=200;
		for (int i=0;i<vinity.length;i++) {
			vinity[i]=(float)(-s*(2*Math.random()-1)); 
			//System.out.println(vinity[i]);
		}

		for (int i=0;i<vinitx.length;i++) {
			vinitx[i]=(float)(-s*(2*Math.random()-1)); 
			//System.out.println(vinitx[i]);
		}
		
	}



	/**
	 * Create color of sound object
	 * @param color
	 * @return
	 */
	public float[] chooseColor(ObjectColorType color){
		float[] colorSet = new float[3];
		switch (color) {
		case WHITE: colorSet[0] = 255f; colorSet[1] = 255f; colorSet[2] = 255f; break;
		case BLUE: colorSet[0] = 0; colorSet[1] = 0; colorSet[2] = 255f; break;
		case GREEN: colorSet[0] = 0; colorSet[1] = 255f; colorSet[2] = 0; break;
		case YELLOW: colorSet[0] = 255f; colorSet[1] = 255f; colorSet[2] = 0; break;
		case ORANGE: colorSet[0] = 255f; colorSet[1] = 125f; colorSet[2] = 0; break;
		}	
		return colorSet;
	}
	
	/**
	 * Draw sound object
	 * @param p5
	 */
	public void draw(PApplet p5){
		p5.pushMatrix();
		p5.translate(objPos.getPosX(), objPos.getPosY());
		
		if(!isCollide){
			
			p5.strokeWeight(5);
			p5.fill(255,255,255,100);
			p5.stroke(color[0], color[1], color[2], 200);
			p5.shapeMode(PConstants.CENTER);
			p5.shape(image, 0, 0, objSize.getWidth(), objSize.getHeight());
			
			
		}
		else {
			jitterAndFill(p5);	
		}

		
		
	//	PShape child = image.getChild("path3603");
	//	child.disableStyle();
	//
	//	p5.shape(child,0, 0,objSize.getWidth(), objSize.getHeight());
		
		//setup for explodeMultple
		//float explodeRate=.1f;
		//explodeMultiple(p5, numberOfShapes,explodeRate);
		
		//setup for spawnMultiple
		//float rotateRate = .007f;
		//float translateParam =  5.0f;
		//float sfactorParam =  (1.0f + .01f);
		//int numShapes = numberOfShapes;
		//spawnMultiple(p5,numShapes,rotateRate, translateParam,sfactorParam);

		
		
		p5.popMatrix();
	}

	private void spawnMultiple(PApplet p5, float numShapes, float rotateRate, float translateParam, float sfactorParam) {
		
		if(isCollide){
			if(getFrame){
				frame = p5.frameCount;
				getFrame=false;
			}
			//image.setVisible(false);
			
			//float theta = (p5.frameCount*2) % MAX_DEGREE;
			//float size = (float) (2.0 - Math.abs((double) (((p5.frameCount-frame)*2) % MAX_DEGREE) / (MAX_DEGREE/2)));
			//p5.rotate((float) Math.toRadians(theta));
			
			updateStep = updateStep + rotateRate;
			
			//updateStep2= updateStep2+ translateRate;
			
			
			for (int i=0; i<numShapes;i++){
				
				p5.rotate(updateStep);
				p5.translate((float) (translateParam*Math.cos(updateStep)),(float) (translateParam*Math.cos(updateStep)));
				p5.scale((float)(sfactorParam), (float)(sfactorParam));
				//p5.stroke(color[0] , color[1], color[2], 200);
				//p5.scale((float)(sfactorParam*Math.cos(updateStep)+.00000001), (float)(sfactorParam*Math.cos(updateStep)+.00000001));
				p5.shape(image, 0, 0, objSize.getWidth(), objSize.getHeight());

			}

			float timeOutMS=1000;
			timeOut(p5,timeOutMS, false);
		}
		
	}

	/**
	 * Rotate and jitter sound object
	 * @param p5
	 */
	private void rotateAndJitter(PApplet p5){
		//TODO isCollider no longer relevant?
		if(isCollide){
			if(getFrame){
				frame = p5.frameCount;
				getFrame=false;
			}
			float theta = (p5.frameCount*2) % MAX_DEGREE;
			//float size = (float) (2.0 - Math.abs((double) (((p5.frameCount-frame)*2) % MAX_DEGREE) / (MAX_DEGREE/2)));
			p5.rotate((float) Math.toRadians(theta));
			float sfactor=(float).1;
			p5.scale((float)(Math.random()*sfactor+1), (float)(Math.random()*sfactor+1));
			timeOut(p5, 500f,false );
		}
	}
	
	/**
	 * jitter and fill sound object
	 * @param p5
	 */
	private void jitterAndFill(PApplet p5){
		//TODO isCollider no longer relevant?

		if(getFrame){
			frame = p5.frameCount;
			getFrame=false;
		}
		//float theta = (p5.frameCount*2) % MAX_DEGREE;
		//float size = (float) (2.0 - Math.abs((double) (((p5.frameCount-frame)*2) % MAX_DEGREE) / (MAX_DEGREE/2)));
		//p5.rotate((float) Math.toRadians(theta));
		float sfactor=(float).1;
		p5.scale((float)(Math.random()*sfactor+1), (float)(Math.random()*sfactor+1));
		timeOut(p5, 500f,false );


		//////
		for (int i=0; i<children.length ;i++)
		{
			if(children[i]!=null)
			{
				//System.out.println(evod);
				//pushMatrix();
				/*
			    translate(tranparam*cos(num),-tranparam*cos(num));
			    rotate(num);
			    //*/

				p5.shape(children[i], 0, 0, objSize.getWidth(), objSize.getHeight());
				children[i].disableStyle();
				p5.noStroke();
				//hoping here that frameCount is always increasing???
				if(p5.frameCount%7==1){

					//fill(100+22*i,20+2*i,1+5*i,150);
					p5.fill((int)Math.random()*255,(int)Math.random()*255,(int)Math.random()*255,150);
				}
				else
				{
					p5.fill(50+22*i,70+2*i,100+5*i,150);
				}

				//popMatrix();

				//evod=counter%7;
				//counter++;
			}







		}
	}

	
	
	
	
	
	private void explodeMultiple(PApplet p5, float numShapes, float rate) {
		
		if(isCollide){
			if(getFrame){
				frame = p5.frameCount;
				getFrame=false;
			}
			//image.setVisible(false);
			
			//float theta = (p5.frameCount*2) % MAX_DEGREE;
			//float size = (float) (2.0 - Math.abs((double) (((p5.frameCount-frame)*2) % MAX_DEGREE) / (MAX_DEGREE/2)));
			//p5.rotate((float) Math.toRadians(theta));
			
			updateStep2 = updateStep2 + rate;
			
			float cosup=(float)Math.cos(updateStep2);
			
			//updateStep2= updateStep2+ translateRate;
			float accel=(float)9.8;
			
			for (int i=0; i<numShapes;i++){
				
				p5.pushMatrix();
				
				//System.out.println(vinitx[i]*updateStep);
				//System.out.println(-Math.abs(vinity[i])*updateStep+.5f*accel*updateStep*updateStep);
				//p5.translate(vinitx[i]*updateStep2, -Math.abs(vinity[i])*updateStep2+.5f*accel*updateStep2*updateStep2 );
				p5.translate(vinitx[i]*cosup, -Math.abs(vinity[i])*cosup+.5f*accel*cosup*cosup );
				
				//p5.scale((float)(sfactorParam), (float)(sfactorParam));
				//p5.stroke(color[0] , color[1], color[2], 200);
				p5.scale( .3f, .3f);
				p5.shape(image, 0, 0, objSize.getWidth(), objSize.getHeight());
				p5.popMatrix();

			}

			float timeOutMS=2000;
			timeOut(p5,timeOutMS, true);
		}
		
	}
	
	
	
	/**
	 * Amount of time sound object will rotate
	 * @param p5
	 * @param timeOutMS 
	 */
	private void timeOut(PApplet p5, float timeOutMS, boolean bval){
		if(System.currentTimeMillis()  - startTime > timeOutMS){
			isCollide = false;
			//image.setVisible(true);
			
			if(bval){
				for (int i=0;i<vinity.length;i++) {
					vinity[i]=(float)(-70*(2*Math.random()-1)); 
					//System.out.println(vinity[i]);
				}

				for (int i=0;i<vinitx.length;i++) {
					vinitx[i]=(float)(-70*(2*Math.random()-1)); 
					//System.out.println(vinitx[i]);
				}
				//updateStep2=0.0f;
			}
			
			
		}
	}

	/*------------------ Getter/Setter ----------------*/
	public int getPosX() {
		return objPos.getPosX();
	}

	public void setPosX(int posX) {
		this.objPos.setPosX(posX);
	}

	public int getPosY() {
		return objPos.getPosY();
	}

	public void setPosY(int posY) {
		this.objPos.setPosY(posY);
	}

	public void setPos(int posX, int posY) {
		objPos.setPosX(posX);
		objPos.setPosY(posY);
	}
	
	public int getWidth() {
		return objSize.getWidth();
	}

	public void setWidth(int width) {
		this.objSize.setWidth(width);
	}

	public int getHeight() {
		return objSize.getHeight();
	}

	public void setHeight(int height) {
		this.objSize.setHeight(height);
	}

	public synchronized boolean isCollide() {
		return isCollide;
	}

	public void setCollide(boolean collide) {
		this.isCollide = collide;
	}

	public boolean isGetFrame() {
		return getFrame;
	}

	public void setGetFrame(boolean getFrame) {
		this.getFrame = getFrame;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	public LinkedList<Boolean> getCollideState() {
		return collideState;
	}
	
	public void addCollideState(boolean collideState){
		this.collideState.add(collideState);
	}
	
	public boolean getCollideState(int index){
		return this.collideState.get(index);
	}
	
	public void removeCollideState(int index) {
		this.collideState.remove(index);
	}

	public void setCollideState(int index, boolean bool) {
		this.collideState.set(index, bool);
	}

	public SndType getSndType() {
		return sndType;
	}

	public void setSndType(SndType sndType) {
		this.sndType = sndType;
	}
	
	public int getMidiNote() {
		return midiNote;
	}

	public void setMidiNote(int midiNote) {
		this.midiNote = midiNote;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
