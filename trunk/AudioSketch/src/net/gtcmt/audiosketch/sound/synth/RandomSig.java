package net.gtcmt.audiosketch.sound.synth;

import ddf.minim.AudioOutput;
import ddf.minim.signals.SineWave;

/**
 * 
 * @author akito
 * @deprecated
 */
public class RandomSig extends AddSynth implements Runnable{

	private static final long serialVersionUID = -8444136963154317346L;
	private static int SR = 44100;
	private SineWave sine;
	private float incr=10;
	public float freq=100;
	private int numOsc = 10;
	private float[] amp;
	
	public RandomSig(AudioOutput out, int midiNote){
		super(out, 1, 100, 20);
		freq = (float) mToF(midiNote);
		amp = new float[numOsc]; 
	}

	public void run() {
		/*-------------- Allocate sound ---------------*/
		  for (int i=0; i < numOsc; i++) {
			  sine = new SineWave(freq, 0, SR);	
			  sineList.add(sine);
			  sigChain.add(sine);
			  freq *= (2f * Math.random());
			  amp[i] = (float) Math.random();
		  }
		  
		  /*-------------- Attack ---------------*/
		  for(int j=0; j<incr;j++){
			  for(int i=0;i<sineList.size();i++){
				  sineList.get(i).setAmp(((float)j/(incr*numOsc))*amp[i]);
			  }
			  try {
				  Thread.sleep(attackTime);
			  } catch (InterruptedException e) {
				  e.printStackTrace();
			  }
		  }  
		  /*--------------- Sustain ---------------*/
		  try {
			  Thread.sleep(sustainTime);
		  } catch (InterruptedException e) {
			  e.printStackTrace();
		  }
		  /*---------------- Release ----------------*/
		  for(int j=(int) incr; j>0;j--){
			  for(int i=0;i<sineList.size();i++){
				  sineList.get(i).setAmp(((float)j/(incr*numOsc))*amp[i]);
			  }
			  try {
				  Thread.sleep(releaseTime);
			  } catch (InterruptedException e) {
				  e.printStackTrace();
			  }
		  }
		  /*-------------- Remove Sound ---------------*/
		  for (int i=numOsc-1; i >= 0; i--) {
			  sigChain.disable(i);
			  sineList.remove(i);
		  }
	}
}
