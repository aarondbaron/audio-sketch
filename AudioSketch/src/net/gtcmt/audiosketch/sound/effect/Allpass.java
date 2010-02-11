package net.gtcmt.audiosketch.sound.effect;

/**
 * 
 * @author akito
 * @deprecated
 */
public class Allpass 
{
	private float	feedback;
	private float[]	buffer;
	private int		bufsize;
	private int		bufidx;
	
	public Allpass()
	{
		bufidx = 0;
	}
	
	public void setbuffer(float[] buf,int size)
	{
		buffer = buf; 
		bufsize = size;
	}
	
	public float process(float input)
	{
		float output;
		float bufout;
		
		bufout = buffer[bufidx];
		undenormalise(bufout);
		
		output = -input + bufout;
		buffer[bufidx] = input + (bufout*feedback);

		if(++bufidx>=bufsize) bufidx = 0;

		return output;
	}
	
	public void mute()
	{
		for (int i=0; i<bufsize; i++)
			buffer[i]=0;
	}
	
	public void setfeedback(float val)
	{
		feedback = val;
	}
	
	public float getfeedback()
	{
		return feedback;
	}
	
	static public float undenormalise(float sample)
	{
		if((((int)sample)&0x7f800000)==0) sample=0.0f;
		return sample;
	}
	
}
