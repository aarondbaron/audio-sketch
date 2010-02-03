package net.gtcmt.audiosketch.sound.synth;

import java.util.LinkedList;

import ddf.minim.AudioOutput;
import ddf.minim.SignalChain;
import ddf.minim.signals.SineWave;

/**
 * Abstract class for additive synthesis
 * @author akito
 *
 */
public abstract class AddSynth{

	private static final long serialVersionUID = 7314444336597786390L;
	public SignalChain sigChain;
	public LinkedList<SineWave> sineList;
	public long attackTime;
	public long sustainTime;
	public long releaseTime;
	
	public AddSynth(AudioOutput out, long attack, long sustain, long release) {
		sigChain = new SignalChain();
		sineList = new LinkedList<SineWave>();
		out.addSignal(sigChain);
		attackTime = attack;
		sustainTime = sustain;
		releaseTime = release;
	}
	
	public double mToF(int midiNote){
		return 440 * Math.pow(2, (double) (midiNote - 69)/12);
	}
}
