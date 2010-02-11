package net.gtcmt.audiosketch.sound.effect;

/**
 * 
 * @author akito
 * @deprecated
 */
public class Comb 
{
	private float	feedback;
	private float	filterstore;
	private float	damp1;
	private float	damp2;
	private float[]	buffer;
	private int		bufsize;
	private int		bufidx;
	
	public Comb()
	{
		filterstore = 0;
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

		output = buffer[bufidx];
		Allpass.undenormalise(output);

		filterstore = (output*damp2) + (filterstore*damp1);
		Allpass.undenormalise(filterstore);

		buffer[bufidx] = input + (filterstore*feedback);

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
	
	public void	setdamp(float val)
	{
		damp1 = val; 
		damp2 = 1-val;
	}
	public float getdamp()
	{
		return damp1;
	}
	
}
