package net.gtcmt.audiosketch.sound.effect;

import ddf.minim.AudioEffect;

/**
 * 
 * @author akito
 * @deprecated
 */
public class Delay implements AudioEffect {
	float[] delayBuf, delayBufR;
	float feedback;
	int index;
	public int bufSize;
	
	public Delay(int sampleRate) {
		// this(0.5f, 0.7f, sampleRate);
		this(0.784f, .7f, sampleRate);
	}
	
	public Delay (float time, float initalFeedback, int sampleRate)
	{
		bufSize = (int) (time * (float) sampleRate);
		delayBuf = new float[bufSize];
		delayBufR = new float[bufSize];
		feedback = initalFeedback;
		index = 0;
	}

	public void process(float[] signal) {
		for (int i=0; i<signal.length; i++)
		{
			float newSamp;
			index++;
			if (index > (bufSize-1))
				index=0;

			newSamp = delayBuf[(index+1) % bufSize];
			
			delayBuf[index] = signal[i] + (newSamp * feedback);
			
			signal[i] = newSamp;			
		}
	}

	public void process(float[] sigLeft, float[] sigRight) {
		for (int i=0; i<sigLeft.length; i++)
		{
			float newSamp, newSampR;
			index++;
			if (index > (bufSize-1))
				index=0;

			newSamp = delayBuf[(index + 1) % bufSize];
			newSampR = delayBufR[(index + 1) % bufSize];
			
			delayBuf[index] = sigLeft[i] + (newSamp * feedback);
			delayBufR[index] = sigRight[i] + (newSampR * feedback);
			
			sigLeft[i] = newSamp;			
			sigRight[i] = newSampR;
		}
	}
	
}