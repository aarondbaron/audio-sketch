package net.gtcmt.audiosketch.sound.synth;

import ddf.minim.AudioOutput;
import ddf.minim.signals.SineWave;

public class Blip extends AddSynth implements Runnable{

	private static final long serialVersionUID = -8444136963154317346L;
	private static int SR = 44100;
	private SineWave sine;
	private float incr=10;
	public float freq=100;
	private int numOsc = 10;
	
	public Blip(AudioOutput out, int midiNote){
		super(out, 1, 10, 1);
		freq = (float) mToF(midiNote);
	
	}

	public void run() {
		float[] amp = { 1f, 0.8f, 0.7f, 0.6f, 0.5f, 0.4f, 0.3f, 0.2f, 0.1f, 0.12f}; 
		/*-------------- Allocate sound ---------------*/
		  for (int i=0; i < numOsc; i++) {
			  sine = new SineWave(freq, 0, SR);	
			  sineList.add(sine);
			  sigChain.add(sine);
			  freq *= 2.6f;
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
