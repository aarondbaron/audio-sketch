package net.gtcmt.audiosketch.p5.action;

import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.object.playbar.CircleFillBar;
import net.gtcmt.audiosketch.p5.object.playbar.ClockBar;
import net.gtcmt.audiosketch.p5.object.playbar.PlayBackBar;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.sound.util.AudioControl;

public class Collision {

	/**
	 * Detects collision for Radial time line. When collision happens initiate action.
	 */
	public static void collideCircle(SoundObject soundObject, PlayBackBar playBar, int index){
		if(!soundObject.getCollideState(index)) {
			float objectX = (soundObject.getPosX()) - playBar.getPosX();
			float objectY = (soundObject.getPosY()) - playBar.getPosY();

			float minDistance = (float) ((Math.sqrt(Math.pow(soundObject.getWidth()/3, 2)
					+Math.pow(soundObject.getHeight()/3, 2)))+(playBar.getWidth()/2));

			if(Math.sqrt(objectX*objectX+objectY*objectY) < minDistance) {
				AudioControl.getAudioCtrl().trigger(soundObject.getSndType().toString(), (float) (Math.random()*.01 +1));
				soundObject.setCollideState(index, true);
				soundObject.setCollide(true);
				soundObject.setGetFrame(true);
				soundObject.setStartTime(System.currentTimeMillis());		
			}
		}
	}
	
	public static void collideClockBar(SoundObject soundObject, PlayBackBar playBar, int index){
		if(!soundObject.getCollideState(index)) {
			
			ClockBar temp= (ClockBar) playBar;//is this necessary?
			int pBarX = playBar.getPosX();
			int pBarY = playBar.getPosY();
			int len = temp.getLen();
			
			int sobjX=soundObject.getPosX();
			int sobjY=soundObject.getPosY();
			int dX=sobjX-pBarX;
			int dY=sobjY-pBarY;
			int dR=(int) Math.round(Math.sqrt(dX*dX +dY*dY));
			

			if(dR<len){
				
				//System.out.println("dR is " + dR);
				//float ang = temp.getClockAngle();
				float ang = temp.getSomeAngle();
				//System.out.println(ang);
				int p2X = (int) Math.round((Math.cos(ang+Math.PI/2)*len) + pBarX);//the Math.PI is necessary but shoudl be fixed so you dotn have to do this
				int p2Y = (int) Math.round((Math.sin(ang+Math.PI/2)*len) + pBarY);
				//System.out.println("p2X: " + p2X + " p2Y: " + p2Y + "...pBarX: " + pBarX + " pBarY: " + pBarY );//what are coordinates
				//System.out.println(Math.sqrt(  Math.pow(pBarX-p2X,2) + Math.pow(pBarY-p2Y,2)));//just a length check
				
				float[] iSectPoints = sphere_line_iSect(p2X, p2Y, pBarX, pBarY, sobjX, sobjY, soundObject.getWidth());
				//System.out.println("iSectPoints[0]: "+ iSectPoints[0]);
				/*for(int i = 0; i<iSectPoints.length;i++){
				System.out.println("iSectPoints[" + i + "]: "+ iSectPoints[i]);

				}*/

				if (iSectPoints[0]>0) {
					/*for(int i = 0; i<iSectPoints.length;i++){
						System.out.println("iSectPoints[" + i + "]: "+ iSectPoints[i]);

					}*/
					AudioControl.getAudioCtrl().trigger(soundObject.getSndType().toString(), (float) Math.random()*2);
					//soundObject.play();
					soundObject.setCollideState(index, true);
					soundObject.setCollide(true);
					soundObject.setGetFrame(true);
					soundObject.setStartTime(System.currentTimeMillis());		
				}
			}
		}
	}
	
	/**
	 * computes 2d/3d circle/sphere - line intersection points 
	 * based on code by peter bourke 
	 * returns array of floats, where first item specifies number of intersection points found {0, 1 or 2} 
	 * set Z coordinates to 0 for 2D intersection checks 
	 * line defined by points {x1;y1;z1} -> {x2;y2;z2} 
	 * circle/sphere defined by center point at {x3;y3;z3} and radius r 
	 **/
	public static float[] sphere_line_iSect(int x1, int y1,int x2, int y2, 
			int x3, int y3, int r) {

		





		float[] points=new float[5];
		//float a =  sq(x2 - x1) + sq(y2 - y1) + sq(z2 - z1);
		float a =  (float) (Math.pow(x2-x1, 2) + Math.pow(y2-y1,2) );

		float b =  2* ( (x2 - x1)*(x1 - x3) + (y2 - y1)*(y1 - y3)  ) ;
		float c =  x3*x3 + y3*y3  + x1*x1 + y1*y1  - 2* (
				x3*x1 + y3*y1  ) - r*r ;
		float i = b * b - 4 * a * c;
		//System.out.println("a: "+ a + " b: "+ b + " c: "+ c);
		//System.out.println("i: "+ i);

		if ( i < 0.0 ) {
			// no intersection
			points[0] = 0;
			return(points);

		} else if ( i == 0.0 ) {
			// one intersection
			points[0] = 1;
			float mu = -b/(2*a) ;
			points[1] = x1 + mu*(x2-x1);
			points[2] = y1 + mu*(y2-y1);
			//System.out.println("i: "+ i);
			return(points);
			

		} else {
			// two intersections
			points[0] = 2;
			// first intersection
			float mu = (float)(-b + Math.sqrt( b*b - 4*a*c )) / (2*a);
			points[1] = x1 + mu*(x2-x1);
			points[2] = y1 + mu*(y2-y1);
			// second intersection
			mu = (float)(-b - Math.sqrt(b*b - 4*a*c )) / (2*a);
			points[3] = x1 + mu*(x2-x1);
			points[4] = y1 + mu*(y2-y1);
			//System.out.println("i: "+ i);
			
			//System.out.println("point1: "+ points[1]);
			//System.out.println("point2: "+ points[2]);
			//System.out.println("point3: "+ points[3]);
			//System.out.println("point4: "+ points[4]);
			
			return(points);

		}
	}
	
	/**
	 * Detects collision for time line bar. When collision happens initiate action.
	 */
	//TODO Akito come up with better collision algorithm
	public static void collideBar(SoundObject soundObject, PlayBackBar playBar, int index){
		

		/*
		int pBarX = playBar.getPosX();
		int pBarY = playBar.getPosY();
		int sobjX=soundObject.getPosX();
		int sobjY=soundObject.getPosY();	
		int p2X = (int) Math.round((Math.cos(playBar.getAngle()+Math.PI/2)*1) + pBarX);//the Math.PI is necessary but shoudl be fixed so you dotn have to do this
		int p2Y = (int) Math.round((Math.sin(playBar.getAngle()+Math.PI/2)*1) + pBarY);
		

		
		
		float[] iSectPoints = sphere_line_iSect(p2X, p2Y, pBarX, pBarY, sobjX, sobjY, soundObject.getWidth());
		//System.out.println("iSectPoints[0]: "+ iSectPoints[0]);


		if (iSectPoints[0]>0) {
			//for(int i = 0; i<iSectPoints.length;i++){
				//System.out.println("iSectPoints[" + i + "]: "+ iSectPoints[i]);

			//}
			AudioControl.getAudioCtrl().trigger(soundObject.getSndType().toString(), (float) Math.random()*2);
			//soundObject.play();
			soundObject.setCollideState(playBar, true);
			soundObject.setCollide(true);
			soundObject.setGetFrame(true);
			soundObject.setStartTime(System.currentTimeMillis());		
		}
		*/
		
		
		
		
		//this works ok, but it only triggers on a particular part of the object.
		if(!soundObject.getCollideState(index)){	//TODO this will not work
			float objectX = (float) ((soundObject.getPosX()+(soundObject.getWidth()/2)) 
					- (playBar.getPosX()+(Math.cos(playBar.getAngle()+Math.PI)*(P5Constants.COLLISION_AREA/2))));
			float objectY = (float) ((soundObject.getPosY()+(soundObject.getHeight()/2)) 
					- (playBar.getPosY()+(Math.sin(playBar.getAngle()+Math.PI)*(P5Constants.COLLISION_AREA/2))));

			float minDistance = (float) ((Math.sqrt(Math.pow(soundObject.getWidth()/2, 2)
					+Math.pow(soundObject.getHeight()/2, 2))/4)+((playBar.getCollisionArea()-400)/2));

			if(Math.sqrt(objectX*objectX+objectY*objectY) < minDistance) {
				AudioControl.getAudioCtrl().trigger(soundObject.getSndType().toString(), (float) Math.random()*2);
				//soundObject.play();
				soundObject.setCollideState(index, true);
				soundObject.setCollide(true);
				soundObject.setGetFrame(true);
				soundObject.setStartTime(System.currentTimeMillis());
			}
		}
		
		
		
		
	}

	public static void collideSquare(SoundObject soundObject, PlayBackBar playBar, int index) {
		// TODO need to make this correct..it functions like colide circle now
		//

		if(!soundObject.getCollideState(index)) {
			float objectX = (soundObject.getPosX()) - playBar.getPosX();
			float objectY = (soundObject.getPosY()) - playBar.getPosY();

			float minDistance = (float) ((Math.sqrt(Math.pow(soundObject.getWidth()/3, 2)
					+Math.pow(soundObject.getHeight()/3, 2)))+(playBar.getWidth()/2));

			if(Math.sqrt(objectX*objectX+objectY*objectY) < minDistance) {
				AudioControl.getAudioCtrl().trigger(soundObject.getSndType().toString(), (float) (Math.random()*.01 +1));
				soundObject.setCollideState(index, true);
				soundObject.setCollide(true);
				soundObject.setGetFrame(true);
				soundObject.setStartTime(System.currentTimeMillis());		
			}
		}
	}
	
	
	public static void collideCircleFillBar(SoundObject soundObject, PlayBackBar playBackBar, int index) {
		// TODO need to make this correct..it functions like colide circle now
		//
		CircleFillBar playBar=(CircleFillBar)playBackBar;
		if(playBar.bang){

			if(!soundObject.getCollideState(index)) {
				float objectX = (soundObject.getPosX()) - playBar.getPosX();
				float objectY = (soundObject.getPosY()) - playBar.getPosY();
				
				System.out.println(objectX);
				System.out.println(objectY);

				float minDistance = (float) ((Math.sqrt(Math.pow(soundObject.getWidth()/3, 2)
						+Math.pow(soundObject.getHeight()/3, 2)))+(playBar.getWidth()/2));
				System.out.println(minDistance);

				if(Math.sqrt(objectX*objectX+objectY*objectY) < minDistance) {
					AudioControl.getAudioCtrl().trigger(soundObject.getSndType().toString(), (float) (Math.random()*.01 +1));
					soundObject.setCollideState(index, true);
					soundObject.setCollide(true);
					soundObject.setGetFrame(true);
					soundObject.setStartTime(System.currentTimeMillis());		
				}
			}
		}
		
	}




}
