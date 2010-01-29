package net.akito.shared.client.sound.effect;

import ddf.minim.AudioEffect;

public class Distort implements AudioEffect {
	
	
	public Distort() {}
	
	public void process(float[] signal) {
		for (int i=0; i<signal.length; i++){
			if(Math.abs(signal[i]) > 0.2)
				signal[i] = Math.abs(signal[i]);
			else
				signal[i] = 0.1f;
		}
	}

	public void process(float[] sigLeft, float[] sigRight) {
		for (int i=0; i<sigLeft.length; i++){
			if(Math.abs(sigLeft[i]) > 0.2)
				sigLeft[i] = Math.abs(sigLeft[i]);
			else
				sigLeft[i] = 0.1f;
		}
		for (int i=0; i<sigRight.length; i++){
			if(Math.abs(sigRight[i]) > 0.2)
				sigRight[i] = Math.abs(sigRight[i]);
			else
				sigRight[i] = 0.1f;
		}
	}
}
