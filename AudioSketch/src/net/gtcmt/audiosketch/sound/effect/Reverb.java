package net.gtcmt.audiosketch.sound.effect;

import ddf.minim.AudioEffect;

public class Reverb implements AudioEffect {

	//constants
	// These values assume 44.1KHz sample rate
	private final int	numcombs		= 8;
	private final int	numallpasses	= 4;
	private final float	muted			= 0;
	private final float	fixedgain		= 0.015f;
	private final float scalewet		= 3;
	private final float scaledry		= 2;
	private final float scaledamp		= 0.4f;
	private final float scaleroom		= 0.28f;
	private final float offsetroom		= 0.7f;
	private final float initialroom		= 0.5f;
	private final float initialdamp		= 0.5f;
	private final float initialwet		= 1/scalewet;
	private final float initialdry		= 0;
	private final float initialwidth	= 1;
	private final float initialmode		= 0;
	private final float freezemode		= 0.5f;
	private final int	stereospread	= 23;
	
	private final int combtuningL1		= 1116;
	private final int combtuningR1		= 1116+stereospread;
	private final int combtuningL2		= 1188;
	private final int combtuningR2		= 1188+stereospread;
	private final int combtuningL3		= 1277;
	private final int combtuningR3		= 1277+stereospread;
	private final int combtuningL4		= 1356;
	private final int combtuningR4		= 1356+stereospread;
	private final int combtuningL5		= 1422;
	private final int combtuningR5		= 1422+stereospread;
	private final int combtuningL6		= 1491;
	private final int combtuningR6		= 1491+stereospread;
	private final int combtuningL7		= 1557;
	private final int combtuningR7		= 1557+stereospread;
	private final int combtuningL8		= 1617;
	private final int combtuningR8		= 1617+stereospread;
	private final int allpasstuningL1	= 556;
	private final int allpasstuningR1	= 556+stereospread;
	private final int allpasstuningL2	= 441;
	private final int allpasstuningR2	= 441+stereospread;
	private final int allpasstuningL3	= 341;
	private final int allpasstuningR3	= 341+stereospread;
	private final int allpasstuningL4	= 225;
	private final int allpasstuningR4	= 225+stereospread;
	
	//variable
	private float	gain;
	private float	roomsize,roomsize1;
	private float	damp,damp1;
	private float	wet,wet1,wet2;
	private float	dry;
	private float	width;
	private float	mode;
	
	// Comb filters
	private Comb[] combL=new Comb[numcombs];
	private Comb[] combR=new Comb[numcombs];

	// Allpass filters
	private Allpass[] allpassL=new Allpass[numallpasses];
	private Allpass[] allpassR=new Allpass[numallpasses];
	
	// Buffers for the combs
	private float[]	bufcombL1=new float[combtuningL1];
	private float[]	bufcombR1=new float[combtuningR1];
	private float[]	bufcombL2=new float[combtuningL2];
	private float[]	bufcombR2=new float[combtuningR2];
	private float[]	bufcombL3=new float[combtuningL3];
	private float[]	bufcombR3=new float[combtuningR3];
	private float[]	bufcombL4=new float[combtuningL4];
	private float[]	bufcombR4=new float[combtuningR4];
	private float[]	bufcombL5=new float[combtuningL5];
	private float[]	bufcombR5=new float[combtuningR5];
	private float[]	bufcombL6=new float[combtuningL6];
	private float[]	bufcombR6=new float[combtuningR6];
	private float[]	bufcombL7=new float[combtuningL7];
	private float[]	bufcombR7=new float[combtuningR7];
	private float[]	bufcombL8=new float[combtuningL8];
	private float[]	bufcombR8=new float[combtuningR8];

	// Buffers for the allpasses
	private float[]	bufallpassL1=new float[allpasstuningL1];
	private float[]	bufallpassR1=new float[allpasstuningR1];
	private float[]	bufallpassL2=new float[allpasstuningL2];
	private float[]	bufallpassR2=new float[allpasstuningR2];
	private float[]	bufallpassL3=new float[allpasstuningL3];
	private float[]	bufallpassR3=new float[allpasstuningR3];
	private float[]	bufallpassL4=new float[allpasstuningL4];
	private float[]	bufallpassR4=new float[allpasstuningR4];
	
	public Reverb()
	{
		int i;
		for(i=0;i<numcombs;i++)
		{
			combL[i]=new Comb();
			combR[i]=new Comb();
		}
		
		for(i=0;i<numallpasses;i++)
		{
			allpassL[i]=new Allpass();
			allpassR[i]=new Allpass();
		}
		
		combL[0].setbuffer(bufcombL1,combtuningL1);
		combR[0].setbuffer(bufcombR1,combtuningR1);
		combL[1].setbuffer(bufcombL2,combtuningL2);
		combR[1].setbuffer(bufcombR2,combtuningR2);
		combL[2].setbuffer(bufcombL3,combtuningL3);
		combR[2].setbuffer(bufcombR3,combtuningR3);
		combL[3].setbuffer(bufcombL4,combtuningL4);
		combR[3].setbuffer(bufcombR4,combtuningR4);
		combL[4].setbuffer(bufcombL5,combtuningL5);
		combR[4].setbuffer(bufcombR5,combtuningR5);
		combL[5].setbuffer(bufcombL6,combtuningL6);
		combR[5].setbuffer(bufcombR6,combtuningR6);
		combL[6].setbuffer(bufcombL7,combtuningL7);
		combR[6].setbuffer(bufcombR7,combtuningR7);
		combL[7].setbuffer(bufcombL8,combtuningL8);
		combR[7].setbuffer(bufcombR8,combtuningR8);
		allpassL[0].setbuffer(bufallpassL1,allpasstuningL1);
		allpassR[0].setbuffer(bufallpassR1,allpasstuningR1);
		allpassL[1].setbuffer(bufallpassL2,allpasstuningL2);
		allpassR[1].setbuffer(bufallpassR2,allpasstuningR2);
		allpassL[2].setbuffer(bufallpassL3,allpasstuningL3);
		allpassR[2].setbuffer(bufallpassR3,allpasstuningR3);
		allpassL[3].setbuffer(bufallpassL4,allpasstuningL4);
		allpassR[3].setbuffer(bufallpassR4,allpasstuningR4);

		// Set default values
		allpassL[0].setfeedback(0.5f);
		allpassR[0].setfeedback(0.5f);
		allpassL[1].setfeedback(0.5f);
		allpassR[1].setfeedback(0.5f);
		allpassL[2].setfeedback(0.5f);
		allpassR[2].setfeedback(0.5f);
		allpassL[3].setfeedback(0.5f);
		allpassR[3].setfeedback(0.5f);
		setwet(initialwet);
		setroomsize(initialroom);
		setdry(initialdry);
		setdamp(initialdamp);
		setwidth(initialwidth);
		setmode(initialmode);

		// Buffer will be full of rubbish - so we MUST mute them
		mute();
	}
	
	public void process(float[] signal) {
		process(signal,signal);
	}
	
	public void process(float[] sigLeft, float[] sigRight) 
	{
		float outL,outR,input;
		long numsamples=sigLeft.length;
		int bufindex=0;
		
		while(numsamples-- > 0)
		{
			outL = outR = 0;
			input = (sigLeft[bufindex] + sigRight[bufindex])* gain;

			// Accumulate comb filters in parallel
			int i;
			for(i=0; i<numcombs; i++)
			{
				outL += combL[i].process(input);
				outR += combR[i].process(input);
			}

			// Feed through allpasses in series
			for(i=0; i<numallpasses; i++)
			{
				outL = allpassL[i].process(outL);
				outR = allpassR[i].process(outR);
			}

			// Calculate output MIXING with anything already there
			sigLeft[bufindex] = outL*wet1 + outR*wet2 + sigLeft[bufindex]*dry;
			sigRight[bufindex] = outR*wet1 + outL*wet2 + sigRight[bufindex]*dry;

			// Increment sample pointers, allowing for interleave (if any)
			bufindex++;
		}
	}
	
	public void mute()
	{
		if (getmode() >= freezemode)
			return;

		int i;
		for (i=0;i<numcombs;i++)
		{
			combL[i].mute();
			combR[i].mute();
		}
		for (i=0;i<numallpasses;i++)
		{
			allpassL[i].mute();
			allpassR[i].mute();
		}
	}
	
	//parameter interfaces
	public void	setroomsize(float value)
	{
		roomsize = (value*scaleroom) + offsetroom;
		update();
	}
	public float getroomsize()
	{
		return (roomsize-offsetroom)/scaleroom;
	}
	public void	setdamp(float value)
	{
		damp = value*scaledamp;
		update();
	}
	public float getdamp()
	{
		return damp/scaledamp;
	}
	public void	setwet(float value)
	{
		wet = value*scalewet;
		update();
	}
	public float getwet()
	{
		return wet/scalewet;
	}
	public void	setdry(float value)
	{
		dry = value*scaledry;
	}
	public float getdry()
	{
		return dry/scaledry;
	}
	public void	setwidth(float value)
	{
		width = value;
		update();
	}
	public float getwidth()
	{
		return width;
	}
	public void	setmode(float value)
	{
		mode = value;
		update();
	}
	public float getmode()
	{
		if (mode >= freezemode)
			return 1;
		else
			return 0;
	}
	
	private void update()
	{
		// Recalculate internal values after parameter change

		int i;

		wet1 = wet*(width/2 + 0.5f);
		wet2 = wet*((1-width)/2);

		if (mode >= freezemode)
		{
			roomsize1 = 1;
			damp1 = 0;
			gain = muted;
		}
		else
		{
			roomsize1 = roomsize;
			damp1 = damp;
			gain = fixedgain;
		}

		for(i=0; i<numcombs; i++)
		{
			combL[i].setfeedback(roomsize1);
			combR[i].setfeedback(roomsize1);
		}

		for(i=0; i<numcombs; i++)
		{
			combL[i].setdamp(damp1);
			combR[i].setdamp(damp1);
		}
	}

}
