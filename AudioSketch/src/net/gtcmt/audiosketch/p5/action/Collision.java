package net.gtcmt.audiosketch.p5.action;

import net.gtcmt.audiosketch.p5.object.SoundObject;
import net.gtcmt.audiosketch.p5.object.playbar.ClockBar;
import net.gtcmt.audiosketch.p5.object.playbar.PlayBackBar;
import net.gtcmt.audiosketch.p5.util.P5Constants;
import net.gtcmt.audiosketch.sound.util.AudioControl;

public class Collision {

	/**
	 * Detects collision for Radial time line. When collision happens initiate action.
	 */
	public static void collideCircle(SoundObject soundObject, PlayBackBar playBar){
		if(!soundObject.getCollideState(playBar)) {
			float objectX = (soundObject.getPosX()+(soundObject.getWidth()/2)) - playBar.getPosX();
			float objectY = (soundObject.getPosY()+(soundObject.getHeight()/2)) - playBar.getPosY();

			float minDistance = (float) ((Math.sqrt(Math.pow(soundObject.getWidth()/2, 2)
					+Math.pow(soundObject.getHeight()/2, 2))/4)+(playBar.getWidth()/2));

			if(Math.sqrt(objectX*objectX+objectY*objectY) < minDistance) {
				AudioControl.getAudioCtrl().trigger(soundObject.getSndType().toString(), (float) Math.random()*2);
				//soundObject.play();
				soundObject.setCollideState(playBar, true);
				soundObject.setCollide(true);
				soundObject.setGetFrame(true);
				soundObject.setStartTime(System.currentTimeMillis());		
			}
		}
	}
	
	public static void collideClockBar(SoundObject soundObject, PlayBackBar playBar){
		if(!soundObject.getCollideState(playBar)) {
			
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
				float ang = temp.getClockAngle();
				//System.out.println(ang);
				int p2X = (int) Math.round((Math.cos(ang+Math.PI)*len) + pBarX);//the Math.PI is necessary but shoudl be fixed so you dotn have to do this
				int p2Y = (int) Math.round((-Math.sin(ang+Math.PI)*len) + pBarY);
				//System.out.println("p2X: " + p2X + " p2Y: " + p2Y + "...pBarX: " + pBarX + " pBarY: " + pBarY );//what are coordinates
				//System.out.println(Math.sqrt(  Math.pow(pBarX-p2X,2) + Math.pow(pBarY-p2Y,2)));//just a length check
				
				float[] iSectPoints = sphere_line_iSect(p2X, p2Y, 0,pBarX, pBarY, 0, 0, sobjX, sobjY, soundObject.getWidth());
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
					soundObject.setCollideState(playBar, true);
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
	public static float[] sphere_line_iSect(int x1, int y1, int z1,int x2, int y2, int
			z2,int x3, int y3, int z3, int r) {

		





		float[] points=new float[7];
		//float a =  sq(x2 - x1) + sq(y2 - y1) + sq(z2 - z1);
		float a =  (float) (Math.pow(x2-x1, 2) + Math.pow(y2-y1,2) + Math.pow(z2-z1,2));

		float b =  2* ( (x2 - x1)*(x1 - x3) + (y2 - y1)*(y1 - y3) + (z2 -
				z1)*(z1 - z3) ) ;
		float c =  x3*x3 + y3*y3 + z3*z3 + x1*x1 + y1*y1 + z1*z1 - 2* (
				x3*x1 + y3*y1 + z3*z1 ) - r*r ;
		float i = b * b - 4 * a * c;
		System.out.println("a: "+ a + " b: "+ b + " c: "+ c);
		System.out.println("i: "+ i);

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
			points[3] = z1 + mu*(z2-z1);
			return(points);

		} else {
			// two intersections
			points[0] = 2;
			// first intersection
			float mu = (float)(-b + Math.sqrt( b*b - 4*a*c )) / (2*a);
			points[1] = x1 + mu*(x2-x1);
			points[2] = y1 + mu*(y2-y1);
			points[3] = z1 + mu*(z2-z1);
			// second intersection
			mu = (float)(-b - Math.sqrt(b*b - 4*a*c )) / (2*a);
			points[4] = x1 + mu*(x2-x1);
			points[5] = y1 + mu*(y2-y1);
			points[6] = z1 + mu*(z2-z1);
			return(points);

		}
	}
	
	/**
	 * Detects collision for time line bar. When collision happens initiate action.
	 */
	//TODO Akito come up with better collision algorithm
	public static void collideBar(SoundObject soundObject, PlayBackBar playBar){
		if(!soundObject.getCollideState(playBar)){	//TODO this will not work
			float objectX = (float) ((soundObject.getPosX()+(soundObject.getWidth()/2)) 
					- (playBar.getPosX()+(Math.cos(playBar.getAngle()+Math.PI)*(P5Constants.COLLISION_AREA/2))));
			float objectY = (float) ((soundObject.getPosY()+(soundObject.getHeight()/2)) 
					- (playBar.getPosY()+(Math.sin(playBar.getAngle()+Math.PI)*(P5Constants.COLLISION_AREA/2))));

			float minDistance = (float) ((Math.sqrt(Math.pow(soundObject.getWidth()/2, 2)
					+Math.pow(soundObject.getHeight()/2, 2))/4)+((playBar.getCollisionArea()-400)/2));

			if(Math.sqrt(objectX*objectX+objectY*objectY) < minDistance) {
				AudioControl.getAudioCtrl().trigger(soundObject.getSndType().toString(), (float) Math.random()*2);
				//soundObject.play();
				soundObject.setCollideState(playBar, true);
				soundObject.setCollide(true);
				soundObject.setGetFrame(true);
				soundObject.setStartTime(System.currentTimeMillis());
			}
		}
	}
}
